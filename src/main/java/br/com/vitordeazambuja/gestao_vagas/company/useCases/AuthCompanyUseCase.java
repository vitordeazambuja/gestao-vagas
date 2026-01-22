package br.com.vitordeazambuja.gestao_vagas.company.useCases;

import br.com.vitordeazambuja.gestao_vagas.company.dto.AuthCompanyDTO;
import br.com.vitordeazambuja.gestao_vagas.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                }
        );

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // Se não for igual, retorna erro
        if(!passwordMatches) {
            throw new BadCredentialsException("Username/password incorrect");
        }
        // Se for igual, gera token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("javagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);
        return token;
    }
}
