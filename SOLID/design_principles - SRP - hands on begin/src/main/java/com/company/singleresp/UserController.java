package com.company.singleresp;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

//Handles incoming JSON requests that work on User resource/entity
public class UserController {
	//Store used by controller
    private UserPersistenceService persistenceService = new UserPersistenceService();
    
    //Create a new user
    public String createUser(String userJson) throws IOException {

        UserRequestParser parser = new UserRequestParser();
        User user = parser.parseUser(userJson);
        UserValidator userValidator = new UserValidator();

        if(!userValidator.isValid(user)) {
            return "ERROR";
        }

        persistenceService.save(user);
        
        return "SUCCESS";
    }

}