package br.edu.utfpr.pb.trabalhofinal.repository;

import br.edu.utfpr.pb.trabalhofinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username);
}
