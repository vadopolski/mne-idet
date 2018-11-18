package com.mneidet.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Alternative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionFoto question;

    private long order;

    @NotNull
    private String description;

    private boolean correct;
}