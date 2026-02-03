package br.com.vitordeazambuja.gestao_vagas.candidate.useCases;

import br.com.vitordeazambuja.gestao_vagas.candidate.dto.ProfileCandidateResponseDTO;
import br.com.vitordeazambuja.gestao_vagas.candidate.repositories.CandidateRepository;
import br.com.vitordeazambuja.gestao_vagas.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId){
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(
                        () -> {
                            throw new UserNotFoundException();
                        }
                );
        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
        return candidateDTO;
    }
}
