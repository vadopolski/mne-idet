package com.mneidet.gui;

import com.mneidet.model.QuestionFoto;
import com.mneidet.repository.QuestionFotoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionFotoController {
    private QuestionFotoRepository questionFotoRepository;

    public QuestionFotoController(QuestionFotoRepository questionFotoRepository) {
        this.questionFotoRepository = questionFotoRepository;
    }


    @GetMapping("/questionfoto")
    public List<QuestionFoto> getQuestionFotoList() {
        List<QuestionFoto> questionFotos = new ArrayList<>();
        questionFotoRepository.findAll();
        questionFotoRepository.findAll().forEach(questionFotos::add);
        return questionFotos;
    }


}
