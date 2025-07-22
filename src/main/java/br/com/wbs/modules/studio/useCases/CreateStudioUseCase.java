package br.com.wbs.modules.studio.useCases;

import br.com.wbs.exceptions.StudioFoundException;
import br.com.wbs.modules.studio.dto.StudioDetailsDTO;
import br.com.wbs.modules.studio.dto.StudioRegisterDTO;
import br.com.wbs.modules.studio.entity.StudioEntity;
import br.com.wbs.modules.studio.repository.StudioRepository;
import br.com.wbs.modules.studio.utils.StudioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateStudioUseCase {

    @Autowired
    private StudioRepository studioRepository;

    public StudioDetailsDTO execute(StudioRegisterDTO studioRegisterDTO) {
        studioRepository.findByName(studioRegisterDTO.name()).ifPresent((studio) -> {
            throw new StudioFoundException();
        });

        var entity = StudioMapper.toEntity(studioRegisterDTO);
        studioRepository.save(entity);

        return StudioMapper.toDTO(entity);
    }
}
