package br.com.wbs.modules.studio.dto;


import br.com.wbs.modules.studio.entity.StudioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudioRegisterDTO(
        @NotBlank
        @Size(min = 3, max = 100, message = "The name field needs to be filled in with between 3 and 100 characters.")
        @Schema(example = "Rockstar Games")
        String name
) {

    public StudioRegisterDTO(StudioEntity entity) {
        this(entity.getName());
    }
}
