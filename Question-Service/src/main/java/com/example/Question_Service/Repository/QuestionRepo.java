package com.example.Question_Service.Repository;


import com.example.Question_Service.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(
            value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numsQ",
            nativeQuery = true
    )
    List<Integer> findQuestionsByCategory(
            @Param("category") String category,
            @Param("numsQ") int numsQ
    );

}
