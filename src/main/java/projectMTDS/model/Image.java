package projectMTDS.model;

public class Image {
    private String userId;
    private String name;
    private String imageId;
    private String extension;

    public Image(String imageId, String userId, String name, String extension){
        this.imageId = imageId;
        this.name = name;
        this.userId = userId;
        this.extension = extension;
    }

    public String getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getFileName(){
        return imageId + "." + extension;
    }
}
