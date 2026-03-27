package br.com.senac.api.controllers;

import br.com.senac.api.dtos.PessoaRequestDTO;
import br.com.senac.api.entidades.Pessoas;
import br.com.senac.api.repositorios.PessoasRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
@CrossOrigin
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

    @PostMapping("/criar")
    public ResponseEntity<Pessoas> criar (@RequestBody PessoaRequestDTO pessoa){
        Pessoas pessoaPersist = new Pessoas();
        pessoaPersist.setNome(pessoa.getNome());
        pessoaPersist.setSobrenome(pessoa.getSobrenome());

        Pessoas retorno = pessoasRepository.save(pessoaPersist);

        return ResponseEntity.status(201).body(retorno);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pessoas> atualizar(@RequestBody PessoaRequestDTO pessoa, @PathVariable Long id){


        if (pessoasRepository.existsById(id)) {
            Pessoas pessoasPersist = new Pessoas();
            pessoasPersist.setNome(pessoa.getNome());
            pessoasPersist.setSobrenome(pessoa.getSobrenome());
            pessoasPersist.setId(id);

            Pessoas retorno = pessoasRepository.save(pessoasPersist);

            return ResponseEntity.ok(retorno);
        }

        return ResponseEntity.badRequest().body(null);

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        if (pessoasRepository.existsById(id)){
            pessoasRepository.deleteById(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().body(null);

    }

}
