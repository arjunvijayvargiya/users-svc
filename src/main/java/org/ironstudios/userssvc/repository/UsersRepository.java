package org.ironstudios.userssvc.repository;

import org.ironstudios.userssvc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends MongoRepository<User,String> {
}