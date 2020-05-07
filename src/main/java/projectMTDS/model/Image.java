package projectMTDS.model;

public class Image {
    private String userId;
    private String name;
    private String id;
    private String extension;

    public Image(String id, String userId, String name, String extension){
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.extension = extension;
    }

    public String getImageId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getFileName(){
        return id + "." + extension;
    }

    public String getExtension() {
        return extension;
    }
}
