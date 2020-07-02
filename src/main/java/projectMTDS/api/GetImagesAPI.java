package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import static projectMTDS.utils.Utils.gson;
import static projectMTDS.utils.ImageSnapshot.getSnapshotsFromImageList;
import static projectMTDS.utils.Utils.logger;

public class GetImagesAPI extends API{
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();
        Authenticator authenticator = Authenticator.getInstance();

        logRequestData(request);

        String userId = authenticator.getUserFromSession(request.cookies());
        logger.info("get images received by user " + userId);
        return gson.toJson(getSnapshotsFromImageList(modelManager.getImagesByUser(userId)));
    }
}
