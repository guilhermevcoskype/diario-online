package com.gui.diarioOnline.controller.dto;

import com.gui.diarioOnline.infra.model.Media;
import com.gui.diarioOnline.infra.model.Role;

import java.util.List;

public record UserResponse(

        String name,

        String email,

        List<Media>media,

        List<Role> roles
) {
}
