package com.mneidet.repository;

import com.mneidet.model.Alternative;
import com.mneidet.model.Attempt;
import com.mneidet.model.Consultant;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AttemptRepository extends CrudRepository<Attempt, Long> {

    List<Attempt> findAllByDate(Date date);

    List<Alternative> findAllByUser(Consultant user);
}
