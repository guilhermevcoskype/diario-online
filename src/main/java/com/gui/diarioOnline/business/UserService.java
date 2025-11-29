package com.gui.diarioOnline.business;

import com.gui.diarioOnline.controller.dto.UserRequest;
import com.gui.diarioOnline.controller.dto.UserResponse;
import com.gui.diarioOnline.infra.entity.User;
import com.gui.diarioOnline.infra.exception.EmailAlreadyUsedException;
import com.gui.diarioOnline.infra.repository.UserRepository;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public List<UserResponse> getUsers(){
        return repository.findAll().stream().map(user -> new UserResponse(
                        user.getName(),
                        user.getEmail(),
                        user.getMedia(),
                        user.getRoles()
                ))
                .toList();
    }

    public void deleteUser(String email){
        repository.deleteByEmail(email);
    }

    public UserResponse createUser(UserRequest userRequest){
        User user = userRequest.toModel();
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        try {
            return repository.save(user).toResponse();
        } catch (DuplicateKeyException e) {
            throw new EmailAlreadyUsedException();
        }
    }
}
