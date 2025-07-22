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
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "game")
@Getter
@Setter
@EqualsAndHashCode(exclude = "studio")
@ToString(exclude = "studio")
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
    @JoinColumn(name = "studio_id", nullable = false)
    private StudioEntity studio;


    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt;
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant updatedAt;

    public GameEntity(GameRegisterDTO dto, StudioEntity studio) {
        this.name = dto.name();
        this.gender = dto.gender();
        this.imgURL = dto.imgURL();
        this.studio = studio;
    }

    public GameEntity(GameDetailsDTO dto, StudioEntity studio) {
        this.id = dto.id();
        this.name = dto.name();
        this.gender = dto.gender();
        this.imgURL = dto.imgURL();
        this.createdAt = dto.createdAt();
        this.updatedAt = dto.updatedAt();
        this.studio = studio;
    }
}
