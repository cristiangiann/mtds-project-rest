package projectMTDS.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ModelManager {
    private HashMap<String, User> usersMap;
    private List<Image> imageList;
    private int lastImageId;

    public ModelManager(){
        usersMap = new HashMap<>();
        imageList = new ArrayList<>();
    }

    private User getUser(String id){
        return usersMap.get(id);
    }

    public List<User> getUsers(){
        return new ArrayList<>(usersMap.values());
    }

    public List<Image> getImagesByUser(String userId){
        return imageList.stream().filter(image -> image.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public Image getImage(String userId, String imageId){
        return getImagesByUser(userId).stream()
                .filter(image -> image.getImageId().equals(imageId))
                .findFirst().orElse(null);
    }

    public void addUser(String id, String name){
        //TODO: check values
        if(getUser(id) == null) usersMap.put(id, new User(id, name));
    }

    public String addImage(String userId, String imageName, String extension){
        Image image = new Image(createNewImageId(), userId, imageName, extension);
        imageList.add(image);
        return image.getImageId();
    }

    private String createNewImageId(){
        lastImageId++;
        return String.valueOf(lastImageId);
    }

    public void deleteImage(String userId, String imageId){
        Image image = getImage(userId, imageId);
        if(image == null) return;
        imageList.remove(image);
    }

    public boolean existUser(String userId) {
        return usersMap.containsKey(userId);
    }

    public boolean existImage(String userId, String imageId) {
        return getImage(userId, imageId) != null;
    }
}
