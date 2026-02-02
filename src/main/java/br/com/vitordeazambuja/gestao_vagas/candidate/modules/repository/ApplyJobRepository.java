package br.com.vitordeazambuja.gestao_vagas.candidate.modules.repository;

import br.com.vitordeazambuja.gestao_vagas.candidate.modules.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}
