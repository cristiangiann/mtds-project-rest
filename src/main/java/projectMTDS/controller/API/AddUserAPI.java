package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.*;
import spark.Request;
import spark.Response;

public class AddUserAPI extends API{
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        logRequestData(request);
        String userId = getUserFromBody(request).getId();
        String userName = getUserFromBody(request).getName();

        if(emptyParameter(userId) || emptyParameter(userName)){
            response.status(400);
            return gson.toJson("User not created. Inserted parameters are not valid.");
        }
        if(modelManager.existUser(userId)){
            response.status(409);
            return gson.toJson("User " + userId + " already exists.");
        }

        modelManager.addUser(userId, userName);
        response.status(201);
        return gson.toJson("New User added with ID " + userId + " and name " + userName);
    }
}
