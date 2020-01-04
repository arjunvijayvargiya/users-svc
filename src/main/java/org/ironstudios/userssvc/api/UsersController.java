package org.ironstudios.userssvc.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.ironstudios.userssvc.model.User;
import org.ironstudios.userssvc.model.UserResponse;
import org.ironstudios.userssvc.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class UsersController {

    UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponse> getAll(){
        return usersService.getAllUsers();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ApiOperation(value = "Add users to the application")
    @ApiResponses(value = {
            @ApiResponse( code = 200 , message = "user created successfully"),
            @ApiResponse( code = 400, message =  "user already exists"),
            @ApiResponse( code = 500, message = "internal server error")
    })
    public ResponseEntity<UserResponse> addUser(@RequestBody User user) {
        return usersService.addUser(user);
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete user to the application")
    @ApiResponses(value = {
            @ApiResponse( code = 200 , message = "user deleted successfully"),
            @ApiResponse( code = 204, message =  "user does not exist"),
            @ApiResponse( code = 500, message = "internal server error")
    })
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("username") String username) {
        return usersService.deleteUserByUserId(username);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ApiOperation(value = "Authenticate users to the application")
    @ApiResponses(value = {
            @ApiResponse( code = 200 , message = "user authenticated successfully"),
            @ApiResponse( code = 400, message =  "authentication failure"),
            @ApiResponse( code = 500, message = "internal server error")
    })
    public ResponseEntity<UserResponse> authenticateUser(@RequestBody User user) {
        return usersService.authenticateUser(user);
    }

}