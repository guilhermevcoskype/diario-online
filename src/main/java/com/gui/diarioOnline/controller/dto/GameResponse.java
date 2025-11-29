package com.gui.diarioOnline.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GameResponse(
        Long id,
        String name,
        String summary,
        Long cover
) {}

