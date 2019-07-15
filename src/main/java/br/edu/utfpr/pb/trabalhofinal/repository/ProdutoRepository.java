package br.edu.utfpr.pb.trabalhofinal.repository;

import br.edu.utfpr.pb.trabalhofinal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByCategoria(Integer categoria);
}
