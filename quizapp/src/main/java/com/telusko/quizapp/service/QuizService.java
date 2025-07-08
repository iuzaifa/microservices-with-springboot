package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.dao.QuizDao;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;



    public Quiz createQuiz(String category, Integer numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionByCategory1(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        return quizDao.save(quiz);
    }


    public List<QuestionWrapper> getQuizQuestions(Integer quizId) {
        Optional<Quiz> quiz = Optional.ofNullable(quizDao.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found try again" + quizId)));
        List<Question> questionsFromDB = quiz.get().getQuestion();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for (Question question : questionsFromDB){
            QuestionWrapper qr = new QuestionWrapper(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
            );
            questionForUser.add(qr);
        }
        return questionForUser;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestion();

        int i = 0;
        int right = 0;
        for (Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            };
            i++;
        }

        return right;
    }
}
