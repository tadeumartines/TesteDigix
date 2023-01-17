package com.teste.demo.service;

import com.teste.demo.model.Familia;
import org.springframework.stereotype.Service;

@Service
public class CriterioService {


	public int calcularPontuacaoRenda(Familia familia) {
		int renda = familia.getRenda();
		if (renda <= 900) {
			return 5;
		} else if (renda <= 1500) {
			return 3;
		}
		return 0;
	}

	public int calcularPontuacaoDependentes(Familia familia) {
		int dependentes = familia.getDependentes();
		if (dependentes >= 3) {
			return 3;
		} else if (dependentes >= 1) {
			return 2;
		}
		return 0;
	}
}
