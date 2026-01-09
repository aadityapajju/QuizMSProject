package com.example.Quiz_Service.Feign;


import com.example.Quiz_Service.Entity.QuestionWrapper;
import com.example.Quiz_Service.Entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numsQ);

    //getQuestions by questionsID when QuizMS asks for Qustions
    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsfromID(@RequestBody List<Integer> questionIDs);

    //get Score from quiz to question service since right answer is in questionDB
    @PostMapping("questions/score")
    //we need to create entity for response to send only id and response
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
