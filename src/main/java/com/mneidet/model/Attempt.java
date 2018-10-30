package com.mneidet.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Consultant user;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "alternative_id")
    private Alternative alternative;

    @NotNull
    private Date date;

    private boolean correct;
}