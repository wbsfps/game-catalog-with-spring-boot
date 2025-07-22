package br.com.wbs.modules.game.enums;

import lombok.Getter;

@Getter
public enum Gender {
    ACTION(1),
    ADVENTURE(2),
    RPG(3), // Role-Playing Game
    STRATEGY(4),
    SHOOTER(5),
    PUZZLE(6),
    PLATFORMER(7),
    SIMULATION(8),
    SPORTS(9),
    RACING(10),
    FIGHTING(11),
    HORROR(12),
    SURVIVAL(13),
    SANDBOX(14),
    MMO(15), // Massively Multiplayer Online
    STEALTH(16),
    RHYTHM(17),
    PARTY(18),
    CARD(19),
    TACTICAL(20),
    OPEN_WORLD(21),
    TURN_BASED(22),
    REAL_TIME_STRATEGY(23),
    ROGUELIKE(24),
    METROIDVANIA(25),
    VISUAL_NOVEL(26);

    private int code;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public Gender valuesOf(int code) {
        for (Gender value : Gender.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid Gender code!");
    }
}
