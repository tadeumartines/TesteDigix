package com.teste.demo.controller;

import com.teste.demo.service.CriterioService;
import com.teste.demo.model.Familia;
import com.teste.demo.repository.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/familia")
public class FamiliaController {
    @Autowired
    private FamiliaRepository familiaRepository;
    @Autowired
    private CriterioService criterioService;

    @PostMapping
    public Familia adicionarFamilia(@RequestBody Familia familia) {
        return familiaRepository.save(familia);
    }
    @GetMapping("/avaliar")
    public List<Familia> avaliarFamilias() {
        List<Familia> familias = familiaRepository.findAll();
        for (Familia familia : familias) {
            familia.setPontuacao(criterioService.calcularPontuacaoRenda(familia) + criterioService.calcularPontuacaoDependentes(familia));
        }
        familias.sort((f1, f2) -> f2.getPontuacao() - f1.getPontuacao());
        return familias;
    }
}
