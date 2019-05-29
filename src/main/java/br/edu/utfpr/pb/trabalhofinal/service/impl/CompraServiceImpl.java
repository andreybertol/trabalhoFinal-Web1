package br.edu.utfpr.pb.trabalhofinal.service.impl;

import br.edu.utfpr.pb.trabalhofinal.model.Compra;
import br.edu.utfpr.pb.trabalhofinal.repository.CompraRepository;
import br.edu.utfpr.pb.trabalhofinal.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraServiceImpl extends CrudServiceImpl<Compra, Integer>
		implements CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Override
	protected JpaRepository<Compra, Integer> getRepository() {
		return compraRepository;
	}

}
