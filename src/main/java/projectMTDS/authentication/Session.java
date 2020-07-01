package projectMTDS.authentication;

import java.time.Duration;
import java.util.Date;

public class Session {
    private String id;
    private String userId;
    private Date startDate;

    public Session(String sessionId, String userId){
        this.id = sessionId;
        this.userId = userId;
        this.startDate = new Date();
    }

    public boolean isExpired(){
        return new Date().toInstant().isAfter(startDate.toInstant().plus(Duration.ofHours(1)));
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
