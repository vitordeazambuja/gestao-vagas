package br.com.vitordeazambuja.gestao_vagas.company.useCases;

import br.com.vitordeazambuja.gestao_vagas.company.entities.JobEntity;
import br.com.vitordeazambuja.gestao_vagas.company.repositories.CompanyRepository;
import br.com.vitordeazambuja.gestao_vagas.company.repositories.JobRepository;
import br.com.vitordeazambuja.gestao_vagas.exceptions.CompanyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException());

        return this.jobRepository.save(jobEntity);
    }
}
