package br.edu.utfpr.pb.trabalhofinal.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "compraProduto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class CompraProduto implements Serializable {
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
    @JoinColumn(name = "compra_id", referencedColumnName = "id")
    private Compra compra;
}
