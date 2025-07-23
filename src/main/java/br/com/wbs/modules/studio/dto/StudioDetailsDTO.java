package br.com.wbs.modules.studio.dto;

import br.com.wbs.modules.game.entity.GameEntity;
import br.com.wbs.modules.studio.entity.StudioEntity;

import java.util.Set;
import java.util.UUID;

public record StudioDetailsDTO(UUID id, String name, Integer quantityGames, Set<GameEntity> games) {

    public StudioDetailsDTO(StudioEntity entity) {
        this(entity.getId(), entity.getName(), entity.getQuantityGames(), entity.getGames());
    }
}
