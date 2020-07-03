package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import projectMTDS.model.Image;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static projectMTDS.controller.Config.IMAGE_FOLDER_DIRECTORY;
import static projectMTDS.utils.Utils.gson;

public class AddImageAPI extends API{
    public static String call(Request request, Response response) throws IOException, ServletException {
        ModelManager modelManager = ModelManager.getInstance();
        Authenticator authenticator = Authenticator.getInstance();

        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        logRequestImageFormData(request);
        String userId = authenticator.getUserFromSession(request.cookies());

        Image image = gson.fromJson(request.raw().getParameter("image_properties"), Image.class);
        if(emptyParameter(userId) || emptyParameter(image.getName())){
            response.status(400);
            return gson.toJson("Image not created. Username or Image name are not valid.");
        }

        String imageExtension = getImageExtension(request.raw().getPart("uploaded_image"));
        String imageId = modelManager.addImage(userId, image.getName(), imageExtension);
        image = modelManager.getImage(userId, imageId);
        createImagesFolder();
        if(uploadImage(request, image)) {
            response.status(201);
            return gson.toJson("New Image added with name: " + image.getName() + " into repository of " + userId);
        } else {
            modelManager.deleteImage(userId, image.getImageId());
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
            saveResizedImage(targetFile, IMAGE_FOLDER_DIRECTORY + image.getPreviewFileName(), image);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static void saveResizedImage(File inputImageFile, String outputImagePath, Image image) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputImageFile);
        int scaledWidth;
        int scaledHeight;
        if(inputImage.getHeight() > inputImage.getWidth()) {
            scaledHeight = 300;
            scaledWidth =  (int)((double) inputImage.getWidth() / inputImage.getHeight() * 300);
        } else {
            scaledWidth = 300;
            scaledHeight = (int)((double) inputImage.getHeight() / inputImage.getWidth() * 300);
        }

        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        ImageIO.write(outputImage, image.getExtension(), new File(outputImagePath));
    }

    private static String getImageExtension(Part part) {
        String fileName = part.getSubmittedFileName();
        String[] splitString = fileName.split("\\.");
        return splitString[splitString.length - 1];
    }
}
