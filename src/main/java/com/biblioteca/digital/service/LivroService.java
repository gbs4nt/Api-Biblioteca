package com.biblioteca.digital.service;

import com.biblioteca.digital.model.AtualizarUsuarioDTO;
import com.biblioteca.digital.model.CriarLivroDTO;
import com.biblioteca.digital.model.Livro;
import com.biblioteca.digital.model.Usuario;
import com.biblioteca.digital.repository.LivroRepository;
import com.biblioteca.digital.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LivroService {

    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;

    private Queue<Usuario> usuariosNaFila;
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public UUID criarLivro(CriarLivroDTO criarLivroDTO){

        //Conversão de DTO pra Entity

       Livro entity = new Livro();
       entity.setTitulo(criarLivroDTO.titulo());
       entity.setPreco(criarLivroDTO.preco());
       entity.setNomeAutor(criarLivroDTO.nomeAutor());;
       entity.setGenero(criarLivroDTO.genero());
       entity.setAlugado(criarLivroDTO.alugado());

        Livro livroSaved = livroRepository.save(entity);
        return livroSaved.getId();
    }

    public void alugarLivro(String usuarioId,AtualizarUsuarioDTO atualizarUsuarioDTO, Livro livro){

        var id = UUID.fromString(usuarioId);
        usuarioRepository.existsById(id);



        Set<Livro> livrosPedidos = new HashSet<>();
        if(livro.isAlugado()) {
          usuariosNaFila.add(usuario);
          usuario.setLivroPedido(livro);
          throw new RuntimeException("O livro que você tentou alugar já tem dono.");
        }
        livrosPedidos.add(livro);
        usuario.setLivrosAlugados(livrosPedidos);

    }

    public void devolverLivro(Usuario usuario, Queue<Usuario> usuariosNaFila){
        Livro livroPedido = usuario.getLivroPedido();
        Set<Livro> livrosAlugados = usuario.getLivrosAlugados();
        List<Usuario> usuarioStream = usuariosNaFila.stream()
                .filter(usuarioNaFila -> usuarioNaFila.getLivroPedido().equals(livroPedido))
                .toList();
        if(!usuarioStream.isEmpty()){

            Usuario removed = usuariosNaFila.remove();
            removed.setLivrosAlugados(usuario.getLivrosAlugados());
            usuario.setLivrosAlugados(null);


        }



    }
}
