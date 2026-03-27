package br.com.senac.api.controllers;

import br.com.senac.api.dtos.AnimaisRequestDTO;
import br.com.senac.api.entidades.Animais;
import br.com.senac.api.repositorios.AnimaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animais")
@CrossOrigin

public class AnimaisController {
    private AnimaisRepository animaisRepository;

    public AnimaisController(AnimaisRepository animaisRepository) {
        this.animaisRepository = animaisRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Animais>> listar() {
        List<Animais> animaisList = animaisRepository.findAll();

        if (animaisList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(animaisList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Animais> criar (@RequestBody AnimaisRequestDTO animal){
        Animais animaisPersist = new Animais();
        animaisPersist.setNome(animal.getNome());
        animaisPersist.setRaca(animal.getRaca());
        animaisPersist.setPeso(animal.getPeso());

        Animais retorno = animaisRepository.save(animaisPersist);

        return ResponseEntity.status(201).body(retorno);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Animais> atualizar(@RequestBody AnimaisRequestDTO animal, @PathVariable Long id){


        if (animaisRepository.existsById(id)) {
            Animais animaisPersist = new Animais();
            animaisPersist.setNome(animal.getNome());
            animaisPersist.setRaca(animal.getRaca());
            animaisPersist.setPeso(animal.getPeso());
            animaisPersist.setId(id);

            Animais retorno = animaisRepository.save(animaisPersist);

            return ResponseEntity.ok(retorno);
        }

        return ResponseEntity.badRequest().body(null);

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        if (animaisRepository.existsById(id)){
            animaisRepository.deleteById(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().body(null);

    }

}