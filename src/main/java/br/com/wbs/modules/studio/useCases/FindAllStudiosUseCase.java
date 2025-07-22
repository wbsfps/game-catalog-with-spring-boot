package br.com.wbs.modules.studio.useCases;

import br.com.wbs.modules.studio.dto.StudioDetailsDTO;
import br.com.wbs.modules.studio.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllStudiosUseCase {

    @Autowired
    private StudioRepository studioRepository;

    public List<StudioDetailsDTO> execute() {
        var allStudios = studioRepository.findAll();
        return allStudios.stream().map(StudioDetailsDTO::new).toList();
    }
}
