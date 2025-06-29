package br.com.wbs.domain.game;

import br.com.wbs.domain.game.enums.Gender;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class GameDTOUpdateData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID uuid;
    private String name;
    private String imgUrl;
    private Integer gender;

    public GameDTOUpdateData(){}
    public GameDTOUpdateData(UUID uuid, String name, String imgUrl, Integer gender) {
        this.uuid = uuid;
        this.name = name;
        this.imgUrl = imgUrl;
        this.gender = gender;
    }

    public UUID getUuid() {
        return uuid;
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

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof GameDTOUpdateData that)) return false;

        return Objects.equals(name, that.name) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(imgUrl);
        result = 31 * result + Objects.hashCode(gender);
        return result;
    }
}
