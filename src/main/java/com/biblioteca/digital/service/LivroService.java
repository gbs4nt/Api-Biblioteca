package com.biblioteca.digital.service;

import com.biblioteca.digital.model.AtualizarUsuarioDTO;
import com.biblioteca.digital.model.CriarLivroDTO;
import com.biblioteca.digital.model.Livro;
import com.biblioteca.digital.model.Usuario;
import com.biblioteca.digital.repository.LivroRepository;
import com.biblioteca.digital.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LivroService {

    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;

    private QueueService queueService;

    public LivroService(LivroRepository livroRepository, UsuarioRepository usuarioRepository, QueueService queueService) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.queueService = queueService;
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

    public void alugarLivro(String usuarioId, String livroId){

        var usuarioBuscaId = UUID.fromString(usuarioId);
       var usuarioEntity = usuarioRepository.findById(usuarioBuscaId);
       var livroBuscaId = UUID.fromString(livroId);
       var livroEntity = livroRepository.findById(livroBuscaId);

       if(usuarioEntity.isPresent() && livroEntity.isPresent()) {
           var usuario = usuarioEntity.get();
           var livro = livroEntity.get();
           Set<Livro> livrosPedidos = new HashSet<>();

           if (livro.isAlugado()) {
               queueService.adicionarNaFila(usuario);
               usuario.setLivroPedido(livro);
               throw new RuntimeException("O livro que você tentou alugar já tem dono.");
           }
           livrosPedidos.add(livro);
           livro.setAlugado(true);
           usuario.setLivrosAlugados(livrosPedidos);
           livro.setUsuario(usuario);
           System.out.println("Livro alugado com sucesso!");
           usuarioRepository.save(usuario);
           livroRepository.save(livro);
       }else {
           throw new EntityNotFoundException("O usuário passado não existe");
       }

    }

//    public void devolverLivro(Usuario usuario, Queue<Usuario> usuariosNaFila){
//        Livro livroPedido = usuario.getLivroPedido();
//        Set<Livro> livrosAlugados = usuario.getLivrosAlugados();
//        List<Usuario> usuarioStream = usuariosNaFila.stream()
//                .filter(usuarioNaFila -> usuarioNaFila.getLivroPedido().equals(livroPedido))
//                .toList();
//        if(!usuarioStream.isEmpty()){
//
//            Usuario removed = usuariosNaFila.remove();
//            removed.setLivrosAlugados(usuario.getLivrosAlugados());
//            usuario.setLivrosAlugados(null);
//
//
//        }
//
//
//
//    }
}
