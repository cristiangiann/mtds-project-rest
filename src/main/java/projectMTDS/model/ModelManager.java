package projectMTDS.model;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static projectMTDS.controller.Config.IMAGE_FOLDER_DIRECTORY;

public class ModelManager {
    private static ModelManager modelManager = null;
    private HashMap<String, User> usersMap;
    private HashMap<String, Image> imagesMap;

    private ModelManager(){
        usersMap = new HashMap<>();
        imagesMap = new HashMap<>();
    }

    public static ModelManager getInstance(){
        if (modelManager == null) modelManager = new ModelManager();
        return modelManager;
    }

    private User getUser(String id){
        return usersMap.get(id);
    }

    public List<User> getUsers(){
        return new ArrayList<>(usersMap.values());
    }

    public List<Image> getImagesByUser(String userId){
        return imagesMap.values().stream().filter(image -> image.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public Image getImage(String imageId){
        return imagesMap.get(imageId);
    }

    public void addUser(String id, String name){
        if(getUser(id) == null) usersMap.put(id, new User(id, name));
    }

    public String addImage(String userId, String imageName, String extension, InputStream is){
        Image image = new Image(createNewImageId(), userId, imageName, extension);
        if(uploadImage(image, is)) {
            imagesMap.put(image.getImageId(), image);
            return image.getImageId();
        }
        return null;
    }

    private String createNewImageId(){
        return UUID.randomUUID().toString();
    }

    public void deleteImage(String imageId){
        Image image = getImage(imageId);
        if(image == null) return;

        File imageFile = new File(IMAGE_FOLDER_DIRECTORY + image.getFileName());
        imageFile.delete();
        File imagePreviewFile = new File(IMAGE_FOLDER_DIRECTORY + image.getPreviewFileName());
        imagePreviewFile.delete();
        imagesMap.remove(imageId);
    }

    private static boolean uploadImage(Image image, InputStream inputImage) {
        try (InputStream is = inputImage) {
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
        java.awt.image.BufferedImage inputImage = ImageIO.read(inputImageFile);
        int scaledWidth;
        int scaledHeight;
        if(inputImage.getHeight() > inputImage.getWidth()) {
            scaledHeight = 300;
            scaledWidth =  (int)((double) inputImage.getWidth() / inputImage.getHeight() * 300);
        } else {
            scaledWidth = 300;
            scaledHeight = (int)((double) inputImage.getHeight() / inputImage.getWidth() * 300);
        }

        java.awt.image.BufferedImage outputImage = new java.awt.image.BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
        java.awt.Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        ImageIO.write(outputImage, image.getExtension(), new File(outputImagePath));
    }

    public boolean existUser(String userId) {
        return usersMap.containsKey(userId);
    }

    public boolean existImage(String imageId) {
        return getImage(imageId) != null;
    }
}
