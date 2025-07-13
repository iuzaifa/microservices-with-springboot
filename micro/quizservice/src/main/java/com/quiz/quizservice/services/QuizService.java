package com.quiz.quizservice.services;

import com.quiz.quizservice.entity.Quiz;

import java.util.List;

public interface QuizService {

    Quiz add(Quiz quiz);


    List<Quiz> getAll();

    Quiz getById(Long id);
}
