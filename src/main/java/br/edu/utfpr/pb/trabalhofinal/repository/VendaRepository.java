package br.edu.utfpr.pb.trabalhofinal.repository;

import br.edu.utfpr.pb.trabalhofinal.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    List<Venda> findByUsuarioId(Long id);
}
