package projectMTDS.controller.API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import projectMTDS.model.Image;
import spark.Request;
import spark.utils.StringUtils;

import static projectMTDS.controller.APIManager.logger;

public abstract class API {
    protected static boolean emptyParameter(String parameter){
        return StringUtils.isEmpty(parameter);
    }

    static Image getImageFromBody(Request request){
        Gson gson = new Gson();
        String body = request.body();
        return gson.fromJson(body, Image.class);
    }

    protected static String getParameterFromBody(String body, String fieldName){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        return jsonObject.get(fieldName).getAsString();
    }

    protected static void logRequestData(Request request) {
        logger.info("Request method: " + request.requestMethod());
        logger.info("Content type: " + request.contentType());
        logger.info("Request body: " + request.body());
    }

    static void logRequestImageFormData(Request request){
        logger.info("Request method: " + request.requestMethod());
        logger.info("Content type: " + request.contentType());
        logger.info("Image details: " + request.raw().getParameter("image_properties"));
    }

//    static boolean checkAuthorization(String sessionId, String userId){
//        return Authenticator.getInstance().checkSession(sessionId, userId);
//    }
}
