package br.com.fiap.tech_challenge_1.controller;

import br.com.fiap.tech_challenge_1.dto.request.UsuarioEditRequest;
import br.com.fiap.tech_challenge_1.dto.request.UsuarioLoginRequest;
import br.com.fiap.tech_challenge_1.dto.request.UsuarioRequest;
import br.com.fiap.tech_challenge_1.dto.response.ApiResponse;
import br.com.fiap.tech_challenge_1.dto.response.UsuarioResponse;
import br.com.fiap.tech_challenge_1.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Get all users
     * @return List of users
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Set<UsuarioResponse>>> findAll() {
        Set<UsuarioResponse> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(ApiResponse.success(usuarios));
    }

    /**
     * Get user by ID
     * @param id User ID
     * @return User details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> findById(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(usuario));
    }

    /**
     * Create new user
     * @param usuarioRequest User data
     * @return Created user
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponse>> create(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse created = usuarioService.save(usuarioRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(created, "Usuário cadastrado com sucesso"));
    }

    /**
     * Update existing user
     * @param id User ID
     * @param usuarioEditRequest Updated user data
     * @return Updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioEditRequest usuarioEditRequest) {
        UsuarioResponse updated = usuarioService.update(id, usuarioEditRequest);
        return ResponseEntity.ok(ApiResponse.success(updated, "Usuário atualizado com sucesso"));
    }

    /**
     * Authenticate user
     * @param loginRequest Login credentials
     * @return Authentication result
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Boolean>> login(@Valid @RequestBody UsuarioLoginRequest loginRequest) {
        boolean authenticated = usuarioService.authenticate(loginRequest);
        return ResponseEntity.ok(
                authenticated ?
                        ApiResponse.success(true, "Autenticação bem-sucedida") :
                        ApiResponse.error("Falha na autenticação")
        );
    }

    /**
     * Delete user
     * @param id User ID
     * @return Confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Usuário removido com sucesso"));
    }
}