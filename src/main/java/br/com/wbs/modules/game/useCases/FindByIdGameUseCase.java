package br.com.wbs.modules.game.useCases;

import br.com.wbs.exceptions.GameNotFoundException;
import br.com.wbs.exceptions.StudioNotFoundException;
import br.com.wbs.modules.game.dto.GameDetailsDTO;
import br.com.wbs.modules.game.repository.GameRepository;
import br.com.wbs.modules.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindByIdGameUseCase {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StudioRepository studioRepository;

    public GameDetailsDTO execute(UUID idGame, UUID idStudio) {
        studioRepository.findById(idStudio).orElseThrow(StudioNotFoundException::new);
        var game = gameRepository.findById(idGame).orElseThrow(GameNotFoundException::new);
        return new GameDetailsDTO(game);
    }
}
