package br.com.wbs.services.studio;

import br.com.wbs.domain.studio.Studio;
import br.com.wbs.domain.studio.StudioDTODetails;
import br.com.wbs.domain.studio.StudioDTORegister;
import br.com.wbs.domain.studio.StudioRepository;

import br.com.wbs.global.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudioServices {
    Logger logger = LoggerFactory.getLogger(StudioServices.class);

    @Autowired
    private StudioRepository repository;

    public StudioDTODetails create(StudioDTORegister dtoRegister) {
        logger.info("Creating a Studio!");
        Studio entity = new Studio();
        entity.setName(dtoRegister.getName());

        Studio savedStudio = repository.save(entity);

        StudioDTODetails dto = new StudioDTODetails();
        dto.setUuid(savedStudio.getUuid());
        dto.setName(savedStudio.getName());

        return dto;
    }

    public StudioDTODetails findById(UUID uuid) {
        logger.info("Find one Studio!");
        Studio entity = repository.findById(uuid).orElseThrow(() ->
                new ResourceNotFoundException("No records not found this ID"));

        return buildDTOStudioDetails(entity);
    }

    public List<StudioDTODetails> findAll() {
        logger.info("Find all Studios!");
        List<Studio> list = repository.findAll();
        return list.stream().map(this::buildDTOStudioDetails).toList();
    }

    private StudioDTODetails buildDTOStudioDetails(Studio studio) {
        StudioDTODetails dto = new StudioDTODetails();
        dto.setUuid(studio.getUuid());
        dto.setName(studio.getName());
        return dto;
    }
}
