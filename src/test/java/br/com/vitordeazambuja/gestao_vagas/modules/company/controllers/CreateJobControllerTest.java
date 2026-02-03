package br.com.vitordeazambuja.gestao_vagas.modules.company.controllers;

import br.com.vitordeazambuja.gestao_vagas.company.dto.CreateJobDTO;
import br.com.vitordeazambuja.gestao_vagas.company.entities.CompanyEntity;
import br.com.vitordeazambuja.gestao_vagas.company.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.vitordeazambuja.gestao_vagas.modules.utils.TestUtils.generateToken;
import static br.com.vitordeazambuja.gestao_vagas.modules.utils.TestUtils.objectToJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @WithMockUser(roles = "COMPANY")
    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        var company = CompanyEntity.builder()
                .description("COMPANY_DESCRIPTION")
                .email("email@company.com")
                .password("1234567890")
                .username("COMPANY_USERNAME")
                .build();

        company = companyRepository.saveAndFlush(company);

        var createJobDTO = CreateJobDTO.builder()
                            .benefits("BENEFITS_TEST")
                            .description("DESCRIPTION_TEST")
                            .level("LEVEL_TEST")
                            .build();

        var result = mvc.perform(post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createJobDTO))
                        .requestAttr("company_id", company.getId()))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "COMPANY")
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception{
        var createdJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mvc.perform(post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createdJobDTO))
                        .header("Authorization", generateToken(UUID.randomUUID(), "JAVAGAS_@123#")))
                .andExpect(status().isBadRequest());
    }

}
