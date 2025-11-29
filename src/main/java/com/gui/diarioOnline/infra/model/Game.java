package com.gui.diarioOnline.infra.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Game extends Media {


    private Long id;

    @Override
    public String getType(){
        return "GAME";
    };


}
