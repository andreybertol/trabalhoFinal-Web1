package br.edu.utfpr.pb.trabalhofinal.service.impl;

import br.edu.utfpr.pb.trabalhofinal.model.VendaProduto;
import br.edu.utfpr.pb.trabalhofinal.repository.VendaProdutoRepository;
import br.edu.utfpr.pb.trabalhofinal.service.VendaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaProdutoServiceImpl extends CrudServiceImpl<VendaProduto, Integer>
		implements VendaProdutoService {

	@Autowired
	private VendaProdutoRepository vendaProdutoRepository;
	
	@Override
	protected JpaRepository<VendaProduto, Integer> getRepository() {
		return vendaProdutoRepository;
	}

}
