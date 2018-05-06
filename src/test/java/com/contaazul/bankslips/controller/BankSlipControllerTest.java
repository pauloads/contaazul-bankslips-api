package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.model.BankSlip;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Paulo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BankSlipControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post("/rest/bankslips")
                .contentType(MediaType.APPLICATION_JSON).content(newBankslip())
        ).andExpect(status().isCreated());
    }

    @Test
    public void testList() throws Exception {
        mockMvc.perform(get("/rest/bankslips")).andExpect(status().isOk());
    }

    @Test
    public void testGetDetails() throws Exception {
        mockMvc.perform(get("/rest/bankslips/" + getCreatedBankSlipId())).andExpect(status().isOk());
    }

    @Test
    public void testPay() throws Exception {
        mockMvc.perform(put("/rest/bankslips/" + getCreatedBankSlipId() + "/pay")).andExpect(status().isOk());
    }

    @Test
    public void testCancel() throws Exception {
        mockMvc.perform(delete("/rest/bankslips/" + getCreatedBankSlipId() + "/cancel")).andExpect(status().isOk());
    }

    private String newBankslip() {
        return "{\"due_date\":\"2999-05-10\",\"total_in_cents\":\"100000\",\"customer\":\"Terry Tate\",\"status\":\"PENDING\"}";
    }

    private String getCreatedBankSlipId() throws Exception {

        mockMvc.perform(post("/rest/bankslips")
                .contentType(MediaType.APPLICATION_JSON).content(newBankslip())
        ).andExpect(status().isCreated());

        MvcResult result = mockMvc.perform(get("/rest/bankslips")).andExpect(status().isOk()).andReturn();

        String bankSlipDetailsJson = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        List<BankSlip> bankSlips = mapper.readValue(bankSlipDetailsJson, new TypeReference<List<BankSlip>>() {
        });

        return bankSlips.get(0).getId();
    }
}
