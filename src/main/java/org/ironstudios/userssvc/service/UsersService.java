package org.ironstudios.userssvc.service;

import org.ironstudios.userssvc.model.User;
import org.ironstudios.userssvc.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
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

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }

    public ResponseEntity<String> addUser(User user){
        Optional<User> existingUserOptional = usersRepository.findById(user.getUserName());
        if(existingUserOptional.isEmpty()){
            String hash = null;
            try {
                hash = encryptionService.getPasswordHash(user.getPassword());
            } catch (GeneralSecurityException e) {
                LOG.error(e.getMessage());
                new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            user.setPassword(hash);
            usersRepository.save(user);
            return new ResponseEntity<>("user created successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("user already exist", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> authenticateUser(User user){
        Optional<User> existingUserOptional = usersRepository.findById(user.getUserName());
        if(existingUserOptional.isPresent()){
            String hash = null;
            try {
                hash = encryptionService.getPasswordHash(user.getPassword());
            } catch (GeneralSecurityException e) {
                LOG.error(e.getMessage());
                new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if(existingUserOptional.get().getPassword().equals(hash)){
                return new ResponseEntity<>("user authenticated successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("incorrect username or password. Please retry with different username or password"
                        , HttpStatus.BAD_REQUEST);
            }

        }
        else{
            return new ResponseEntity<>("username doesn't exist. Please retry with different username or password"
                    , HttpStatus.BAD_REQUEST);
        }
    }
}
