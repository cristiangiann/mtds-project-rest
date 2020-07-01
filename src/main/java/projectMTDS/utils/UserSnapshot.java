package projectMTDS.utils;

import projectMTDS.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserSnapshot {
    private String id;
    private String name;
    private String url;

    public UserSnapshot(User user){
        this(user.getId(), user.getName());
    }

    public UserSnapshot(String id, String name){
        this(id, name, "/users/" + id);
    }

    public UserSnapshot(String id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static List<UserSnapshot> getSnapshotsFromUserList(List<User> userList){
        List<UserSnapshot> snapshots = new ArrayList<>();
        for (User user: userList) {
            snapshots.add(new UserSnapshot(user));
        }
        return snapshots;
    }
}
