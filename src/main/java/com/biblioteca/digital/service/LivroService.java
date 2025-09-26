package com.biblioteca.digital.service;

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
               usuario.setLivroPedido(livro);
               queueService.adicionarNaFila(usuario);
               usuarioRepository.save(usuario);
               throw new RuntimeException("O livro que você tentou alugar já tem dono.");


           }
           livrosPedidos.add(livro);
           livro.setAlugado(true);
           usuario.setLivrosAlugados(livrosPedidos);
           livro.setUsuario(usuario);
           usuarioRepository.save(usuario);
           livroRepository.save(livro);
           System.out.println("Livro alugado com sucesso!");
       }else {
           throw new EntityNotFoundException("O usuário passado não existe");
       }

    }

    public void devolverLivro(String usuarioId, String livroId){
        var usuarioBuscaId = UUID.fromString(usuarioId);
        var usuarioEntity = usuarioRepository.findById(usuarioBuscaId);
        var livroBuscaId = UUID.fromString(livroId);
        var livroEntity = livroRepository.findById(livroBuscaId);


        if(usuarioEntity.isPresent() && livroEntity.isPresent()){
            var usuario = usuarioEntity.get();
            var livro = livroEntity.get();
        Livro livroPedido = usuario.getLivroPedido();
        /*Pega os usuários que tem livroPedido igual ao livroPedido do usuário que foi passado
        como parâmetro do método, ou seja, só os que quiserem o mesmo livro que o usuário
         estão devolvendo irão retornar
        */
            Optional<Usuario> usuarioComLivroIgual = queueService.getFila()
                    .stream()
                    .findFirst();
        if(usuarioComLivroIgual.isPresent()) {

           var usuarioSatisfeito = usuarioComLivroIgual.get();
           usuarioSatisfeito.setLivroPedido(null);
            Usuario proximoDono = queueService.getFila().remove();
            Set<Livro> livroEntregue = new HashSet<>();
            livroEntregue.add(livro);
            
            proximoDono.setLivrosAlugados(livroEntregue);
            livro.setUsuario(proximoDono);


            //remove o livro em específico dos livros do usuário anterior
            usuario.getLivrosAlugados().remove(livro);

            usuarioRepository.save(usuario);
            usuarioRepository.save(proximoDono);

        }else {
            livro.setUsuario(null);
            livro.setAlugado(false);
            usuario.getLivrosAlugados().remove(livro);

            usuarioRepository.save(usuario);

        }


        }



    }
}
