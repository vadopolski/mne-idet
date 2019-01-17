package com.mneidet.db.repository;

import com.mneidet.db.entity.HintType;
import com.mneidet.db.entity.QuestionFoto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HintTypeRepository extends CrudRepository<HintType, Long> {

    List<HintType> findByQuestion(QuestionFoto question);
}
