package com.zdy.router;


import java.util.Map;

public interface IRouter {

    void classRegister(Map<String, Class<? extends IdisPatchData>> router);

}
