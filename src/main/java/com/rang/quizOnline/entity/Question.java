package com.rang.quizOnline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String question;
    @NotBlank
    private String subject;

    //single or mutiple answer
    @NotBlank
    private String questionType;


    @ElementCollection //map to have a separate table,this annotation is used for mapping of strings onetoMany
    private List<String> choices;


    @ElementCollection
    //@CollectionTable
    private List<String> correctAnswers;


}
