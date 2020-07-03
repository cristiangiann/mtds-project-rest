package projectMTDS.api;

import com.google.gson.JsonObject;
import projectMTDS.model.Image;
import projectMTDS.utils.Message;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import static projectMTDS.utils.Utils.gson;
import static projectMTDS.utils.Utils.logger;

public abstract class API {
    protected static boolean emptyParameter(String parameter){
        return StringUtils.isEmpty(parameter);
    }

    static Image getImageFromBody(Request request){
        String body = request.body();
        return gson.fromJson(body, Image.class);
    }

    protected static String getParameterFromBody(String body, String fieldName){
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        return jsonObject.get(fieldName).getAsString();
    }

    protected static void logRequestData(Request request) {
        logger.info("New API call - Path: " + request.pathInfo() + "\n\t\t" +
                "Request method: " + request.requestMethod() + "\n\t\t" +
                "Content type: " + request.contentType() + "\n\t\t" +
                "Request body: " + request.body());
    }

    static void logRequestImageFormData(Request request){
        logger.info("New API call - Path: " + request.pathInfo() + "\n\t\t" +
                "Request method: " + request.requestMethod() + "\n\t\t" +
                "Content type: " + request.contentType() + "\n\t\t" +
                "Image details: " + request.raw().getParameter("image_properties"));
    }

    static String invalidSession(Response response){
        logger.info("Bad Request error - Invalid session");
        response.status(401);
        return gson.toJson(new Message("Invalid session", "/"));
    }
}
