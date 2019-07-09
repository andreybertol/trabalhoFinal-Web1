package br.edu.utfpr.pb.trabalhofinal.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "vendaProduto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class VendaProduto implements Serializable {
    private static final long serialVersionUID = -7543363605557353088L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double valor;

    @ManyToOne
    @NotNull(message = "Insira um Produto!")
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "venda_id", referencedColumnName = "id")
    private Venda venda;
}
