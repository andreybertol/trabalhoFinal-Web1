package br.edu.utfpr.pb.trabalhofinal.config;

import br.edu.utfpr.pb.trabalhofinal.model.Usuario;
import br.edu.utfpr.pb.trabalhofinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class CrudAuditorAware implements AuditorAware<Usuario> {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public Optional<Usuario> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder
											.getContext()
											.getAuthentication();
	
		return Optional.ofNullable( 
				usuarioService.findByUsername(authentication.getName()));
	}

}
