package projectMTDS.authentication;

import projectMTDS.model.ModelManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static spark.utils.StringUtils.isEmpty;

public class Authenticator {
    private static Authenticator authenticator = null;
    private ModelManager modelManager;
    private Map<String, Session> sessionMap;
    private Map<String, String> credentialsMap;

    private Authenticator(){
        modelManager = ModelManager.getInstance();
        sessionMap = new HashMap<>();
        credentialsMap = new HashMap<>();
    }

    public static Authenticator getInstance(){
        if(authenticator == null) authenticator = new Authenticator();
        return authenticator;
    }

    public String login(String userId, String password){
        if (userId == null || password == null) return null;
        if (modelManager.existUser(userId)) {
            if(checkPassword(userId, password)) {
                String sessionId = generateId();
                Session session = new Session(sessionId, userId);
                sessionMap.put(sessionId, session);
                return session.getId();
            }
        }
        return null;
    }

    private boolean logout(String sessionId){
        return sessionMap.remove(sessionId) != null;
    }

    public boolean logout(Map<String, String> cookies){
        return logout(cookies.get("sessionId"));
    }

    public boolean addUser(String id, String name, String password){
        if (isEmpty(id) || isEmpty(name) || isEmpty(password)) return false;
        if (!modelManager.existUser(id)) {
            modelManager.addUser(id, name);
            credentialsMap.put(id, getHash(password));
            return true;
        }
        return false;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getUserFromSession(Map<String, String> cookie) {
        String sessionId = cookie.get("sessionId");
        if (sessionId == null) return null;
        Session session = sessionMap.get(sessionId);
        return session == null ? null : session.getUserId();
    }

    private boolean checkPassword(String userId, String password){
        String hash = getHash(password);
        return credentialsMap.get(userId).equals(hash);
    }

    private String getHash(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return input;
        }
    }
}
