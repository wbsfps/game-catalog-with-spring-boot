package br.com.wbs.modules.studio.dto;

import br.com.wbs.modules.studio.entity.StudioEntity;

import java.util.UUID;

public record StudioDetailsDTO(UUID id, String name, Integer quantityGames) {

    public StudioDetailsDTO(StudioEntity entity) {
        this(entity.getId(), entity.getName(), entity.getQuantityGames());
    }
}
