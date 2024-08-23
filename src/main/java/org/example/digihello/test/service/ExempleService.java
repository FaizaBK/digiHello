package org.example.digihello.test.service;

import org.example.digihello.test.exceptions.CustomException;
import org.springframework.stereotype.Service;

@Service
public class ExempleService {

    public void executeSomething() throws CustomException {
        // Condition pour lancer l'exception
        boolean someCondition = false;
        if (someCondition) {
            throw new CustomException("Une erreur personnalisée s'est produite.");
        }
    }
}