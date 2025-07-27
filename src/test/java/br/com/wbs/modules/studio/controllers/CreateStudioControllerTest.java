package br.com.wbs.modules.studio.controllers;

import br.com.wbs.modules.studio.dto.StudioDetailsDTO;
import br.com.wbs.modules.studio.dto.StudioRegisterDTO;
import br.com.wbs.modules.studio.entity.StudioEntity;
import br.com.wbs.modules.studio.repository.StudioRepository;
import br.com.wbs.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CreateStudioControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private StudioRepository studioRepository;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void should_be_able_to_create_a_new_studio() throws Exception {
        var studioRegisterDTO = new StudioRegisterDTO("STUDIO_TEST");
        var json = TestUtils.objectToJson(studioRegisterDTO);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/api/studio/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        System.out.println(result);

        var studios = studioRepository.findAll();
        assertTrue(studios.stream().anyMatch(studio -> studio.getName().equals("STUDIO_TEST")));
    }
}
