package br.com.wbs.domain.game;

import br.com.wbs.domain.game.enums.Gender;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_game")
public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;
    private String imgUrl;
    private Integer gender;

    public Game(){}
    public Game(UUID uuid, String name, String imgUrl, Gender gender) {
        this.uuid = uuid;
        this.name = name;
        this.imgUrl = imgUrl;
        setGender(gender);
    }

    public Game(GameDTODetails gameDTODetails) {
        this.uuid = gameDTODetails.getUuid();
        this.name = gameDTODetails.getName();
        this.imgUrl = gameDTODetails.getImgUrl();
        this.gender = gameDTODetails.getGender();
    }

    public Game(GameDTORegister gameDTORegister) {
        this.name = gameDTORegister.getName();
        this.imgUrl = gameDTORegister.getImgUrl();
        this.gender = gameDTORegister.getGender();
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

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Game game)) return false;

        return Objects.equals(uuid, game.uuid) && Objects.equals(name, game.name) && Objects.equals(imgUrl, game.imgUrl) && Objects.equals(gender, game.gender);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(uuid);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(imgUrl);
        result = 31 * result + Objects.hashCode(gender);
        return result;
    }
}
