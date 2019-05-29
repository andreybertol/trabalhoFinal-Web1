package br.edu.utfpr.pb.trabalhofinal.service.impl;

import br.edu.utfpr.pb.trabalhofinal.model.CompraProduto;
import br.edu.utfpr.pb.trabalhofinal.repository.CompraProdutoRepository;
import br.edu.utfpr.pb.trabalhofinal.service.CompraProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraProdutoServiceImpl extends CrudServiceImpl<CompraProduto, Integer>
		implements CompraProdutoService {

	@Autowired
	private CompraProdutoRepository compraProdutoRepository;
	
	@Override
	protected JpaRepository<CompraProduto, Integer> getRepository() {
		return compraProdutoRepository;
	}

}
