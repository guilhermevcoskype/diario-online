package com.gui.diarioOnline.controller.dto;

public record DetailedGameResponse(
        Long id,
        String name,
        String summary,
        Long coverId,
        String coverUrl
) {}