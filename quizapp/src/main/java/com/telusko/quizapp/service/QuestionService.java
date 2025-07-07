package com.telusko.quizapp.service;

import com.telusko.quizapp.Question;
import com.telusko.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;


    public List<Question> questionList(){
        return questionDao.findAll();
    };


    public List<Question> getQuestionsByCategory(String category){
        List<Question> question = questionDao.getQuestionByCategory(category);
        return Optional.of(question)
                .filter(guestionList -> !questionList().isEmpty())
                .orElseThrow(()-> new RuntimeException("Category not found : "  + category));

    }



}
