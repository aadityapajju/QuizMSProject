package com.example.Question_Service.Services;



import com.example.Question_Service.Entity.Question;
import com.example.Question_Service.Entity.QuestionWrapper;
import com.example.Question_Service.Entity.Response;
import com.example.Question_Service.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public Page<Question> getQuestionsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepo.findAll(pageable);
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public String addQuestions(Question question) {
        questionRepo.save(question);
        return "success";
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numsQ) {
        List<Integer> questions = questionRepo.findQuestionsByCategory(category,numsQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);
        }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsfromID(List<Integer> questionIDs) {

        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        // Fetch questions by ID
        for (Integer id : questionIDs) {
            questions.add(questionRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Question not found with id: " + id)));
        }

        // Convert Question → QuestionWrapper
        for (Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());

            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;

        for (Response response : responses) {

            Question question = questionRepo
                    .findById(response.getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Question not found with id: " + response.getId()
                    ));

            if (response.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
