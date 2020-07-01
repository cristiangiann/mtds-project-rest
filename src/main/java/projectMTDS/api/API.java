package projectMTDS.api;

import com.google.gson.JsonObject;
import projectMTDS.model.Image;
import spark.Request;
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
