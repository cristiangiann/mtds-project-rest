package projectMTDS.api;

import com.google.gson.JsonObject;
import projectMTDS.utils.Message;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.util.Map;

import static projectMTDS.utils.Utils.*;

abstract class API {
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

    static String getParameterFromBody(String body, String fieldName){
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        return jsonObject.get(fieldName).getAsString();
    }

    static void logRequestData(Request request) {
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

    static String unauthorized(Response response){
        response.status(401);
        return gson.toJson(new Message("Unauthorized", "/"));
    }

    static String resourceNotFound(Response response){
        response.status(404);
        return gson.toJson(new Message("Not found", "/"));
    }

    static void addImageUrl(Map<String, String> linkMap){
        linkMap.put("image_by_id", IMAGE_API_URL);
    }

    static void addImageUrlById(Map<String, String> linkMap, String id){
        linkMap.put("image", imageUrl(id));
    }

    static void addUploadedImageUrl(Map<String, String> linkMap, String imageId){
        linkMap.put("uploaded_image", imageId);
    }

    static void addImagesUrl(Map<String, String> linkMap){
        linkMap.put("images", IMAGES_API_URL);
    }

    static void addUsersUrl(Map<String, String> linkMap){
        linkMap.put("users", USERS_API);
    }

    static void addGetImagePreviewsUrl(Map<String, String> linkMap){
        linkMap.put("image_previews", PREVIEW_API_URL);
    }

    static void addSelfUrl(Map<String, String> linkMap, String url){
        linkMap.put("self", url);
    }

    static void addLoginUrl(Map<String, String> linkMap){
        linkMap.put("login", LOGIN_API_URL);
    }

    static void addLogoutUrl(Map<String, String> linkMap){
        linkMap.put("logout", LOGOUT_API_URL);
    }

    static String createResponseBody(Object response, Map<String, String> links){
        ResponseBody responseBody = new ResponseBody(response, links);
        return gson.toJson(responseBody);
    }

    static String createResponseBody(Map<String, String> links){
        return createResponseBody(null, links);
    }
}
