package com.quizaap.quiz_service.controller;


import com.quizaap.quiz_service.model.Response;
import com.quizaap.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/quiz")
@RestController
public class QuizController {


    @Autowired
    private QuizService quizService;

//    @PostMapping("/create")
//    public ResponseEntity<?> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title ){
//        return new ResponseEntity<>(quizService.createQuiz(category, numQ, title), HttpStatus.CREATED);
//    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<?> getQuizQuestions(@PathVariable Integer quizId){
        return new ResponseEntity<>(quizService.getQuizQuestions(quizId), HttpStatus.OK);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return new ResponseEntity<>(quizService.calculateResult(id, responses), HttpStatus.OK);
    }
}
