package com.gui.diarioOnline.infra.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

public abstract class Media {

    private String name;
    private String summary;
    private String cover;
    private Double rating;
    private String comments;

    public abstract String getType();
}
