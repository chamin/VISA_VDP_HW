package com.visa.vdp.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 ** @Author kkcmpathi , Created on 28/03/24
 **/

@Configuration
public class RestClientConfig {

    @Value("${key.store.path}")
    String keyStorePath ;

    @Value("${key.store.password}")
    String keyStorePassword;

    @Value("${client.user.id}")
    String clientUserId;

    @Value("${client.user.pwd}")
    String clientUserPassword;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadKeyMaterial(ResourceUtils.getFile(keyStorePath), keyStorePassword.toCharArray(), keyStorePassword.toCharArray())
                .loadTrustMaterial(ResourceUtils.getFile(keyStorePath), keyStorePassword.toCharArray())
                .build();

        HttpClient client = HttpClients
                .custom()
                .setSSLContext(sslContext)
                .build();

        return builder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client))
                .basicAuthentication(clientUserId, clientUserPassword)
                .build();
    }

}
