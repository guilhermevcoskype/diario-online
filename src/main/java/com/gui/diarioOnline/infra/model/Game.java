package com.gui.diarioOnline.infra.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Game extends Media {

    private Long id;

    @Override
    public String getType(){
        return "GAME";
    };


}
