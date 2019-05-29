package br.edu.utfpr.pb.trabalhofinal.service.impl;

import br.edu.utfpr.pb.trabalhofinal.model.Marca;
import br.edu.utfpr.pb.trabalhofinal.repository.MarcaRepository;
import br.edu.utfpr.pb.trabalhofinal.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl extends CrudServiceImpl<Marca, Integer>
		implements MarcaService{

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	protected JpaRepository<Marca, Integer> getRepository() {
		return marcaRepository;
	}

}
