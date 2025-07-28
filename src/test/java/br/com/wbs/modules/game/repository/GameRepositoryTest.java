package br.com.wbs.modules.game.repository;

import br.com.wbs.modules.game.dto.GameRegisterDTO;
import br.com.wbs.modules.game.entity.GameEntity;
import br.com.wbs.modules.game.enums.Gender;
import br.com.wbs.modules.studio.entity.StudioEntity;
import br.com.wbs.modules.studio.repository.StudioRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StudioRepository studioRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Should get Game successfully from DB")
    void createGameCase1() {
        var gameRegisterDTO = new GameRegisterDTO(
                "GAME_TEST,", Gender.ACTION, "https://rockstar.com/gta.png", UUID.randomUUID());
        createGame(gameRegisterDTO);

        Optional<GameEntity> result = gameRepository.findByName(gameRegisterDTO.name());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get Studio from DB when Studio not exists")
    void createGameCase2() {
        var gameRegisterDTO = new GameRegisterDTO(
                "GAME_TEST,", Gender.ACTION, "https://rockstar.com/gta.png", UUID.randomUUID());

        Optional<GameEntity> result = gameRepository.findByName(gameRegisterDTO.name());

        assertThat(result.isEmpty()).isTrue();
    }


    private GameEntity createGame(GameRegisterDTO data) {
        var studio = new StudioEntity();
        studio.setName("STUDIO_TEST");
        studio = studioRepository.save(studio);

        var newGame = new GameEntity(data, studio);
        entityManager.persist(newGame);
        return newGame;
    }
}