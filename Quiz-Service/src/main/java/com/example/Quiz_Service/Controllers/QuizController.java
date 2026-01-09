package com.example.Quiz_Service.Controllers;


import com.example.Quiz_Service.Entity.QuestionWrapper;
import com.example.Quiz_Service.Entity.QuizDTO;
import com.example.Quiz_Service.Entity.Response;
import com.example.Quiz_Service.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/hello")
    public String greetings(){
        return "Welcome to Tech Quiz !!";
    }
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){ //acceptiong obj
        return quizService.createQuiz(quizDTO.getCategory(), quizDTO.getNumsQ(), quizDTO.getTitle());
    }

    @GetMapping("/get/{id}")
    //we don't need to fetch answer for the client we just need questions so we use QuestionWrapper
    public ResponseEntity<List<QuestionWrapper>> getQuestionsBYID(@PathVariable Integer id){
        return quizService.getQuestionsBYID(id);
    }

    @PostMapping("/submit/{id}")
    //we need to create entity for response to send only id and response
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}
