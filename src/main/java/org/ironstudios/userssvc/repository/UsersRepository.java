package org.ironstudios.userssvc.repository;

import org.ironstudios.userssvc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User,String> {
}