package com.example.Question_Service.Controllers;



import com.example.Question_Service.Entity.Question;
import com.example.Question_Service.Entity.QuestionWrapper;
import com.example.Question_Service.Entity.Response;
import com.example.Question_Service.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/hello")
    public String greetings(){
        return "Welcome to Tech Quiz !!";
    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }


    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/paginated")
    public Page<Question> getQuestionsPaginated(
            @RequestParam int page,
            @RequestParam int size) {
        return questionService.getQuestionsPaginated(page, size);
    }

    @PostMapping("/addQuestions")
    public ResponseEntity<String> addQuestions(@RequestBody Question question) {
        questionService.addQuestions(question);
        return ResponseEntity.ok("Question saved successfully");
    }

    //generate quiz
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numsQ){
        return questionService.getQuestionsForQuiz(category, numsQ);
    }
    //getQuestions by questionsID when QuizMS asks for Qustions
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsfromID(@RequestBody List<Integer> questionIDs){
        return questionService.getQuestionsfromID(questionIDs);
    }
    //get Score from quiz to question service since right answer is in questionDB
    @PostMapping("/score")
    //we need to create entity for response to send only id and response
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
