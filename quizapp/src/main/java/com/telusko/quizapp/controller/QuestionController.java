package com.telusko.quizapp.controller;


import com.telusko.quizapp.Question;
import com.telusko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    // http://localhost:8080/question/allQuestions

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.questionList(), HttpStatus.OK);
    }

    // http://localhost:8080/question/category/java
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    // http://localhost:8080/question/add
    @PostMapping("/add")
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestions(question), HttpStatus.CREATED);
    }
}
