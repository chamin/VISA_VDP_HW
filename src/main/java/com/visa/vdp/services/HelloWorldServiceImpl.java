package com.visa.vdp.services;

import com.visa.vdp.exception.ApplicationException;
import com.visa.vdp.model.VdpResponse;
import com.visa.vdp.util.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 ** @Author kkcmpathi , Created on 28/03/24
 **/

@Service
public class HelloWorldServiceImpl implements HelloWorldService{
   private static final Logger logger = LogManager.getLogger(HelloWorldServiceImpl.class);

   @Value("${visa.hello.endpoint}")
   String endpointHelloWorld;

    RestTemplate restTemplate;
    @Autowired
    public void  setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

   @Override
   public String invokeHelloWorldService() {
       try{
           VdpResponse res = restTemplate.getForObject(endpointHelloWorld, VdpResponse.class);
           String responseMessage = res != null ? res.getMessage() : "";
           logger.info("Visa VDP Response::{}", responseMessage);
           return responseMessage;
       } catch (Exception e){
           logger.error("Error while invoking service{}", e.getMessage(), e);
           throw new ApplicationException(Constant.DOWN_STREAM_ERROR_CODE, Constant.DOWN_STREAM_ERROR_MESSAGE, e);
       }

   }
}
