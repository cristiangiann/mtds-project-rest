package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.controller.Authenticator;
import spark.Request;
import spark.Response;

public class AddUserAPI extends API{
    public static String call(Request request, Response response) {
        Authenticator authenticator = Authenticator.getInstance();

        Gson gson = new Gson();
        logRequestData(request);
        String userId = getParameterFromBody(request.body(), "id");
        String userName = getParameterFromBody(request.body(), "name");
        String password = getParameterFromBody(request.body(), "password");

        if(emptyParameter(userId) || emptyParameter(userName)){
            response.status(400);
            return gson.toJson("User not created. Parameters are not valid.");
        }

        if(authenticator.addUser(userId, userName, password)) {
            response.status(201);
            return gson.toJson("New User added with ID " + userId + " and name " + userName);
        }

        response.status(409);
        return gson.toJson("User " + userId + " already exists.");
    }
}
