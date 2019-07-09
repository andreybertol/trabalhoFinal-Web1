package br.edu.utfpr.pb.trabalhofinal.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Compra implements Serializable{
	private static final long serialVersionUID = -7543363605557353088L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "descricao", length = 1000, nullable = false)
	private String descricao;

	@NotNull(message = "Preencha o campo data da compra!")
	@Column(name = "data_compra", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_compra;

	@OneToMany(mappedBy = "compra",
			cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
			fetch = FetchType.LAZY)
	private List<CompraProduto> compraProdutos;

	@ManyToOne
	@JoinColumn(name = "usuario_id",referencedColumnName = "id")
	private Usuario usuario;

	@NotNull(message = "Preencha o campo fornecedor!")
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
	private Fornecedor fornecedor;

	@Column(name = "valor_total")
    private Double valor_total;

}







