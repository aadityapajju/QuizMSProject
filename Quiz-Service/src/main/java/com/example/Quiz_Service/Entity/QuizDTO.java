package com.example.Quiz_Service.Entity;

public class QuizDTO {

    private String category;
    private Integer numsQ;
    private String title;

    public QuizDTO(){}
    public QuizDTO(String category, Integer numsQ, String title) {
        this.category = category;
        this.numsQ = numsQ;
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumsQ() {
        return numsQ;
    }

    public void setNumsQ(Integer numsQ) {
        this.numsQ = numsQ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
