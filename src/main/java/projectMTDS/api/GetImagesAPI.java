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

        String userId = authenticator.getUserFromSession(request.cookies());
        if(userId == null) return invalidSession(response);

        logger.info("GET images received by user " + userId);
        response.status(200);
        return createResponseBody(getSnapshotsFromImageList(modelManager.getImagesByUser(userId)), relatedLinks());
    }

    static private Map<String, String> relatedLinks(){
        Map<String, String> linkMap = new HashMap<>();
        addUrl(linkMap, "image", IMAGE_API_URL);
        addUrl(linkMap, "image_preview", PREVIEW_API_URL);
        addUrl(linkMap, "logout", LOGOUT_API_URL);
        addUrl(linkMap, "gallery", GALLERY_URL);
        addUrl(linkMap, "self", IMAGES_API_URL);
        return linkMap;
    }
}
