package com.teste.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "familia")
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "renda",nullable = false)
    private int renda;
    @Column(name = "dependentes",nullable = false)
    private int dependentes;
    private int pontuacao;

}
