package com.biblioteca.digital.controller;


import com.biblioteca.digital.model.CriarUsuarioDTO;
import com.biblioteca.digital.model.Usuario;
import com.biblioteca.digital.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody CriarUsuarioDTO criarUsuarioDTO){


      var usuarioId =  usuarioService.criarUsuario(criarUsuarioDTO);

        return ResponseEntity.created(URI.create("/usuarios/"+usuarioId.toString())).build();

    }
}
