package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import projectMTDS.controller.Config;
import projectMTDS.model.Image;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

import static projectMTDS.utils.Utils.*;

public class GetImageAPI extends API{
    public static String call(Request request, Response response, boolean preview) {
        ModelManager modelManager = ModelManager.getInstance();
        Authenticator authenticator = Authenticator.getInstance();

        logRequestData(request);
        String loggedUserId = authenticator.getUserFromSession(request.cookies());
        if(loggedUserId == null) return invalidSession(response);

        String imageId = request.params(":id");

        Image image = modelManager.getImage(imageId);
        if(image == null) return resourceNotFound(response);
        if (!image.getUserId().equals(loggedUserId)) return unauthorized(response);
        Path path = Paths.get(Config.IMAGE_FOLDER_DIRECTORY).resolve(preview ? image.getPreviewFileName() : image.getFileName());
        File file = path.toFile();

        if (file.exists()) {
            response.raw().setContentType("image/" + image.getExtension());
            try (OutputStream out = response.raw().getOutputStream()) {
                ImageIO.write(ImageIO.read(file), image.getExtension(), out);
                return createResponseBody(String.join("", Files.readAllLines(path)), relatedLinks(imageId, 200, preview));
            } catch (IOException e) {
                return "Exception occurred while reading file" + e.getMessage();
            }
        }
        return "File does not exist";
    }

    static private Map<String, String> relatedLinks(String id, int status, boolean preview){
        Map<String, String> linkMap = new HashMap<>();
        addLogoutUrl(linkMap);
        addImagesUrl(linkMap);
        if(status != 200) return linkMap;
        if(preview){
            addSelfUrl(linkMap, imagePreviewUrl(id));
            addImageUrlById(linkMap, imageUrl(id));
            return linkMap;
        }
        addSelfUrl(linkMap, imageUrl(id));
        return linkMap;
    }
}
