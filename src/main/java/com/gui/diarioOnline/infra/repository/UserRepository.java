package com.gui.diarioOnline.infra.repository;

import com.gui.diarioOnline.infra.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}

