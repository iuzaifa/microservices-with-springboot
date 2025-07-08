package com.telusko.quizapp.controller;


import com.telusko.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/quiz")
@RestController
public class QuizController {


    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam String category, @RequestParam Integer numQ, @RequestParam String title ){
        return new ResponseEntity<>(quizService.createQuiz(category, numQ, title), HttpStatus.CREATED);
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<?> getQuizQuestions(@PathVariable Integer quizId){
        return new ResponseEntity<>(quizService.getQuizQuestions(quizId), HttpStatus.OK);
    }
}
