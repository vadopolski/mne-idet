package com.mneidet.repository;

import com.mneidet.model.HintType;
import com.mneidet.model.Hint;
import com.mneidet.model.Consultant;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface HintRepository extends CrudRepository<Hint, Long> {

    List<Hint> findAllByDate(Date date);

    List<HintType> findAllByUser(Consultant user);
}
