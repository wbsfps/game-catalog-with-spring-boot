package br.com.wbs.domain.game.enums;

public enum Gender {
    ACTION(1),
    ADVENTURE(2),
    COMEDY(3),
    DRAMA(4),
    FANTASY(5),
    HORROR(6),
    SPORT(7);

    private int code;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Gender valueOf(int code) {
        for (Gender value : Gender.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Gender code");
    }
}
