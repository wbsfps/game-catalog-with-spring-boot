package br.com.wbs.modules.studio.useCases;

import br.com.wbs.exceptions.StudioNotFoundException;
import br.com.wbs.modules.studio.dto.StudioDetailsDTO;
import br.com.wbs.modules.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindByIdStudioUseCase {
    @Autowired
    private StudioRepository studioRepository;

    public StudioDetailsDTO execute(UUID id) {
        var studio = studioRepository.findById(id).orElseThrow(StudioNotFoundException::new);
        return new StudioDetailsDTO(studio);
    }
}
