package projectMTDS.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ModelManager {
    private HashMap<String, User> usersMap;
    private HashMap<String, Image> imagesMap;
    private int lastImageId;

    public ModelManager(){
        usersMap = new HashMap<>();
        imagesMap = new HashMap<>();
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

    public Image getImage(String userId, String imageId){
        Image image = imagesMap.get(imageId);
        if(image != null && image.getUserId().equals(userId)) return image;
        return null;
    }

    public void addUser(String id, String name){
        //TODO: check values
        if(getUser(id) == null) usersMap.put(id, new User(id, name));
    }

    public String addImage(String userId, String imageName, String extension){
        Image image = new Image(createNewImageId(), userId, imageName, extension);
        imagesMap.put(image.getImageId(), image);
        return image.getImageId();
    }

    private String createNewImageId(){
        lastImageId++;
        return String.valueOf(lastImageId);
    }

    public void deleteImage(String userId, String imageId){
        Image image = getImage(userId, imageId);
        if(image == null) return;
        imagesMap.remove(imageId);
    }

    public boolean existUser(String userId) {
        return usersMap.containsKey(userId);
    }

    public boolean existImage(String userId, String imageId) {
        return getImage(userId, imageId) != null;
    }
}
