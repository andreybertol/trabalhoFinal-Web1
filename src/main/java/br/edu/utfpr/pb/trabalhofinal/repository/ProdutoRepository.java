package br.edu.utfpr.pb.trabalhofinal.repository;

import br.edu.utfpr.pb.trabalhofinal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
