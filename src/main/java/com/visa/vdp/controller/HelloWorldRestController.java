package com.visa.vdp.controller;

import com.visa.vdp.model.Response;
import com.visa.vdp.services.HelloWorldService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 ** @Author kkcmpathi , Created on 28/03/24
 **/

@RestController
@RequestMapping("/visa/vdp")
public class HelloWorldRestController {
    private static final Logger logger = LogManager.getLogger(HelloWorldRestController.class);
    private HelloWorldService helloWorldService;

    @Autowired
    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/helloworld")
    public Response helloWorld() {
        Response response = new Response();
        String responseString = helloWorldService.invokeHelloWorldService();
        logger.info("Response from Visa VDP server::{}",responseString);
        response.setMessage(responseString);
        return response;


    }

}
