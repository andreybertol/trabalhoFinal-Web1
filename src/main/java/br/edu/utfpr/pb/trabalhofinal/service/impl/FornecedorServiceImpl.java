package br.edu.utfpr.pb.trabalhofinal.service.impl;


import br.edu.utfpr.pb.trabalhofinal.model.Fornecedor;
import br.edu.utfpr.pb.trabalhofinal.repository.FornecedorRepository;
import br.edu.utfpr.pb.trabalhofinal.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FornecedorServiceImpl extends CrudServiceImpl<Fornecedor, Integer>
        implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    protected JpaRepository<Fornecedor, Integer> getRepository() {
        return fornecedorRepository;
    }
}
