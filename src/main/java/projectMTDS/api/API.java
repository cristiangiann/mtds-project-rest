package projectMTDS.api;

import com.google.gson.JsonObject;
import projectMTDS.utils.Message;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.Map;

import static projectMTDS.utils.Utils.gson;
import static projectMTDS.utils.Utils.logger;

public abstract class API {
    private static class ResponseBody {
        private Object response;
        private Map<String, String> _links;
        private ResponseBody(Object response, Map<String, String> links){
            this.response = response;
            this._links = links;
        }
    }

    static boolean emptyParameter(String parameter){
        return StringUtils.isEmpty(parameter);
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

    public static void addUrl(Map<String, String> resources, String resourceName, String resourceUrl){
        resources.put(resourceName, resourceUrl);
    }

    static String createResponseBody(Object response, Map<String, String> links){
        ResponseBody responseBody = new ResponseBody(response, links);
        return gson.toJson(responseBody);
    }

    static String createResponseBody(Map<String, String> links){
        return createResponseBody(null, links);
    }
}
