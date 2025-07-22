package br.com.wbs.modules.studio.repository;

import br.com.wbs.modules.studio.entity.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudioRepository extends JpaRepository<StudioEntity, UUID> {
    Optional<StudioEntity> findByName(String name);
}
