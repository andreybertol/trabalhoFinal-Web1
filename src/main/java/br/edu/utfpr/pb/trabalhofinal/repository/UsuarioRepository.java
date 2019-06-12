package br.edu.utfpr.pb.trabalhofinal.repository;

import br.edu.utfpr.pb.trabalhofinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

	@Query(value = "delete from usuario_permissoes where usuario_id = :id_usuario", nativeQuery = true)
	void deletaUsuarioPermissao(@Param("id_usuario") long id);
}
