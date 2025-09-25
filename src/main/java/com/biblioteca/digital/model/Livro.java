package com.biblioteca.digital.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name= "LIVROS")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "TITULO", nullable = false, updatable = false)
    private String titulo;

    @Column(name = "PREÇO", nullable = false)
    private Double preco;


    @Column(name = "NOME_AUTOR", nullable = false)
    private String nomeAutor;

    @JoinColumn(name = "USUARIO_ID")
    @ManyToOne
    private Usuario usuario;

    @Column(name = "GÊNERO")
    private String genero;

    @Column(name = "ALUGADO")
    private boolean alugado;

    public Livro() {
    }

    public Livro(UUID id, String titulo, Double preco, String nomeAutor, Usuario usuario, String genero, boolean alugado) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.nomeAutor = nomeAutor;
        this.usuario = usuario;
        this.genero = genero;
        this.alugado = alugado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return alugado == livro.alugado && Objects.equals(id, livro.id) && Objects.equals(titulo, livro.titulo) && Objects.equals(preco, livro.preco) && Objects.equals(nomeAutor, livro.nomeAutor) && Objects.equals(usuario, livro.usuario) && Objects.equals(genero, livro.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, preco, nomeAutor, usuario, genero, alugado);
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


}
