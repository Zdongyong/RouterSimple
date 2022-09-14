package com.zdy.module_router_compile;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.zdy.module_router_annotation.VRouter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * 创建日期：2022/9/14 on 21:26
 * 描述：
 * 作者：zhudongyong
 */
//@AutoService(Process.class)
@SupportedAnnotationTypes(ProcessorConfig.VROUTER_ANNOTATION)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class RouterProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Filer mFiler;
    private String targetModuleName = ""; //每个业务module需要在gralde配置
    private Messager vLog;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        vLog = processingEnv.getMessager();
        Map<String, String> envOptions = processingEnv.getOptions();
        Set<String> keys = envOptions.keySet();
        for (String key:keys){
            if ("targetModuleName".equals(key)){
                targetModuleName = envOptions.get(key);//获取组名
                vLog.printMessage(Diagnostic.Kind.NOTE,"<VRouter> get targetModuleName is :"+targetModuleName);
            }
        }
        vLog.printMessage(Diagnostic.Kind.NOTE,"<VRouter> =====init success=====");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.size()<=0 || annotations.isEmpty()){
            vLog.printMessage(Diagnostic.Kind.NOTE,"<VRouter> annotations is null");
            return false;
        }

        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(VRouter.class);

        ClassName className = ClassName.get(ProcessorConfig.TARGET_PACKAGE_NAME, ProcessorConfig.TARGET_INTERFACE_NAME);
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder((targetModuleName.length() == 0 ? "Apt" : targetModuleName) + ProcessorConfig.TARGET_INTERFACE_NAME)
                .addSuperinterface(className)
                .addModifiers(Modifier.PUBLIC);

        TypeElement activityRouteTableInitializerTypeElement = elementUtils.getTypeElement(className.toString());
        List<? extends Element> members = elementUtils.getAllMembers(activityRouteTableInitializerTypeElement);

        MethodSpec.Builder routerMethodSpecBuilder = null;
        for (Element element : members) {
            String simpleName = element.getSimpleName().toString();
            vLog.printMessage(Diagnostic.Kind.NOTE,"<VRouter> simpleName is : "+simpleName);
            if (ProcessorConfig.TARGET_INTERFACE_METHOD_NAME.equals(simpleName)) {
                routerMethodSpecBuilder = MethodSpec.overriding((ExecutableElement) element);
            }
        }

        if (routerMethodSpecBuilder == null){
            vLog.printMessage(Diagnostic.Kind.NOTE,"<VRouter> no class process @VRouter or no method name is "+ProcessorConfig.TARGET_INTERFACE_METHOD_NAME);
            return false;
        }

        for (Element element:elementsAnnotatedWith){
            VRouter vRouter = element.getAnnotation(VRouter.class);
            TypeElement typeElement = (TypeElement) element;
            String path = vRouter.path();
            routerMethodSpecBuilder.addStatement("arg0.put($S, $T.class)", path, typeElement.asType());
        }

        vLog.printMessage(Diagnostic.Kind.NOTE,"<VRouter> begin to writeTo file");

        //通过JavaFile创建文件
        JavaFile javaFile = JavaFile.builder(ProcessorConfig.TARGET_PACKAGE_NAME, typeSpec.addMethod(routerMethodSpecBuilder.build()).build()).build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}