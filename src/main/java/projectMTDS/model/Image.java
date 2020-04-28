package projectMTDS.model;

class Image {
    private String userId;
    private String name;

    Image(String userId, String name){
        this.name = name;
        this.userId = userId;
    }

    String getName() {
        return name;
    }

    String getUserId() {
        return userId;
    }
}
