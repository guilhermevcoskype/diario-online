package com.gui.diarioOnline.business;

import com.gui.diarioOnline.controller.dto.SaveGameRequest;
import com.gui.diarioOnline.infra.entity.User;
import com.gui.diarioOnline.infra.exception.MediaAlreadyExistsException;
import com.gui.diarioOnline.infra.exception.UserNotFoundException;
import com.gui.diarioOnline.infra.model.Game;
import com.gui.diarioOnline.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class GameService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveGameUser(SaveGameRequest saveGameRequest){
        User user = userRepository.findByEmail(saveGameRequest.email()).orElseThrow(UserNotFoundException::new);
        if (user.getMedia() == null) {
            user.setMedia(new ArrayList<>());
        }else{
            // VERIFICAÇÃO DE UNICIDADE MANUAL AQUI:
            boolean alreadyExists = user.getMedia().stream()
                    .anyMatch(mediaItem -> mediaItem instanceof Game && ((Game) mediaItem).equals(saveGameRequest.toModel()));

            if (alreadyExists) {
                throw new MediaAlreadyExistsException("O jogo com ID " + saveGameRequest.toModel().getId() + " já foi adicionado a este usuário.");
            }

            user.getMedia().add(saveGameRequest.toModel());
            userRepository.save(user);
        }
    }
}