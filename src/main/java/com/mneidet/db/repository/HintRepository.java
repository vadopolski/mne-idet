package com.mneidet.db.repository;

import com.mneidet.db.entity.HintType;
import com.mneidet.db.entity.Hint;
import com.mneidet.db.entity.Consultant;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface HintRepository extends CrudRepository<Hint, Long> {

    List<Hint> findAllByDate(Date date);

    List<HintType> findAllByUser(Consultant user);
}
