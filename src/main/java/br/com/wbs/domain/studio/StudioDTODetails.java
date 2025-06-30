package br.com.wbs.domain.studio;

import br.com.wbs.domain.game.Game;
import br.com.wbs.domain.game.GameDTODetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class StudioDTODetails implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID uuid;
    private String name;

    private Set<GameDTODetails> games = new HashSet<>();

    public StudioDTODetails(){}
    public StudioDTODetails(Studio studio) {
        this.uuid = studio.getUuid();
        this.games = studio.getGames().stream()
                .map(GameDTODetails::new)
                .collect(Collectors.toSet());
        this.name = studio.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<GameDTODetails> getGames() {
        return games;
    }

    public Integer getTotalGames() {
        return games.size();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof StudioDTODetails that)) return false;

        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name) && Objects.equals(games, that.games);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(uuid);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(games);
        return result;
    }
}
