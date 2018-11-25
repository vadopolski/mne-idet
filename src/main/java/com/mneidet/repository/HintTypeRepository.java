package com.mneidet.repository;

import com.mneidet.model.HintType;
import com.mneidet.model.QuestionFoto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HintTypeRepository extends CrudRepository<HintType, Long> {

    List<HintType> findByQuestion(QuestionFoto question);
}
