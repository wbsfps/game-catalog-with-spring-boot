package br.com.wbs.services.game;

import br.com.wbs.domain.game.*;

import static br.com.wbs.mapper.ObjectMapper.parseObject;
import static br.com.wbs.mapper.ObjectMapper.parseListObjects;

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
    private GameRepository repository;

    public GameDTODetails findById(UUID id) {
        logger.info("Find one game");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records not found this ID"));
        return parseObject(entity, GameDTODetails.class);
    }

    public List<GameDTODetails> findAll() {
        logger.info("Find All games");
        return parseListObjects(repository.findAll(), GameDTODetails.class);
    }

    public GameDTODetails create(GameDTORegister gameRegister) {
        logger.info("Creating one person");
        Game entity = parseObject(gameRegister, Game.class);
        return parseObject(repository.save(entity), GameDTODetails.class);
    }

    public GameDTODetails update(GameDTOUpdateData dto) {
        logger.info("Updating a game");
        var entity = repository.findById(dto.getUuid()).orElseThrow(() ->
                new ResourceNotFoundException("No records not found this ID"));

        if (entity.getUuid() != null) {
            entity.setUuid(dto.getUuid());
        }

        if (entity.getName() != null && !entity.getName().isEmpty()) {
            entity.setName(dto.getName());
        }

        if (entity.getGender() != null) {
            entity.setGender(dto.getGender());
        }

        if (entity.getImgUrl() != null && !entity.getImgUrl().isEmpty()) {
            entity.setImgUrl(dto.getImgUrl());
        }

        return parseObject(repository.save(entity), GameDTODetails.class);
    }
}
