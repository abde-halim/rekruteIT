package com.springboot.rekruteIT.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "recruteur-service", url = "${recruteur.service.url:http://localhost:8082}")
public interface RecruteurServiceClient {

    @GetMapping("/api/recruteurs/{id}/exists")
    boolean existsById(@PathVariable Long id);

    @GetMapping("/api/recruteurs/{id}")
    RecruteurDTO getRecruteurById(@PathVariable Long id);
}
