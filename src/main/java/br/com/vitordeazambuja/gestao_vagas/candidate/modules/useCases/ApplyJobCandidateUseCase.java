package br.com.vitordeazambuja.gestao_vagas.candidate.modules.useCases;

import br.com.vitordeazambuja.gestao_vagas.candidate.repositories.CandidateRepository;
import br.com.vitordeazambuja.gestao_vagas.company.repositories.JobRepository;
import br.com.vitordeazambuja.gestao_vagas.exceptions.JobNotFoundException;
import br.com.vitordeazambuja.gestao_vagas.exceptions.UserNotFoundException;
import br.com.vitordeazambuja.gestao_vagas.candidate.modules.entity.ApplyJobEntity;
import br.com.vitordeazambuja.gestao_vagas.candidate.modules.repository.ApplyJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob){
    // Validar se candidato existe
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(()->{
                    throw new UserNotFoundException();
                });

    // Validar se vaga existe
        this.jobRepository.findById(idJob)
                .orElseThrow(()->{
                    throw new JobNotFoundException();
                });
    // Candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
                        .candidateId(idCandidate)
                        .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);

        return applyJob;

    }
}
