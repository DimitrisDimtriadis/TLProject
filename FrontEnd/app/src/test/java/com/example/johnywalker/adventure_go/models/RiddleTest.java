package com.example.johnywalker.adventure_go.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dimitriadis983 on 14-Jan-17.
 */
public class RiddleTest
{
    private Riddle riddle;

    private int id, points;
    private String question, answer, hint, category, difficulty;

    private int newID, newPoints;
    private String newQuestion, newAnswer, newHint, newCategory, newDifficulty;

    @Before
    public void initializeVariables()
    {
        id = 1;
        points = 10;
        question = "Who is the best professor";
        answer = "Petalidis";
        hint = "Software technology";
        category = "Best";
        difficulty = "easy";

        riddle = new Riddle(id, points, question, answer, hint, category, difficulty);

        newID = 2;
        newPoints = 5;
        newQuestion = "Who is better than Petalidis";
        newAnswer = "noone";
        newHint = "0";
        newCategory = "Better";
        newDifficulty = "very easy";
    }

    @Test
    public void getId() throws Exception
    {
        assertEquals(id, riddle.getId());
    }

    @Test
    public void setId() throws Exception
    {
        riddle.setId(newID);
        assertEquals(newID, riddle.getId());
    }

    @Test
    public void getPoints() throws Exception
    {
        assertEquals(points, riddle.getPoints());
    }

    @Test
    public void setPoints() throws Exception
    {
        riddle.setPoints(newPoints);
        assertEquals(newPoints, riddle.getPoints());
    }

    @Test
    public void getQuestion() throws Exception
    {
        assertEquals(question, riddle.getQuestion());
    }

    @Test
    public void setQuestion() throws Exception
    {
        riddle.setQuestion(newQuestion);
        assertEquals(newQuestion, riddle.getQuestion());
    }

    @Test
    public void getAnswer() throws Exception
    {
        assertEquals(answer, riddle.getAnswer());
    }

    @Test
    public void setAnswer() throws Exception
    {
        riddle.setAnswer(newAnswer);
        assertEquals(newAnswer, riddle.getAnswer());
    }

    @Test
    public void getHint() throws Exception
    {
        assertEquals(hint, riddle.getHint());
    }

    @Test
    public void setHint() throws Exception
    {
        riddle.setHint(newHint);
        assertEquals(newHint, riddle.getHint());
    }

    @Test
    public void getCategory() throws Exception
    {
        assertEquals(category, riddle.getCategory());
    }

    @Test
    public void setCategory() throws Exception
    {
        riddle.setCategory(newCategory);
        assertEquals(newCategory, riddle.getCategory());
    }

    @Test
    public void setDifficulty() throws Exception
    {
        riddle.setDifficulty(newDifficulty);
        assertEquals(newDifficulty, riddle.getDifficulty());
    }

    @Test
    public void getDifficulty() throws Exception
    {
        assertEquals(difficulty, riddle.getDifficulty());
    }

    @After
    public void nullifyVariables()
    {
        question = null;
        answer = null;
        hint = null;
        category = null;
        difficulty = null;

        riddle = null;

        newQuestion = null;
        newAnswer = null;
        newHint = null;
        newCategory = null;
        newDifficulty = null;
    }

}