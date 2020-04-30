package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.Image;
import projectMTDS.model.User;
import spark.Request;
import spark.utils.StringUtils;

abstract class API {
    static boolean emptyParameter(String parameter){
        return StringUtils.isEmpty(parameter);
    }

    static User getUserFromBody(Request request){
        Gson gson = new Gson();
        String body = request.body();
        return gson.fromJson(body, User.class);
    }
    static Image getImageFromBody(Request request){
        Gson gson = new Gson();
        String body = request.body();
        return gson.fromJson(body, Image.class);
    }
}
