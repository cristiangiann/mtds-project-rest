package projectMTDS.controller.API;

import projectMTDS.controller.Authenticator;
import projectMTDS.controller.Config;
import projectMTDS.model.Image;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.*;

public class GetImageAPI extends API{
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();
        Authenticator authenticator = Authenticator.getInstance();

        logRequestData(request);
        String userId = authenticator.getUserFromSession(request.cookies());
        String imageId = request.params(":id");

        Image image = modelManager.getImage(userId, imageId);
        if(image == null) return "Image does not exist";
        Path path = Paths.get(Config.IMAGE_FOLDER_DIRECTORY).resolve(image.getFileName());
        File file = path.toFile();

        if (file.exists()) {
            response.raw().setContentType("image/" + image.getExtension());
            try (OutputStream out = response.raw().getOutputStream()) {
                ImageIO.write(ImageIO.read(file), image.getExtension(), out);
                return String.join("", Files.readAllLines(path));
            } catch (IOException e) {
                return "Exception occurred while reading file" + e.getMessage();
            }
        }
        return "File does not exist";
    }
}
