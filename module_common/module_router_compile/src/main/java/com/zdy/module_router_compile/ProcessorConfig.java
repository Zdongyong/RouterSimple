package com.zdy.module_router_compile;

/**
 * 创建日期：2022/9/14 on 21:31
 * 描述：
 * 作者：zhudongyong
 */

interface ProcessorConfig {

    String VROUTER_ANNOTATION = "com.zdy.module_router_annotation.VRouter";

    String TARGET_PACKAGE_NAME = "com.zdy.router";
    String TARGET_INTERFACE_NAME = "IRouter";
    String TARGET_INTERFACE_METHOD_NAME = "classRegister";
}
