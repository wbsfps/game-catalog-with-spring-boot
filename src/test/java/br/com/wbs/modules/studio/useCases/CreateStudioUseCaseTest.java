package br.com.wbs.modules.studio.useCases;

import br.com.wbs.exceptions.StudioFoundException;
import br.com.wbs.modules.studio.dto.StudioRegisterDTO;
import br.com.wbs.modules.studio.entity.StudioEntity;
import br.com.wbs.modules.studio.repository.StudioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateStudioUseCaseTest {

    @Mock
    private StudioRepository studioRepository;

    @InjectMocks
    @Autowired
    private CreateStudioUseCase createStudioUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create studio successfully when everything is OK")
    void createStudioCase1() throws Exception {
        var studioRegisterDTO = new StudioRegisterDTO("STUDIO_TEST");

        when(studioRepository.findByName("STUDIO_TEST")).thenReturn(Optional.empty());

        when(studioRepository.save(any())).thenAnswer(invocation -> {
            var entity = new StudioEntity(studioRegisterDTO);
            entity.setId(UUID.randomUUID());
            return entity;
        });

        var result = createStudioUseCase.execute(studioRegisterDTO);

        assertNotNull(result);
        assertEquals("STUDIO_TEST", result.name());
        verify(studioRepository, times(1)).save(any());
        verify(studioRepository, times(1)).findByName("STUDIO_TEST");
    }

    @Test
    @DisplayName("Should throw StudioFoundException when studio already exists")
    void createStudioCase2() {
        var studioRegisterDTO = new StudioRegisterDTO("STUDIO_TEST");

        var existingStudio = new StudioEntity(studioRegisterDTO);
        when(studioRepository.findByName("STUDIO_TEST")).thenReturn(Optional.of(existingStudio));

        assertThrows(StudioFoundException.class, () -> {
            createStudioUseCase.execute(studioRegisterDTO);
        });

        verify(studioRepository, times(1)).findByName("STUDIO_TEST");
        verify(studioRepository, never()).save(any());
    }
}