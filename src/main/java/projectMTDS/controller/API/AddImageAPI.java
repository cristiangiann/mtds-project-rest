package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.Image;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.*;

import static projectMTDS.controller.Config.IMAGE_FOLDER_DIRECTORY;

public class AddImageAPI extends API{
    public static String call(Request request, Response response, ModelManager modelManager) throws IOException, ServletException {
        Gson gson = new Gson();
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        logRequestImageFormData(request);

        Image image = gson.fromJson(request.raw().getParameter("image_properties"), Image.class);
        if(emptyParameter(image.getUserId()) || emptyParameter(image.getName())){
            response.status(400);
            return gson.toJson("Image not created. Username or Image name are not valid.");
        }

        String imageExtension = getImageExtension(request.raw().getPart("uploaded_image"));
        String imageId = modelManager.addImage(image.getUserId(), image.getName(), imageExtension);
        image = modelManager.getImage(image.getUserId(), imageId);
        createImagesFolder();
        if(uploadImage(request, image)) {
            response.status(201);
            return gson.toJson("New Image added with name: " + image.getName() + " into repository of " + image.getUserId());
        } else {
            modelManager.deleteImage(image.getUserId(), image.getImageId());
            return gson.toJson("Error uploading image");
        }
    }

    private static void createImagesFolder() {
        File folder = new File(IMAGE_FOLDER_DIRECTORY);
        folder.mkdirs();
    }

    private static boolean uploadImage(Request request, Image image) {
        try (InputStream is = request.raw().getPart("uploaded_image").getInputStream()) {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            File targetFile = new File(IMAGE_FOLDER_DIRECTORY + image.getFileName());
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            outStream.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static String getImageExtension(Part part) {
        String fileName = part.getSubmittedFileName();
        String[] splitString = fileName.split("\\.");
        return splitString[splitString.length - 1];
    }
}
