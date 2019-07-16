package br.edu.utfpr.pb.trabalhofinal.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Venda implements Serializable{
	private static final long serialVersionUID = -7543363605557353088L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "usuario_id",referencedColumnName = "id")
	private Usuario usuario;

	@Column(name = "data_venda", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_venda;

	@OneToMany(mappedBy = "venda",
			cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
			fetch = FetchType.LAZY)
	private List<VendaProduto> vendaProdutos;

	@Column(name = "valor_total")
    private Double valor_total;

	@Column(name = "forma_pgto")
	private String forma_pgto;
}







