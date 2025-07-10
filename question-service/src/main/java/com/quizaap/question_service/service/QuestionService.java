package com.telusko.quizapp.service;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public String addQuestions(Question question){
        questionDao.save(question);
        return "SUCCESS TO ADD QUESTIONS";
    }


    public List<Integer> getQuestionForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionByCategory(categoryName, numQuestions);
        return questions;
    }

    public List<QuestionWrapper> getQuestionFromId(List<Integer> questionId) {

        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (Integer id : questionId){
            questions.add(questionDao.findById(id).get());
        }
        for (Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return wrappers;
    }

    public Integer getScore(List<Response> responses) {
        int score = 0;
        for (Response response : responses){
            Question question = questionDao.findById(response.getId()).get();
            if (response.getResponse().equals(question.getRightAnswer())){
                score++;
            }
        }
        return score;
    }
}

