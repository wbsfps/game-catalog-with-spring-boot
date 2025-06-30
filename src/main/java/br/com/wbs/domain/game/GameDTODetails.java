package br.com.wbs.domain.game;

import br.com.wbs.domain.game.enums.Gender;
import br.com.wbs.domain.studio.Studio;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class GameDTODetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID uuid;
    private String name;
    private String imgUrl;
    private Integer gender;

    private UUID uuidStudio;

    public GameDTODetails(){}

    public GameDTODetails(Game game) {
        this.uuid = game.getUuid();
        this.name = game.getName();
        this.imgUrl = game.getImgUrl();
        this.gender = game.getGender().getCode();
        this.uuidStudio = game.getStudio().getUuid();
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Gender getGender() {
        return Gender.valueOf(gender);
    }

    public void setGender(Gender gender) {
        if (gender != null) {
            this.gender = gender.getCode();
        }
    }

    public UUID getUuidStudio() {
        return uuidStudio;
    }

    public void setUuidStudio(UUID uuidStudio) {
        this.uuidStudio = uuidStudio;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof GameDTODetails that)) return false;

        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(gender, that.gender) && Objects.equals(uuidStudio, that.uuidStudio);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(uuid);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(imgUrl);
        result = 31 * result + Objects.hashCode(gender);
        result = 31 * result + Objects.hashCode(uuidStudio);
        return result;
    }
}
