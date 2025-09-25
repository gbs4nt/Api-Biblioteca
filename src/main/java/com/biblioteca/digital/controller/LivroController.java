package com.biblioteca.digital.controller;


import com.biblioteca.digital.model.CriarLivroDTO;
import com.biblioteca.digital.model.Livro;
import com.biblioteca.digital.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody CriarLivroDTO criarLivroDTO){
      var livroId =  livroService.criarLivro(criarLivroDTO);

        return ResponseEntity.created(URI.create("/v1/livros/"+livroId.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable("id") String id){

        return null;
    }

    @PutMapping("/{usuarioId}/{livroId}/emprestar")
    public ResponseEntity<Void> emprestarLivro(@PathVariable("usuarioId") String usuarioId,
                                               @PathVariable("livroId") String livroId){
        livroService.alugarLivro(usuarioId,livroId);
        return ResponseEntity.noContent().build();
    }


}
