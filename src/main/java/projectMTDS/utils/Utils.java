package projectMTDS.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectMTDS.controller.APIManager;

public class Utils {
    public static Logger logger = LoggerFactory.getLogger(APIManager.class);
    public static Gson gson = new Gson();

    public static final String LOGIN_API_URL = "/login";
    public static final String LOGOUT_API_URL = "/logout";
    public static final String IMAGES_API_URL = "/images";
    public static final String IMAGE_API_URL = "/images/{id}";
    public static final String PREVIEW_API_URL = "/images/{id}/preview";
    public static final String USERS_API = "/users";

    public static final String HOME_PAGE_URL = "/";
    public static final String GALLERY_URL = "/pages/gallery.html";
    public static final String SIGN_UP_URL = "/pages/signup.html";

    public static String imagePreviewUrl(String id){
        return "/images/" + id + "/preview";
    }

    public static String imageUrl(String id){
        return "/images/" + id;
    }
}
