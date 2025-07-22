package br.com.wbs.modules.game.useCases;

import br.com.wbs.modules.game.dto.GameDetailsDTO;
import br.com.wbs.modules.game.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllGamesUseCase {

    @Autowired
    private GameRepository gameRepository;

    public List<GameDetailsDTO> execute() {
        var allGames = gameRepository.findAll();
        return allGames.stream().map(GameDetailsDTO::new).toList();
    }
}
