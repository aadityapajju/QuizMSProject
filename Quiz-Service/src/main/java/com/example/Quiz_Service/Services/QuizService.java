package com.example.Quiz_Service.Services;


import com.example.Quiz_Service.Entity.Quiz;
import com.example.Quiz_Service.Feign.QuizInterface;
import com.example.Quiz_Service.Repository.QuizRepo;
import com.example.Quiz_Service.Entity.Response;
import com.example.Quiz_Service.Entity.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numsQ, String title) {
        // we need to call the "/generate" API from question service to create quiz using "rest template",
        // http://localhost:9009/questions/generate
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numsQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsBYID(Integer id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questionIds = quiz.getQuestion();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsfromID(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
