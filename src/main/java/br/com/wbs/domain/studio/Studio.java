package br.com.wbs.domain.studio;

import br.com.wbs.domain.game.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_studio")
public class Studio implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Game> games = new HashSet<>();

    public Studio(){}
    public Studio(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public void addGame(Game game) {
        games.add(game);
        game.setStudio(this);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public Integer getTotalGames() {
        return games.size();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Studio studio)) return false;

        return Objects.equals(uuid, studio.uuid) && Objects.equals(name, studio.name) && Objects.equals(games, studio.games);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(uuid);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(games);
        return result;
    }
}
