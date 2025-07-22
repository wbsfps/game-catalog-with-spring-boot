package br.com.wbs.modules.game.entity;

import br.com.wbs.modules.game.dto.GameDetailsDTO;
import br.com.wbs.modules.game.dto.GameRegisterDTO;
import br.com.wbs.modules.game.enums.Gender;
import br.com.wbs.modules.studio.entity.StudioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Size(min = 3, max = 100, message = "The name field needs to be filled in with between 3 and 100 characters.")
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @URL(message = "Must be a valid URL.")
    private String imgURL;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "studio_id", updatable = false, insertable = false)
    private StudioEntity studioEntity;

    @Column(name = "studio_id", nullable = false)
    private UUID studioId;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt;
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant updatedAt;

    public GameEntity(GameRegisterDTO dto) {
        this.name = dto.name();
        this.gender = dto.gender();
        this.imgURL = dto.imgURL();
        this.studioId = dto.idStudio();
    }

    public GameEntity(GameDetailsDTO dto) {
        this.id = dto.id();
        this.name = dto.name();
        this.gender = dto.gender();
        this.imgURL = dto.imgURL();
        this.createdAt = dto.createdAt();
        this.updatedAt = dto.updatedAt();
    }
}
