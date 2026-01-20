package br.com.vitordeazambuja.gestao_vagas.candidate.controllers;

import br.com.vitordeazambuja.gestao_vagas.candidate.CandidateEntity;
import br.com.vitordeazambuja.gestao_vagas.candidate.CandidateRepository;
import br.com.vitordeazambuja.gestao_vagas.exceptions.UserFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
        this.candidateRepository
                .findByUserameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user)->{
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidateEntity);
    }
}
