package org.ironstudios.userssvc.service;

import org.ironstudios.userssvc.model.User;
import org.ironstudios.userssvc.model.UserResponse;
import org.ironstudios.userssvc.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);
    private UsersRepository usersRepository;
    private EncryptionService encryptionService;

    @Autowired
    public UsersService(UsersRepository usersRepository,EncryptionService encryptionService) {
        this.usersRepository = usersRepository;
        this.encryptionService = encryptionService;
    }

    public ResponseEntity<UserResponse> getAllUsers(){
        List<User> users = usersRepository.findAll();
        if(users.isEmpty())
            return new ResponseEntity<>(new UserResponse<>(204, "no users exist"),HttpStatus.NO_CONTENT);
        return new ResponseEntity<UserResponse>(new UserResponse(200, users), HttpStatus.OK);
    }

    public ResponseEntity<UserResponse> addUser(User user){
        Optional<User> existingUserOptional = usersRepository.findById(user.getUserName());
        if(existingUserOptional.isEmpty()){
            String hash = null;
            try {
                hash = encryptionService.getPasswordHash(user.getPassword());
            } catch (Exception e) {
                LOG.error(e.getMessage());
                return new ResponseEntity<UserResponse>(new UserResponse(500, "internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            user.setPassword(hash);
            usersRepository.save(user);
            return new ResponseEntity<UserResponse>(new UserResponse(200, "user created successfully"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<UserResponse>(new UserResponse(400, "user already exist"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UserResponse> authenticateUser(User user){
        Optional<User> existingUserOptional = usersRepository.findById(user.getUserName());
        if(existingUserOptional.isPresent()){

            if(existingUserOptional.get().getPassword().equals(encryptionService.getPasswordHash(user.getPassword()))){
                return new ResponseEntity<UserResponse>(new UserResponse(200,"user authenticated successfully"), HttpStatus.OK);
            }else{
                return new ResponseEntity<UserResponse>(new UserResponse(400,"incorrect username or password.")
                        , HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<UserResponse>(new UserResponse(400,"username doesn't exist"
            ), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UserResponse> deleteUserByUserId(String id){
        Optional<User> existingUserOptional = usersRepository.findById(id);
        if(existingUserOptional.isPresent()){
            usersRepository.deleteById(id);
            return new ResponseEntity<UserResponse>(new UserResponse(200,"user deleted successfully"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<UserResponse>(new UserResponse(204,"username doesn't exist."
            ), HttpStatus.BAD_REQUEST);
        }
    }
}
