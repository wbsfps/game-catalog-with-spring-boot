package br.com.wbs.modules.game.repository;

import br.com.wbs.modules.game.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {
    Optional<GameEntity> findByNameOrStudioId(String name, UUID idStudio);
}
