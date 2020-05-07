package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.*;
import spark.Request;
import spark.utils.StringUtils;

import static projectMTDS.controller.APIManager.logger;

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

    static void logRequestData(Request request) {
        logger.info("Request method: " + request.requestMethod());
        logger.info("Content type: " + request.contentType());
        logger.info("Request body: " + request.body());
    }

    static void logRequestImageFormData(Request request){
        logger.info("Request method: " + request.requestMethod());
        logger.info("Content type: " + request.contentType());
        logger.info("Image details: " + request.raw().getParameter("image_properties"));
    }
}
