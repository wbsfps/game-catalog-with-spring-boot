package br.com.wbs.domain.studio;

import br.com.wbs.domain.game.Game;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class StudioDTORegister implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private Set<Game> games = new HashSet<>();

    public StudioDTORegister(){}
    public StudioDTORegister(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof StudioDTORegister that)) return false;

        return Objects.equals(name, that.name) && Objects.equals(games, that.games);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(games);
        return result;
    }
}
