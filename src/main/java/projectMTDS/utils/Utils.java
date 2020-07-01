package projectMTDS.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectMTDS.controller.APIManager;

public class Utils {
    public static Logger logger = LoggerFactory.getLogger(APIManager.class);
    public static Gson gson = new Gson();
}
