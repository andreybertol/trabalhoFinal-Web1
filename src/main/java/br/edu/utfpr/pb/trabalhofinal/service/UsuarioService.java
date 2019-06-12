package br.edu.utfpr.pb.trabalhofinal.service;

import br.edu.utfpr.pb.trabalhofinal.model.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long> {

    Usuario findByUsername(String username);

}
