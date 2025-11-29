package com.gui.diarioOnline.controller;

import com.gui.diarioOnline.business.IGDBService;
import com.gui.diarioOnline.business.UserService;
import com.gui.diarioOnline.controller.dto.DetailedGameResponse;
import com.gui.diarioOnline.infra.model.Game;
import com.gui.diarioOnline.infra.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gamelist")
public class GamelistController {

    @Autowired
    private IGDBService igbdService;

    @Autowired
    private UserService userService;

    @PostMapping("/games")
    public ResponseEntity<List<Media>> gamesList(@RequestBody String gameResponse) {
        List<DetailedGameResponse> list = igbdService.consumeGamesList(gameResponse);
        List<Media> listMedia = new ArrayList<>();
        list.forEach(dataildGame -> {
            Game game = new Game();
            game.setId(dataildGame.id());
            game.setName(dataildGame.name());
            game.setSummary(dataildGame.summary());
            game.setCover(dataildGame.coverUrl());
            listMedia.add(game);
        });

        return ResponseEntity.ok(listMedia);
    }
}