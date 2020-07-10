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
        String loggedUser = authenticator.getUserFromSession(request.cookies());
        if(loggedUser == null) return invalidSession(response, invalidSessionRelatedLinks());

        String imageId = request.params(":id");
        if(!modelManager.existImage(imageId)) {
            logger.info("Image " + imageId + " not found");
            return resourceNotFound(response, relatedLinks(imageId));
        }

        if(!modelManager.getImage(imageId).getUserId().equals(loggedUser)) {
            logger.info("User " + loggedUser + " is not authorized to retrieve image " + imageId);
            return unauthorized(response, relatedLinks(imageId));
        }
        logger.info("Image " + imageId + " deleted");
        modelManager.deleteImage(imageId);
        response.status(200);
        return createResponseBody(relatedLinks(imageId));
    }

    static private Map<String, String> relatedLinks(String imageId) {
        Map<String, String> linkMap = new HashMap<>();
        addSelfUrl(linkMap, imageUrl(imageId));
        addImagesUrl(linkMap);
        addLogoutUrl(linkMap);
        return linkMap;
    }
}
