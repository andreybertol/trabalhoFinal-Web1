package br.edu.utfpr.pb.trabalhofinal.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "fornecedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Preencha o campo nome!")
    @Column(name = "nome", length = 250, nullable = false)
    private String nome;

    @NotEmpty(message = "Preencha o campo endereco!")
    @Column(length = 250)
    private String endereco;

    @Column
    private String cnpj;
}
