package com.visa.vdp.controller;

import com.visa.vdp.exception.ApplicationException;
import com.visa.vdp.services.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@WebMvcTest(HelloWorldRestController.class)
public class HelloWorldRestControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    HelloWorldService helloWorldService;
    @MockBean
    RestTemplate restTemplate;

    @Test
    public void helloWorldExpectSuccess() throws Exception {
        String resp = "VDP Response";
        Mockito.when(helloWorldService.invokeHelloWorldService()).thenReturn(resp);
        mvc.perform(MockMvcRequestBuilders
                .get("/visa/vdp/helloworld")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{message:'VDP Response'}"));
    }
    @Test
    public void helloWorldException() throws Exception {
        Mockito.when(helloWorldService.invokeHelloWorldService())
                .thenThrow(new ApplicationException("120", "Error connecting to remote VPD helloworld endpoint"));
        mvc.perform(MockMvcRequestBuilders
                        .get("/visa/vdp/helloworld")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("ERROR CODE::120"));
    }
}