package br.edu.utfpr.pb.trabalhofinal.service.impl;

import br.edu.utfpr.pb.trabalhofinal.model.Usuario;
import br.edu.utfpr.pb.trabalhofinal.repository.UsuarioRepository;
import br.edu.utfpr.pb.trabalhofinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long>
		implements UsuarioService, UserDetailsService{


	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected JpaRepository<Usuario, Long> getRepository() {
		return usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não "
					+ "encontrado!");
		}
		return usuario;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}
}
