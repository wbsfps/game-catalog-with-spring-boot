package br.com.wbs.modules.studio.utils;

import br.com.wbs.modules.studio.dto.StudioDetailsDTO;
import br.com.wbs.modules.studio.dto.StudioRegisterDTO;
import br.com.wbs.modules.studio.entity.StudioEntity;

public class StudioMapper {

    public static StudioEntity toEntity(StudioRegisterDTO dto) {
        return new StudioEntity(dto);
    }

    public static StudioDetailsDTO toDTO(StudioEntity entity) {
        return new StudioDetailsDTO(entity);
    }
}
