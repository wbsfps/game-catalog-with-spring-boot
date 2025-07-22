package br.com.wbs.modules.game.dto;

import br.com.wbs.modules.game.entity.GameEntity;
import br.com.wbs.modules.game.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

public record GameRegisterDTO(
        @NotBlank
        @Size(min = 3, max = 100, message = "The name field needs to be filled in with between 3 and 100 characters.")
        String name,
        Gender gender,
        @URL(message = "Must be a valid URL.")
        String imgURL,
        @NotNull
        UUID idStudio
) {

    public GameRegisterDTO(GameEntity entity) {
        this(entity.getName(), entity.getGender(), entity.getImgURL(), entity.getStudio().getId());
    }
}
