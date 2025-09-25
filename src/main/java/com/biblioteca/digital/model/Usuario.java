package com.biblioteca.digital.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;


    @OneToMany(mappedBy = "usuario")
    private Set<Livro> livrosAlugados = new HashSet<>();

    @Column(name = "LIVRO_PEDIDO")
    private Livro livroPedido;

    @CreationTimestamp
    private Instant creationTimeStamp;

    public Livro getLivroPedido() {
        return livroPedido;
    }

    public void setLivroPedido(Livro livroPedido) {
        this.livroPedido = livroPedido;
    }

    @UpdateTimestamp
    private Instant updatedTimeStamp;

    public Usuario(){

    }



    public Usuario(UUID id, String nome, String cpf, Set<Livro> livrosAlugados, Instant creationTimeStamp, Instant updatedTimeStamp) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.livrosAlugados = livrosAlugados;
        this.creationTimeStamp = creationTimeStamp;
        this.updatedTimeStamp = updatedTimeStamp;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Livro> getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setLivrosAlugados(Set<Livro> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    public Instant getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Instant creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public Instant getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(Instant updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
}
