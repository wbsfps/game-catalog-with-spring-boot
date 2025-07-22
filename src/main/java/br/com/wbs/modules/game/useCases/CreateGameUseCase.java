package br.com.wbs.modules.game.useCases;

import br.com.wbs.exceptions.StudioNotFoundException;
import br.com.wbs.modules.game.dto.GameDetailsDTO;
import br.com.wbs.modules.game.dto.GameRegisterDTO;
import br.com.wbs.modules.game.entity.GameEntity;
import br.com.wbs.modules.game.repository.GameRepository;
import br.com.wbs.modules.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateGameUseCase {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StudioRepository studioRepository;

    public GameDetailsDTO execute(GameRegisterDTO gameRegisterDTO) {
        var studio = studioRepository.findById(gameRegisterDTO.idStudio()).orElseThrow(StudioNotFoundException::new);

        var game = new GameEntity(gameRegisterDTO, studio);
        studio.addGame(game);

        var gameSaved = gameRepository.save(game);
        studioRepository.save(studio);

        return new GameDetailsDTO(gameSaved);
    }
}
