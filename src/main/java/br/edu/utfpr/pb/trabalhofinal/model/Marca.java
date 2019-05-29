package br.edu.utfpr.pb.trabalhofinal.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "marca")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Marca implements Serializable {
    private static final long serialVersionUID = -8727132008984758268L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Preencha o campo nome!")
    @Column(name = "nome", length = 250, nullable = false)
    private String nome;

}





