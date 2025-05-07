package br.com.fiap.tech_challenge_1.repository;

import br.com.fiap.tech_challenge_1.repository.entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findById(Long id);



}
