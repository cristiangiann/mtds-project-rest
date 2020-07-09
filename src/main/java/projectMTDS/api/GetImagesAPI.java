package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static projectMTDS.utils.ImageSnapshot.getSnapshotsFromImageList;
import static projectMTDS.utils.Utils.*;

public class GetImagesAPI extends API{
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();
        Authenticator authenticator = Authenticator.getInstance();
        logRequestData(request);

        String loggedUser = authenticator.getUserFromSession(request.cookies());
        if(loggedUser == null) return invalidSession(response, relatedLinks(401));

        String userId = request.params("userId");
        if(userId == null) userId = loggedUser;
        else if(!userId.equals(loggedUser)) {
            logger.info("User: " + loggedUser + " is not authorized to get images of " + userId);
            return unauthorized(response, relatedLinks(401));
        }

        logger.info("GET images received by user " + userId);
        response.status(200);
        return createResponseBody(getSnapshotsFromImageList(modelManager.getImagesByUser(userId)), relatedLinks(response.status()));
    }

    static private Map<String, String> relatedLinks(int status){
        Map<String, String> linkMap = new HashMap<>();
        addSelfUrl(linkMap, IMAGES_API_URL);
        if (status == 401) addLoginUrl(linkMap);
        addLogoutUrl(linkMap);
        addGetImagePreviewsUrl(linkMap);
        addImageUrl(linkMap);
        return linkMap;
    }
}
