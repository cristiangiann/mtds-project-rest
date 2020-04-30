package projectMTDS.controller.API;

import spark.utils.StringUtils;

abstract class API {
    static boolean checkParameter(String parameter){
        return !StringUtils.isEmpty(parameter);
    }
}
