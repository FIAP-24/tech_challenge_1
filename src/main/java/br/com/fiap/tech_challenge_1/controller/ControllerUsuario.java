package br.com.fiap.tech_challenge_1.controller;

import br.com.fiap.tech_challenge_1.controller.dto.UsuarioDTO;
import br.com.fiap.tech_challenge_1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/usuario")
public class ControllerUsuario {

  @Autowired private UsuarioService usuarioService;

  @GetMapping
  public ResponseEntity<Set<UsuarioDTO>> findAll() {

    return ResponseEntity.ok(usuarioService.findAll());
  }

  @PostMapping("/cadastraUsuario")
  public ResponseEntity<String> saveUsuario(@RequestBody UsuarioDTO usuarioDTO) {
    usuarioService.save(usuarioDTO);
    return ResponseEntity.ok("Usuario cadastrado com sucesso");
  }

}
