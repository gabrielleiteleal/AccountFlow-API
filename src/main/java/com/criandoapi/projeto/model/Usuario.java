package com.criandoapi.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "O campo 'nome' é obrigatório")
    @Size(min = 3, message = "Insira no mínimo 3 caracteres")
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @NotBlank(message = "O campo 'email' é obrigatório")
    @Email(message = "Insira um e-mail válido")
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O campo 'senha' é obrigatório")
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false)
    private String senha;

    @Column(name = "telefone", length = 15, nullable = true)
    private String telefone;

}
