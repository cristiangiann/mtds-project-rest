package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import projectMTDS.utils.Message;
import spark.Request;
import spark.Response;

import static projectMTDS.utils.Utils.gson;
import static projectMTDS.utils.Utils.logger;

public class AddUserAPI extends API{
    public static String call(Request request, Response response) {
        Authenticator authenticator = Authenticator.getInstance();

        logRequestData(request);
        String userId = getParameterFromBody(request.body(), "id");
        String userName = getParameterFromBody(request.body(), "name");
        String password = getParameterFromBody(request.body(), "password");

        if(emptyParameter(userId) || emptyParameter(userName) || emptyParameter(password)){
            logger.info("User not created - Invalid parameters - " +
                    "userId: " + userId + ", userName: " + userName + ", password: " + password);
            response.status(400);
            return gson.toJson("User not created. Parameters are not valid.");
        }

        if(authenticator.addUser(userId, userName, password)) {
            logger.info("New user added - userId: " + userId + ", userName: " + userName);
            response.status(201);
            return gson.toJson(new Message("New User added with ID " + userId + " and name " + userName, "/"));
        }

        logger.info("User not created - User with ID " + userId + " already exists");
        response.status(409);
        return gson.toJson("User " + userId + " already exists.");
    }
}
