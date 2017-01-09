package com.example.johnywalker.adventure_go.miscellaneous;

import java.util.Random;

/**
 * Created by Dimitriadis983 on 09-Jan-17.
 */

public class Randomize
{
    String answers[];
    Random randomNumber;

    public Randomize(String answerArray[])
    {
        answers = answerArray;
        randomNumber = new Random();
    }

    public String[] shuffleArray()
    {
        int index;
        String temp;

        for(int i = answers.length - 1; i > 0; i--)
        {
            index = randomNumber.nextInt(i+1);

            temp = answers[index];
            answers[index] = answers[i];
            answers[i] = temp;
        }

        return answers;
    }
}
