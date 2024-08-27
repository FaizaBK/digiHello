package org.example.digihello.controllers;

import org.example.digihello.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class HelloControleur {

    private final HelloService helloService;

    @Autowired
    public HelloControleur(HelloService helloService) {
        this.helloService = helloService;
    }

    // Étape 4 : Transformer la méthode direHello()
    @GetMapping("/hello")
    public String direHello() {
        return helloService.salutations();
    }
}