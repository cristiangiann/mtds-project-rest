package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static projectMTDS.utils.Utils.*;

public class DeleteImageAPI extends API{
    public static String call(Request request, Response response) {
        Authenticator authenticator = Authenticator.getInstance();
        ModelManager modelManager = ModelManager.getInstance();

        logRequestData(request);
        String userId = authenticator.getUserFromSession(request.cookies());
        if(userId == null) return invalidSession(response);

        String imageId = request.params(":id");
        if(emptyParameter(imageId)){
            response.status(400);
            return gson.toJson("Image ID is not valid.");
        }

        if(!modelManager.existImage(userId, imageId)){
            response.status(404);
            return gson.toJson("Image " + imageId + " not found in the repository of " + userId);
        }

        modelManager.deleteImage(userId, imageId);
        response.status(200);
        return createResponseBody(relatedLinks());
    }

    static private Map<String, String> relatedLinks(){
        Map<String, String> linkMap = new HashMap<>();
        addUrl(linkMap, "redirect_to", GALLERY_URL);
        return linkMap;
    }
}
