package com.biblioteca.digital.service;

import com.biblioteca.digital.model.CriarUsuarioDTO;
import com.biblioteca.digital.model.Usuario;
import com.biblioteca.digital.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UUID criarUsuario(CriarUsuarioDTO criarUsuarioDTO){

        var entity = new Usuario();


        entity.setNome(criarUsuarioDTO.nome());
        entity.setCpf(criarUsuarioDTO.cpf());
        entity.setLivrosAlugados(criarUsuarioDTO.livrosAlugados());
        entity.setUpdatedTimeStamp(null);

        var usuarioSaved = usuarioRepository.save(entity);

        return usuarioSaved.getId();
    }
}
