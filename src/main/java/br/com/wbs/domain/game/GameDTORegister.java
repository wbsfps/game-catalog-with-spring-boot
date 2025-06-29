package br.com.wbs.domain.game;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class GameDTORegister implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String imgUrl;
    private Integer gender;

    public GameDTORegister(){}

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof GameDTORegister that)) return false;

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
