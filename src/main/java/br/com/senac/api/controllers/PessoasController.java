package br.com.senac.api.controllers;

import br.com.senac.api.entidades.Pessoas;
import br.com.senac.api.repositorios.PessoasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {

    private PessoasRepository pessoasRepository;

    public PessoasController(PessoasRepository pessoasRepository) {
        this.pessoasRepository = pessoasRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoas>> listar() {
        List<Pessoas> pessoasList = pessoasRepository.findAll();

        if (pessoasList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(pessoasList);
    }
}
