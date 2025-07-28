package br.com.wbs.modules.game.useCases;

import br.com.wbs.exceptions.StudioNotFoundException;
import br.com.wbs.modules.game.dto.GameRegisterDTO;
import br.com.wbs.modules.game.entity.GameEntity;
import br.com.wbs.modules.game.enums.Gender;
import br.com.wbs.modules.game.repository.GameRepository;
import br.com.wbs.modules.studio.entity.StudioEntity;
import br.com.wbs.modules.studio.repository.StudioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateGameUseCaseTest {

    @InjectMocks
    private CreateGameUseCase createGameUseCase;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private StudioRepository studioRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldCreateGameSuccessfully() {
        var studio = new StudioEntity(UUID.randomUUID(), "STUDIO_TEST", new HashSet<>(), Instant.now(), Instant.now());
        var gameRegisterDTO = new GameRegisterDTO(
                "GAME_TEST", Gender.ACTION, "https://rockstar.com/gta.png", studio.getId());


        when(studioRepository.findById(studio.getId())).thenReturn(Optional.of(studio));
        when(gameRepository.findByName("GAME_TEST")).thenReturn(Optional.empty());

        when(gameRepository.save(any())).thenAnswer(invocation -> {
            var game = new GameEntity(gameRegisterDTO, studio);
            game.setId(UUID.randomUUID());
            return game;
        });

        var result = createGameUseCase.execute(gameRegisterDTO);

        assertNotNull(result);
        verify(gameRepository, times(1)).save(any());
        verify(studioRepository, times(1)).save(studio);
        assertEquals(1, studio.getGames().size());
    }

    @Test
    void noCreateGameIfStudioNotExists() {
        var gameRegisterDTO = new GameRegisterDTO(
                "GAME_TEST", Gender.ACTION, "https://rockstar.com/gta.png", null);

        when(gameRepository.findByName("GAME_TEST")).thenReturn(Optional.empty());

        when(gameRepository.save(any())).thenAnswer(invocation -> {
            var game = new GameEntity(gameRegisterDTO, null);
            game.setId(UUID.randomUUID());
            return game;
        });

        try {
            createGameUseCase.execute(gameRegisterDTO);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(StudioNotFoundException.class);
        }
    }
}