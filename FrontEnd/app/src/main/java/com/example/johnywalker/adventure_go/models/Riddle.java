package com.example.johnywalker.adventure_go.models;

/**
 * Created by JohnyWalker on 12/14/2016.
 */

public class Riddle
{
    private Long id;
    private String question;
    private String answer;
    private String hint;
    private String category;
    private Long points;
    private String difficulty;

    public Riddle(){}

    public Riddle(String question, String answer, String hint, String category, Long points, String difficulty)
    {
        this.question = question;
        this.answer = answer;
        this.hint = hint;
        this.category = category;
        this.points = points;
        this.difficulty = difficulty;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getHint()
    {
        return hint;
    }

    public void setHint(String hint)
    {
        this.hint = hint;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public Long getPoints()
    {
        return points;
    }

    public void setPoints(Long points)
    {
        this.points = points;
    }

    public String getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(String difficulty)
    {
        this.difficulty = difficulty;
    }

    @Override
    public String toString()
    {
        return "Riddle{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", hint='" + hint + '\'' +
                ", category='" + category + '\'' +
                ", points=" + points +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
