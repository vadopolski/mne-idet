package com.mneidet.repository;

import com.mneidet.model.Alternative;
import com.mneidet.model.QuestionFoto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlternativeRepository extends CrudRepository<Alternative, Long> {

    List<Alternative> findByQuestion(QuestionFoto question);
}
