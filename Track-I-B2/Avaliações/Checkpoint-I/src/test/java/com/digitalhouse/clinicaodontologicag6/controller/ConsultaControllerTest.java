package com.digitalhouse.clinicaodontologicag6.controller;

import com.digitalhouse.clinicaodontologicag6.entity.dto.ConsultaDTO;
import com.digitalhouse.clinicaodontologicag6.entity.dto.DentistaDTO;
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
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Batata", password = "test", roles = "ADMIN")
    void create() throws Exception {
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Dentista1");
        dentistaDTO.setSobrenome("Um");
        dentistaDTO.setUsername("dentista1");
        dentistaDTO.setPassword("testdentista1");
        dentistaDTO.setMatricula("dentista1");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResultDentista = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();


        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente1");
        pacienteDTO.setSobrenome("Um");
        pacienteDTO.setUsername("paciente1");
        pacienteDTO.setPassword("123456");
        pacienteDTO.setRg("paciente1");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResultPaciente = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyDentista = mvcResultDentista.getResponse().getContentAsString();
        String responseBodyPaciente = mvcResultPaciente.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBodyDentista);
        pacienteDTO = objectFromString(PacienteDTO.class, responseBodyPaciente);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentistaDTO.getId());
        consultaDTO.setPaciente(pacienteDTO.getId());
        consultaDTO.setDataConsulta("27-09-2022 18:00");

        mockMvc.perform(MockMvcRequestBuilders.post("/consulta/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(consultaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @WithMockUser(username = "Batata", password = "test", roles = "ADMIN")
    void getById() throws Exception {

        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Dentista2");
        dentistaDTO.setSobrenome("Dois");
        dentistaDTO.setUsername("dentista2");
        dentistaDTO.setPassword("dois");
        dentistaDTO.setMatricula("dentista2");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResultDentista = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente2");
        pacienteDTO.setSobrenome("Dois");
        pacienteDTO.setUsername("paciente2");
        pacienteDTO.setPassword("paciente2");
        pacienteDTO.setRg("paciente2");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResultPaciente = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyDentista = mvcResultDentista.getResponse().getContentAsString();
        String responseBodyPaciente = mvcResultPaciente.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBodyDentista);
        pacienteDTO = objectFromString(PacienteDTO.class, responseBodyPaciente);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentistaDTO.getId());
        consultaDTO.setPaciente(pacienteDTO.getId());
        consultaDTO.setDataConsulta("27-09-2022 18:00");

        mockMvc.perform(MockMvcRequestBuilders.get("/consulta/buscar?id={id}", 1).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata", password = "test", roles = "ADMIN")
    void getByDentista() throws Exception {

        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Dentista3");
        dentistaDTO.setSobrenome("Três");
        dentistaDTO.setUsername("dentista3");
        dentistaDTO.setPassword("tres");
        dentistaDTO.setMatricula("dentista3");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResultDentista = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente3");
        pacienteDTO.setSobrenome("Três");
        pacienteDTO.setUsername("paciente3");
        pacienteDTO.setPassword("paciente3");
        pacienteDTO.setRg("paciente3");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResultPaciente = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyDentista = mvcResultDentista.getResponse().getContentAsString();
        String responseBodyPaciente = mvcResultPaciente.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBodyDentista);
        pacienteDTO = objectFromString(PacienteDTO.class, responseBodyPaciente);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentistaDTO.getId());
        consultaDTO.setPaciente(pacienteDTO.getId());
        consultaDTO.setDataConsulta("27-09-2022 18:00");

        mockMvc.perform(MockMvcRequestBuilders.get("/consulta/buscar?dentista={id}", 1).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata", password = "test", roles = "ADMIN")
    void getByPaciente() throws Exception {

        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Dentista4");
        dentistaDTO.setSobrenome("Quatro");
        dentistaDTO.setUsername("dentista4");
        dentistaDTO.setPassword("quatro");
        dentistaDTO.setMatricula("dentista4");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResultDentista = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente4");
        pacienteDTO.setSobrenome("Quatro");
        pacienteDTO.setUsername("paciente4");
        pacienteDTO.setPassword("paciente4");
        pacienteDTO.setRg("paciente4");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResultPaciente = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyDentista = mvcResultDentista.getResponse().getContentAsString();
        String responseBodyPaciente = mvcResultPaciente.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBodyDentista);
        pacienteDTO = objectFromString(PacienteDTO.class, responseBodyPaciente);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentistaDTO.getId());
        consultaDTO.setPaciente(pacienteDTO.getId());
        consultaDTO.setDataConsulta("27-09-2022 18:00");

        mockMvc.perform(MockMvcRequestBuilders.get("/consulta/buscar?paciente={id}", 1).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata", password = "test", roles = "ADMIN")
    void getByData() throws Exception {

        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Dentista5");
        dentistaDTO.setSobrenome("Cinco");
        dentistaDTO.setUsername("dentista5");
        dentistaDTO.setPassword("cinco");
        dentistaDTO.setMatricula("dentista5");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResultDentista = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente5");
        pacienteDTO.setSobrenome("Cinco");
        pacienteDTO.setUsername("paciente5");
        pacienteDTO.setPassword("paciente5");
        pacienteDTO.setRg("paciente5");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResultPaciente = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyDentista = mvcResultDentista.getResponse().getContentAsString();
        String responseBodyPaciente = mvcResultPaciente.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBodyDentista);
        pacienteDTO = objectFromString(PacienteDTO.class, responseBodyPaciente);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentistaDTO.getId());
        consultaDTO.setPaciente(pacienteDTO.getId());
        consultaDTO.setDataConsulta("27-09-2022 18:00");

        mockMvc.perform(MockMvcRequestBuilders.get("/consulta/buscar?data={id}", "27-09-2022 18:00").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata", password = "test", roles = "ADMIN")
    void getAll() throws Exception {

        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Dentista6");
        dentistaDTO.setSobrenome("Seis");
        dentistaDTO.setUsername("dentista6");
        dentistaDTO.setPassword("seis");
        dentistaDTO.setMatricula("dentista6");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResultDentista = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente6");
        pacienteDTO.setSobrenome("Seis");
        pacienteDTO.setUsername("paciente6");
        pacienteDTO.setPassword("paciente6");
        pacienteDTO.setRg("paciente6");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResultPaciente = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyDentista = mvcResultDentista.getResponse().getContentAsString();
        String responseBodyPaciente = mvcResultPaciente.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBodyDentista);
        pacienteDTO = objectFromString(PacienteDTO.class, responseBodyPaciente);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentistaDTO.getId());
        consultaDTO.setPaciente(pacienteDTO.getId());
        consultaDTO.setDataConsulta("27-09-2022 18:00");

        mockMvc.perform(MockMvcRequestBuilders.get("/consulta/listar").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "Batata", password = "test", roles = "ADMIN")
    void update() throws Exception {

        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Dentista7");
        dentistaDTO.setSobrenome("Sete");
        dentistaDTO.setUsername("dentista7");
        dentistaDTO.setPassword("sete");
        dentistaDTO.setMatricula("dentista7");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResultDentista = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente7");
        pacienteDTO.setSobrenome("Sete");
        pacienteDTO.setUsername("paciente7");
        pacienteDTO.setPassword("paciente7");
        pacienteDTO.setRg("paciente7");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResultPaciente = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyDentista = mvcResultDentista.getResponse().getContentAsString();
        String responseBodyPaciente = mvcResultPaciente.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBodyDentista);
        pacienteDTO = objectFromString(PacienteDTO.class, responseBodyPaciente);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentistaDTO.getId());
        consultaDTO.setPaciente(pacienteDTO.getId());
        consultaDTO.setDataConsulta("27-09-2022 18:00");

        MvcResult mvcResultConsulta = mockMvc.perform(MockMvcRequestBuilders.post("/consulta/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(consultaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyConsulta = mvcResultConsulta.getResponse().getContentAsString();

        consultaDTO = objectFromString(ConsultaDTO.class, responseBodyConsulta);
        consultaDTO.setDentista(1L);
        consultaDTO.setPaciente(2L);
        consultaDTO.setDataConsulta("27-09-2023 18:00");

        mockMvc.perform(MockMvcRequestBuilders.put("/consulta/atualizar?id={id}", consultaDTO.getId()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(consultaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "Batata", password = "test", roles = "ADMIN")
    void delete() throws Exception {

        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setNome("Dentista8");
        dentistaDTO.setSobrenome("Oito");
        dentistaDTO.setUsername("dentista8");
        dentistaDTO.setPassword("oito");
        dentistaDTO.setMatricula("dentista8");
        dentistaDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        MvcResult mvcResultDentista = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(dentistaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente8");
        pacienteDTO.setSobrenome("Oito");
        pacienteDTO.setUsername("paciente8");
        pacienteDTO.setPassword("paciente8");
        pacienteDTO.setRg("paciente8");
        pacienteDTO.setLogradouro("Rua dos AD");
        pacienteDTO.setNumero("003");
        pacienteDTO.setComplemento("Viela NaN");
        pacienteDTO.setBairro("Liberdade");
        pacienteDTO.setCidade("Madri");
        pacienteDTO.setEstado("JA");
        pacienteDTO.setCep("00000-003");
        pacienteDTO.setUserRoles(UserRoles.ROLE_USER);

        MvcResult mvcResultPaciente = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(pacienteDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyDentista = mvcResultDentista.getResponse().getContentAsString();
        String responseBodyPaciente = mvcResultPaciente.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBodyDentista);
        pacienteDTO = objectFromString(PacienteDTO.class, responseBodyPaciente);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setDentista(dentistaDTO.getId());
        consultaDTO.setPaciente(pacienteDTO.getId());
        consultaDTO.setDataConsulta("27-09-2022 18:00");

        MvcResult mvcResultConsulta = mockMvc.perform(MockMvcRequestBuilders.post("/consulta/cadastrar").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(consultaDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBodyConsulta = mvcResultConsulta.getResponse().getContentAsString();

        consultaDTO = objectFromString(ConsultaDTO.class, responseBodyConsulta);

        mockMvc.perform(MockMvcRequestBuilders.delete("/consulta/excluir?id={id}", consultaDTO.getId()).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }
}