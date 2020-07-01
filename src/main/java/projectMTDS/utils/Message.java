package projectMTDS.utils;

public class Message {
    private String url;
    private String message;

    public Message(String message){
        this.message = message;
    }

    public Message(String message, String url){
        this.message = message;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }
}
