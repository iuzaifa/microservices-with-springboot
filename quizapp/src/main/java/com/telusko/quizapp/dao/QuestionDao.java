package com.telusko.quizapp.dao;

import com.telusko.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    //    @Query(value = "SELECT * FROM question where category = :category", nativeQuery = true)
    @Query(value = "SELECT * FROM question WHERE LOWER(category) = LOWER(:category)", nativeQuery = true)
    List<Question> getQuestionByCategory(@Param("category") String category);



    @Query( value = "SELECT * FROM question q WHERE q.category =:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionByCategory1(String category, Integer numQ); // not using this is garbage of code

    @Query( value = "SELECT id FROM question q WHERE q.category =:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(String category, Integer numQ);
}
