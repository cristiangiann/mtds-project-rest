package projectMTDS.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ModelManager {
    private HashMap<String, User> usersMap;
    private List<Image> imageList;

    public ModelManager(){
        usersMap = new HashMap<>();
        imageList = new ArrayList<>();
    }

    private User getUser(String username){
        return usersMap.get(username);
    }

    public List<String> getUsers(){
        return usersMap.values().stream().map(User::getName).collect(Collectors.toList());
    }

    public List<Image> getImagesByUser(String username){
        return imageList.stream().filter(image -> image.getUserId().equals(username)).collect(Collectors.toList());
    }

    public Image getImage(String username, String imageId){
        return getImagesByUser(username).stream()
                .filter(image -> image.getName().equals(imageId))
                .findFirst().orElse(null);
    }

    public void addUser(String username){
        if(getUser(username) == null) usersMap.put(username, new User(username));
    }

    public void addImage(String username, String imageName){
        imageList.add(new Image(username, imageName));
    }

    public void deleteImage(String username, String imageId){
        Image image = getImage(username, imageId);
        if(image == null) return;
        imageList.remove(image);
    }

    public boolean existUser(String username) {
        return usersMap.containsKey(username);
    }

    public boolean existImage(String username, String imageId) {
        return getImage(username, imageId) != null;
    }
}
