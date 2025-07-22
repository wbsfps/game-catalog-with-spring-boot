package br.com.wbs.modules.game.dto;

import br.com.wbs.modules.game.entity.GameEntity;
import br.com.wbs.modules.game.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.UUID;

public record GameDetailsDTO(
        UUID id,
        String name,
        Gender gender,
        String imgURL,
        UUID idStudio,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant updatedAt
) {

    public GameDetailsDTO(GameEntity entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getGender(),
                entity.getImgURL(),
                entity.getStudioId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
