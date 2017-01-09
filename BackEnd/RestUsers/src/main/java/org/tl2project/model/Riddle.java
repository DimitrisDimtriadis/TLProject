package org.tl2project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "riddles")
public class Riddle implements Serializable{
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "question")
  private String question;
  
  @Column(name = "answer")
  private String answer;
  
  @Column(name = "hint")
  private String hint;
  
  @Column(name = "category")
  private String category;
  
  @Column(name = "points")
  private Long points;
  
  @Column(name = "difficulty")
  private String difficulty;

  public Riddle() {}

  public Riddle(String question, String answer, String hint, String category, int points, String difficulty) {
    this.question = question;
    this.answer = answer; 
    this.hint = hint;
    this.category = category;
    this.difficulty = difficulty;

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
  
  public Long getPoints() {
    return points;
  }

  public void setPoints(Long points) {
    this.points = points;
  }
  
  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }
  
}
