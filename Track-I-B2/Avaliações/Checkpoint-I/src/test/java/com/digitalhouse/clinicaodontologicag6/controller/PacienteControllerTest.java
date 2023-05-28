package com.digitalhouse.clinicaodontologicag6.controller;

import com.digitalhouse.clinicaodontologicag6.entity.dto.PacienteDTO;
import com.digitalhouse.clinicaodontologicag6.enums.UserRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.digitalhouse.clinicaodontologicag6.utils.ClinicaOdontologicaUtils.asJsonString;
import static com.digitalhouse.clinicaodontologicag6.utils.ClinicaOdontologicaUtils.objectFromString;

@SpringBootTest
@AutoConfigureMockMvc
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Batata7 test", password = "test", roles = "ADMIN")
    void create() throws Exception {
        //=> Apenas admin pode criar paciente ok
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Batata");
        pacienteDTO.setSobrenome("paciente testes");
        pacienteDTO.setUsername("Batata test");
        pacienteDTO.setPassword("test");
        pacienteDTO.setRg("00000001");
        pacienteDTO.setLogradouro("Rua dos QA");
        pacienteDTO.setNumero("001");
        pacienteDTO.setComplemento("Apt NaN");
        pacienteDTO.setBairro("Socorro");
        pacienteDTO.setCidade("Pesado dos dev");
        pacienteDTO.setEstado("QA");
        pacienteDTO.setCep("00000-001");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "Batata7 test", password = "test", roles = "ADMIN")
    void getById() throws Exception {
        //=> criar um novo paciente para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Batata");
        pacienteDTO.setSobrenome("paciente testes");
        pacienteDTO.setUsername("arroz test");
        pacienteDTO.setPassword("test");
        pacienteDTO.setRg("01928374");
        pacienteDTO.setLogradouro("Rua dos QA");
        pacienteDTO.setNumero("001");
        pacienteDTO.setComplemento("Apt NaN");
        pacienteDTO.setBairro("Socorro");
        pacienteDTO.setCidade("Pesado dos dev");
        pacienteDTO.setEstado("QA");
        pacienteDTO.setCep("00000-001");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
        // .andReturn();

        //String responseBody = mvcResult.getResponse().getContentAsString();
        //=> Pego o paciente cadastrado, obs: pegou o id
        //pacienteDTO = objectFromString(PacienteDTO.class, responseBody);

        //=> Agora sim fazer o getById
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscar?id={id}", 4).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata9 paciente test", password = "test", roles = "ADMIN")
    void getByNome() throws Exception {
        //=> criar um novo paciente para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Jose");
        pacienteDTO.setSobrenome("paciente 2");
        pacienteDTO.setUsername("Pac2");
        pacienteDTO.setPassword("12345");
        pacienteDTO.setRg("03030303");
        pacienteDTO.setLogradouro("Rua dos AB");
        pacienteDTO.setNumero("002");
        pacienteDTO.setComplemento("Casa NaN");
        pacienteDTO.setBairro("Java");
        pacienteDTO.setCidade("Parana");
        pacienteDTO.setEstado("PA");
        pacienteDTO.setCep("00000-002");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o paciente cadastrado, obs: pegou o nome
        pacienteDTO = objectFromString(PacienteDTO.class, responseBody);

        //=> Agora sim fazer o getByNome
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscar?nome={nome}", pacienteDTO.getNome()).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata2 Paciente test", password = "test", roles = "ADMIN")
    void getByMatricula() throws Exception {
        //=> criar um novo paciente para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Fernando");
        pacienteDTO.setSobrenome("paciente 3");
        pacienteDTO.setUsername("Pac3");
        pacienteDTO.setPassword("123456");
        pacienteDTO.setRg("04040404");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o paciente cadastrado, obs: pegou a matricula
        pacienteDTO = objectFromString(PacienteDTO.class, responseBody);

        //=> Agora sim fazer o getByMatricula
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscar?rg={rg}", pacienteDTO.getRg()).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "Batata3 paciente test", password = "test", roles = "ADMIN")
    void getAll() throws Exception {
        //=> criar um novo paciente para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Lucas");
        pacienteDTO.setSobrenome("paciente 4");
        pacienteDTO.setUsername("Pac4");
        pacienteDTO.setPassword("123457");
        pacienteDTO.setRg("05050505");
        pacienteDTO.setLogradouro("Rua dos AV");
        pacienteDTO.setNumero("004");
        pacienteDTO.setComplemento("Travessa NaN");
        pacienteDTO.setBairro("Mooca");
        pacienteDTO.setCidade("Barcelona");
        pacienteDTO.setEstado("TA");
        pacienteDTO.setCep("00000-004");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());

        //=> Agora sim fazer o getAll
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/listar").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata4 paciente test", password = "test", roles = "ADMIN")
    void update() throws Exception {
        //=> criar um novo paciente para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Marcos");
        pacienteDTO.setSobrenome("paciente 5");
        pacienteDTO.setUsername("Pac5");
        pacienteDTO.setPassword("123459");
        pacienteDTO.setRg("06060606");
        pacienteDTO.setLogradouro("Rua dos AK");
        pacienteDTO.setNumero("005");
        pacienteDTO.setComplemento("Quadra NaN");
        pacienteDTO.setBairro("Butanta");
        pacienteDTO.setCidade("Osasco");
        pacienteDTO.setEstado("PA");
        pacienteDTO.setCep("00000-005");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o paciente cadastrado
        pacienteDTO = objectFromString(PacienteDTO.class, responseBody);

        pacienteDTO.setNome("Luan");
        pacienteDTO.setSobrenome("paciente 6");
        pacienteDTO.setUsername("Pac6");
        pacienteDTO.setPassword("123451");
        pacienteDTO.setRg("07070707");
        pacienteDTO.setLogradouro("Rua dos AL");
        pacienteDTO.setNumero("006");
        pacienteDTO.setComplemento("Avenida NaN");
        pacienteDTO.setBairro("Carrao");
        pacienteDTO.setCidade("Sao Paulo");
        pacienteDTO.setEstado("RA");
        pacienteDTO.setCep("00000-006");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        mockMvc.perform(MockMvcRequestBuilders.put("/paciente/atualizar?id={id}", pacienteDTO.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "Batata6 paciente test", password = "test", roles = "ADMIN")
    void delete() throws Exception {
        //=> criar um novo paciente para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Roberto");
        pacienteDTO.setSobrenome("paciente 6");
        pacienteDTO.setUsername("Pac6");
        pacienteDTO.setPassword("123450");
        pacienteDTO.setRg("08080808");
        pacienteDTO.setLogradouro("Rua dos AT");
        pacienteDTO.setNumero("008");
        pacienteDTO.setComplemento("Lugar NaN");
        pacienteDTO.setBairro("Raposo");
        pacienteDTO.setCidade("Roraima");
        pacienteDTO.setEstado("RA");
        pacienteDTO.setCep("00000-008");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o dentista cadastrado, obs: pegou o id
        pacienteDTO = objectFromString(PacienteDTO.class, responseBody);

        mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/excluir?id={id}", pacienteDTO.getId()).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createAuthenticationToken() throws Exception {
        //=> criar um novo paciente para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/auth").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content("{\"username\":\"Pac3\",\"password\":\"123456\"}")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

}