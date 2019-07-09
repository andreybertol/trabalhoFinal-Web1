package br.edu.utfpr.pb.trabalhofinal.service.impl;

import br.edu.utfpr.pb.trabalhofinal.model.Venda;
import br.edu.utfpr.pb.trabalhofinal.repository.VendaRepository;
import br.edu.utfpr.pb.trabalhofinal.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaServiceImpl extends CrudServiceImpl<Venda, Integer>
		implements VendaService {

	@Autowired
	private VendaRepository VendaRepository;
	
	@Override
	protected JpaRepository<Venda, Integer> getRepository() {
		return VendaRepository;
	}

}
