package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import projectMTDS.model.Image;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import javax.naming.InvalidNameException;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static projectMTDS.controller.Config.IMAGE_FOLDER_DIRECTORY;
import static projectMTDS.utils.Utils.*;

public class AddImageAPI extends API{
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();
        Authenticator authenticator = Authenticator.getInstance();
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

        logRequestImageFormData(request);
        String userId = authenticator.getUserFromSession(request.cookies());
        if(userId == null) return invalidSession(response);
        try {
            Image image = gson.fromJson(request.raw().getParameter("image_properties"), Image.class);
            if (emptyParameter(userId) || emptyParameter(image.getName())) {
                response.status(400);
                return gson.toJson("Image not created. Username or Image name are not valid.");
            }

            String imageExtension = getImageExtension(request.raw().getPart("uploaded_image"));
            if(imageExtension == null) throw new InvalidNameException("Error - Unsupported media type");
            String imageId = modelManager.addImage(userId, image.getName(), imageExtension, request.raw().getPart("uploaded_image").getInputStream());

            if (imageId != null) {
                image = modelManager.getImage(imageId);
                createImagesFolder();
                logger.info("New Image added with name: " + image.getName() + " into repository of " + userId);
                response.status(201);
                return createResponseBody(relatedLinks(imageId));
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.status(415);
            return gson.toJson("Unsupported media type");
        }
        logger.info("Error - Error uploading image - return 400 status");
        response.status(400);
        return gson.toJson("Error uploading image");
    }

    private static void createImagesFolder() {
        File folder = new File(IMAGE_FOLDER_DIRECTORY);
        folder.mkdirs();
    }

    private static String getImageExtension(Part part) {
        String fileName = part.getSubmittedFileName();
        if(fileName == null) return null;
        String[] splitString = fileName.split("\\.");
        String extension = splitString[splitString.length - 1];
        return extension.equals("jpg") || extension.equals("png") ? extension : null;
    }

    static private Map<String, String> relatedLinks(String imageId){
        Map<String, String> linkMap = new HashMap<>();
        addSelfUrl(linkMap, IMAGES_API_URL);
        addUploadedImageUrl(linkMap, imageUrl(imageId));
        addImagesUrl(linkMap);
        addLogoutUrl(linkMap);
        return linkMap;
    }
}
