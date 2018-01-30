package com.example.resttemplate.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.resttemplate.dto.MyDto;

/**
 * User: stevedavis
 * Date: 30/01/2018
 * Time: 17:08
 * Description:
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String helloWorld() {

        return "Hello World";

    }

    @GetMapping("/template")
    public String template() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>("{\"firstname\":\"inda\", \"surname\":\"code\"}", headers);
        
        //String response = restTemplate.postForObject("http://localhost:8080/dto", entity, String.class);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/dto", HttpMethod.POST, entity, String.class, (Object)null);

        System.out.println (response.getStatusCode());

        return response.getBody();
    }

    @PostMapping("/dto")
    public String passDto(@RequestBody MyDto dto) {

        return "Hello World to " + dto.getFirstname() + " " + dto.getSurname();

    }

}
