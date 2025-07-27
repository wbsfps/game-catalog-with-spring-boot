package br.com.wbs.modules.studio.repository;

import br.com.wbs.modules.studio.dto.StudioRegisterDTO;
import br.com.wbs.modules.studio.entity.StudioEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudioRepositoryTest {

    @Autowired
    private StudioRepository studioRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Should get Studio successfully from DB")
    void findByNameCase1() {
        var data = new StudioRegisterDTO("STUDIO_TEST");
        this.createStudio(data);

        Optional<StudioEntity> result = studioRepository.findByName(data.name());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get Studio from DB when Studio not exists")
    void findByNameCase2() {
        var data = new StudioRegisterDTO("STUDIO_TEST");
        Optional<StudioEntity> result = studioRepository.findByName(data.name());

        assertThat(result.isEmpty()).isTrue();
    }

    private StudioEntity createStudio(StudioRegisterDTO data) {
        StudioEntity newStudio = new StudioEntity(data);
        entityManager.persist(newStudio);
        return newStudio;
    }
}
