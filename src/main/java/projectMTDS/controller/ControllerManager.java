package projectMTDS.controller;

import com.google.gson.Gson;
import projectMTDS.model.ModelManager;
import spark.utils.StringUtils;

class ControllerManager {
    private ModelManager model;
    private Gson gson;

    ControllerManager(){
        model = new ModelManager();
        gson = new Gson();
    }

    String getImages(String username){
        return gson.toJson(model.getImagesByUser(username));
    }

    String getImage(String imageId, String username){
        if (StringUtils.isEmpty(username)) return "Invalid username";
        if (StringUtils.isEmpty(imageId)) return "Invalid image id";

        return gson.toJson(model.getImage(username, imageId));
    }

    String deleteImage(String imageId, String username){
        if(StringUtils.isEmpty(username)) return "Invalid username";
        if(StringUtils.isEmpty(imageId)) return "Invalid image id";

        model.deleteImage(username, imageId);
        return gson.toJson("Image deleted: " + imageId);
    }

    String newImage(String imageId, String username){
        if (StringUtils.isEmpty(username)) return "Invalid username";
        if (StringUtils.isEmpty(imageId)) return "Invalid image id";

        model.addImage(username, imageId);
        return gson.toJson("New image with name: " + imageId + " added to " + username + " repo");
    }

    String newUser(String username) {
        if (StringUtils.isEmpty(username)) return "Invalid username";
        if (model.existUser(username)) return "username " + username + " does exist";

        model.addUser(username);
        return gson.toJson("New User added with name: " + username);
    }

    String getUsers() {
        return gson.toJson(model.getUsers());
    }
}
