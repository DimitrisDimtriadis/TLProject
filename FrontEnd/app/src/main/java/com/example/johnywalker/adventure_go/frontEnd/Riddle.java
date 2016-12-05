package com.example.johnywalker.adventure_go.frontEnd;

/**
 * Created by rezu on 5/12/2016.
 */

public class Riddle {
    private int id,points;
    private String question, answer , hint, category,difficulty;

    public Riddle(int id, int points, String question, String answer, String hint, String category,String difficulty){
        this.setId(id);
        this.setPoints(points);
        this.setAnswer(answer);
        this.setCategory(category);
        this.setHint(hint);
        this.setQuestion(question);
        this.setDifficulty(difficulty);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty= difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
