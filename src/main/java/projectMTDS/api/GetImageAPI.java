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
        String userId = authenticator.getUserFromSession(request.cookies());
        if(userId == null) return invalidSession(response);

        String imageId = request.params(":id");

        Image image = modelManager.getImage(userId, imageId);
        if(image == null) return "Image does not exist";
        Path path = Paths.get(Config.IMAGE_FOLDER_DIRECTORY).resolve(preview ? image.getPreviewFileName() : image.getFileName());
        File file = path.toFile();

        if (file.exists()) {
            response.raw().setContentType("image/" + image.getExtension());
            try (OutputStream out = response.raw().getOutputStream()) {
                ImageIO.write(ImageIO.read(file), image.getExtension(), out);
                return createResponseBody(String.join("", Files.readAllLines(path)), relatedLinks(imageId));
            } catch (IOException e) {
                return "Exception occurred while reading file" + e.getMessage();
            }
        }
        return "File does not exist";
    }

    static private Map<String, String> relatedLinks(String id){
        Map<String, String> linkMap = new HashMap<>();
        addUrl(linkMap, "gallery", GALLERY_URL);
        addUrl(linkMap, "logout", LOGOUT_API_URL);
        addUrl(linkMap, "self", imageUrl(id));
        return linkMap;
    }
}
