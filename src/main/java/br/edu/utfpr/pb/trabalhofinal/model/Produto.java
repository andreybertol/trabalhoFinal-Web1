package br.edu.utfpr.pb.trabalhofinal.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Produto implements Serializable{
	private static final long serialVersionUID = -7543363605557353088L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Preencha o campo nome!")
	@Column(name = "nome", length = 250, nullable = false)
	private String nome;

	@Column(name = "detalhes", length = 250, nullable = false)
	private String detalhes;

	@NotNull(message = "Preencha o campo valor!")
	@Column(name = "valor", nullable = false)
	private double valor;

	@NotNull(message = "Preencha o campo estoque!")
	@Column(name = "estoque", nullable = false)
	private double estoque;
	
	@NotNull(message = "Preencha o campo categoria!")
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;
	
	@NotNull(message = "Preencha o campo marca!")
	@ManyToOne
	@JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marca;

	@Column(name = "imagem", length = 100, nullable = true)
	private String imagem;
}







