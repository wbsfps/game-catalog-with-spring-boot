package br.com.wbs.services.game;

import br.com.wbs.domain.game.*;

import br.com.wbs.domain.studio.Studio;
import br.com.wbs.domain.studio.StudioRepository;
import br.com.wbs.global.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameServices {

    private Logger logger = LoggerFactory.getLogger(GameServices.class.getName());

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StudioRepository studioRepository;

    public GameDTODetails findById(UUID id) {
        logger.info("Find one game");
        Game entity = gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records not found this ID"));
        return buildDTOGameDetails(entity);
    }

    public List<GameDTODetails> findAll() {
        logger.info("Find All games");
        List<Game> games = gameRepository.findAll();
        return games.stream().map(this::buildDTOGameDetails).toList();
    }

    public GameDTODetails create(GameDTORegister gameRegister) {
        logger.info("Creating one game");
        Studio studio = instatiateStudio(gameRegister);

        Game game = new Game();
        game.setName(gameRegister.getName());
        game.setGender(gameRegister.getGender());
        game.setImgUrl(gameRegister.getImgUrl());
        game.setStudio(studio);

        studio.addGame(game);

        Game savedGame = gameRepository.save(game);

        GameDTODetails dtoDetails = new GameDTODetails();
        dtoDetails.setUuid(savedGame.getUuid());
        dtoDetails.setName(savedGame.getName());
        dtoDetails.setGender(savedGame.getGender());
        dtoDetails.setImgUrl(savedGame.getImgUrl());
        dtoDetails.setUuidStudio(savedGame.getStudio().getUuid());


        return dtoDetails;
    }

    public GameDTODetails update(GameDTOUpdateData dto) {
        logger.info("Updating a game");
        var entity = gameRepository.findById(dto.getUuid()).orElseThrow(() ->
                new ResourceNotFoundException("No records not found this ID"));

        if (dto.getName() != null && !dto.getName().isEmpty()) {
            entity.setName(dto.getName());
        }

        if (dto.getGender() != null) {
            entity.setGender(dto.getGender());
        }

        if (dto.getImgUrl() != null && !dto.getImgUrl().isEmpty()) {
            entity.setImgUrl(dto.getImgUrl());
        }

        return buildDTOGameDetails(gameRepository.save(entity));
    }

    private Studio instatiateStudio(GameDTORegister dto) {
        return studioRepository.findById(dto.getUuidStudio()).orElseThrow(() ->
                new ResourceNotFoundException("No records not found this ID"));
    }

    private GameDTODetails buildDTOGameDetails(Game game) {
        GameDTODetails dto = new GameDTODetails();
        dto.setUuid(game.getUuid());
        dto.setName(game.getName());
        dto.setGender(game.getGender());
        dto.setImgUrl(game.getImgUrl());
        dto.setUuidStudio(game.getStudio().getUuid());
        return dto;
    }
}
