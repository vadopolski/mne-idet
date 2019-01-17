package com.mneidet.db.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Consultant {

    @Id
    private String id;
}
