package br.com.wbs.modules.studio.entity;

import br.com.wbs.modules.game.entity.GameEntity;
import br.com.wbs.modules.studio.dto.StudioDetailsDTO;
import br.com.wbs.modules.studio.dto.StudioRegisterDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "studio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 100, message = "The name field needs to be filled in with between 3 and 100 characters.")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "studioEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GameEntity> games = new HashSet<>();

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt;
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant updatedAt;

    public StudioEntity(StudioRegisterDTO dto) {
        this.name = dto.name();
    }

    public StudioEntity(StudioDetailsDTO dto) {
        this.name = dto.name();
        this.id = dto.id();
    }

    public void addGame(GameEntity game) {
        games.add(game);
    }

    public int getQuantityGames() {
        return games.size();
    }

}
