package com.telusko.quizapp.controller;


import com.telusko.quizapp.Question;
import com.telusko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    // http://localhost:8080/question/allQuestions

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.questionList();
    }

    // http://localhost:8080/question/category/java
    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
}
