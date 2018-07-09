package com.algaworks.cobranca.service;

import com.algaworks.cobranca.Repository.TituloRepository;
import com.algaworks.cobranca.model.Titulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroTituloService {

    @Autowired
    TituloRepository tituloRepository;


    public List<Titulo> findAll() {

        return tituloRepository.findAll();
    }

    public Titulo save(Titulo titulo) {

        try {
            return tituloRepository.save(titulo);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Formato de data inválido");
        }
    }

    public void delete(Titulo titulo) {

        tituloRepository.delete(titulo);
    }
}