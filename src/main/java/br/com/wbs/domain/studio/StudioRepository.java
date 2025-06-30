package br.com.wbs.domain.studio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface StudioRepository extends JpaRepository<Studio, UUID> {

//    @Query("select s from tb_studio s left join fetch s.tb_game where s.uuid = :uuid")
//    Optional<Studio> findByIdWithGames(@Param("uuid") UUID uuid);
}
