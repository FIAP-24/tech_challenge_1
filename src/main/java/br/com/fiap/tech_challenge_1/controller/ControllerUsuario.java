package br.com.fiap.tech_challenge_1.controller;

import br.com.fiap.tech_challenge_1.controller.dto.Usuario;
import br.com.fiap.tech_challenge_1.controller.dto.UsuarioLogin;
import br.com.fiap.tech_challenge_1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/usuario")
public class ControllerUsuario {

  @Autowired private UsuarioService usuarioService;

  @GetMapping
  public ResponseEntity<Set<Usuario>> findAll() {

    return ResponseEntity.ok(usuarioService.findAll());
  }

  @PostMapping("/cadastraUsuario")
  public ResponseEntity<String> saveUsuario(@RequestBody Usuario usuario) {
    usuarioService.save(usuario);
    return ResponseEntity.status(HttpStatus.OK).body("Usuario cadastrado com sucesso");
  }

  @PostMapping("/login")
  public ResponseEntity<Boolean> login(@RequestBody UsuarioLogin usuarioLogin) {

    return ResponseEntity.ok(usuarioService.login(usuarioLogin));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    usuarioService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
  }

}
