package br.com.fiap.tech_challenge_1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tech-challenge")
public class ControllerTeste {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @RequestMapping("/teste")
    public String teste() {
        return "Teste";
    }

    @RequestMapping("/teste2")
    public String teste2() {
        return "Teste 2";
    }

}
