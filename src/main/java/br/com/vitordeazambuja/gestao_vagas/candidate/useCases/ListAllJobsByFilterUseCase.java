package br.com.vitordeazambuja.gestao_vagas.candidate.useCases;


import br.com.vitordeazambuja.gestao_vagas.company.entities.JobEntity;
import br.com.vitordeazambuja.gestao_vagas.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter){
        return this.jobRepository.findByDescriptionContaining(filter);
    }
}
