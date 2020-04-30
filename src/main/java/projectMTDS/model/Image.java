package projectMTDS.model;

public class Image {
    private String userId;
    private String name;

    public Image(String userId, String name){
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
