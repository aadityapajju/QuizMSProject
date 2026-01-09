package com.example.Quiz_Service.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ElementCollection
    private List<Integer> question;

    public Quiz(){
    }

    public Quiz(Integer id, String title, List<Integer> question) {
        this.id = id;
        this.title = title;
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestion() {
        return question;
    }

    public void setQuestion(List<Integer> question) {
        this.question = question;
    }
}
