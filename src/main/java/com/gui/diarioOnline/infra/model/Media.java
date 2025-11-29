package com.gui.diarioOnline.infra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class Media {

    private String name;
    private String summary;
    private String cover;
    private Double rating;
    private String comments;

    public abstract String getType();
}
