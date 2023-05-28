package com.digitalhouse.clinicaodontologicag6.controller;

import com.digitalhouse.clinicaodontologicag6.entity.dto.DentistaDTO;
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
class DentistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Batata");
        dentistaDTO.setSobrenome("Testes Dentista controler");
        dentistaDTO.setUsername("batata test");
        dentistaDTO.setPassword("test");
        dentistaDTO.setMatricula("0000000001");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "Batata Dentista test", password = "test", roles = "ADMIN")
    void getById() throws Exception {
        //=> criar um novo dentista para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Batata getById test");
        dentistaDTO.setSobrenome("Testes Dentista controler getById");
        dentistaDTO.setUsername("batata getById test");
        dentistaDTO.setPassword("getById");
        dentistaDTO.setMatricula("0000000009");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o dentista cadastrado, obs: pegou o id
        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);

        //=> Agora sim fazer o getById
        mockMvc.perform(MockMvcRequestBuilders.get("/dentista/buscar?id={id}", dentistaDTO.getId()).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "Batata Dentista test", password = "test", roles = "ADMIN")
    void getByNome() throws Exception {
        //=> criar um novo dentista para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Batata getByNome test");
        dentistaDTO.setSobrenome("Testes Dentista controler getByNome");
        dentistaDTO.setUsername("batata getByNome test");
        dentistaDTO.setPassword("getByNome");
        dentistaDTO.setMatricula("0000000011");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o dentista cadastrado, obs: pegou o nome
        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);

        //=> Agora sim fazer o getByNome
        mockMvc.perform(MockMvcRequestBuilders.get("/dentista/buscar?nome={nome}", dentistaDTO.getNome()).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata Dentista test", password = "test", roles = "ADMIN")
    void getByMatricula() throws Exception {
        //=> criar um novo dentista para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Batata getByMatricula test");
        dentistaDTO.setSobrenome("Testes Dentista controler getByMatricula");
        dentistaDTO.setUsername("batata getByMatricula test");
        dentistaDTO.setPassword("getByMatricula");
        dentistaDTO.setMatricula("5555555555");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o dentista cadastrado, obs: pegou a matricula
        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);

        //=> Agora sim fazer o getByMatricula
        mockMvc.perform(MockMvcRequestBuilders.get("/dentista/buscar?matricula={matricula}", dentistaDTO.getMatricula()).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "Batata Dentista test", password = "test", roles = "ADMIN")
    void getAll() throws Exception {
        //=> criar um novo dentista para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Batata getAll test");
        dentistaDTO.setSobrenome("Testes Dentista controler getAll");
        dentistaDTO.setUsername("batata getAll test");
        dentistaDTO.setPassword("getAll");
        dentistaDTO.setMatricula("1234567890");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());

        //=> Agora sim fazer o getAll
        mockMvc.perform(MockMvcRequestBuilders.get("/dentista/listar").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata Dentista test", password = "test", roles = "ADMIN")
    void update() throws Exception {
        //=> criar um novo dentista para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Batata test");
        dentistaDTO.setSobrenome("Testes Dentista controler");
        dentistaDTO.setUsername("batata test");
        dentistaDTO.setPassword("test");
        dentistaDTO.setMatricula("3377327730");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o dentista cadastrado
        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);

        dentistaDTO.setNome("Batata alterado");
        dentistaDTO.setSobrenome("Testes Dentista controler alterado");
        dentistaDTO.setUsername("batata test alterada");
        dentistaDTO.setPassword("test");
        dentistaDTO.setMatricula("7777777777");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.put("/dentista/atualizar?id={id}", dentistaDTO.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "Batata Dentista test", password = "test", roles = "ADMIN")
    void delete() throws Exception {
        //=> criar um novo dentista para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Batata delete test");
        dentistaDTO.setSobrenome("Testes Dentista controler delete");
        dentistaDTO.setUsername("batata delete test");
        dentistaDTO.setPassword("delete");
        dentistaDTO.setMatricula("5512451585");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        //=> Pego o dentista cadastrado, obs: pegou o id
        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);

        mockMvc.perform(MockMvcRequestBuilders.delete("/dentista/excluir?id={id}", dentistaDTO.getId()).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createAuthenticationToken() throws Exception {
        //=> criar um novo dentista para puxar ele obs: Não ficar dependendo de outro teste(boas praticas)
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Batata createAuthenticationToken test");
        dentistaDTO.setSobrenome("Testes Dentista controler createAuthenticationToken");
        dentistaDTO.setUsername("batata createAuthenticationToken test");
        dentistaDTO.setPassword("createAuthenticationToken");
        dentistaDTO.setMatricula("9912991599");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/dentista/auth").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content("{\"username\":\"batata createAuthenticationToken test\",\"password\":\"createAuthenticationToken\"}")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }
}