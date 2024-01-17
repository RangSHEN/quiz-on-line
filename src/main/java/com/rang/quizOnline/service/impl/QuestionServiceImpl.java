package com.rang.quizOnline.service.impl;

import com.rang.quizOnline.dao.QuestionDao;
import com.rang.quizOnline.entity.Question;
import com.rang.quizOnline.service.IQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements IQuestionService {

    private QuestionDao questionDao;
    @Override
    public Question createQuestion(Question question) {
        return questionDao.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionDao.findById(id);
    }

    @Override
    public List<String> getAllSubjects() {
        return questionDao.findDistinctSubject();
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Optional<Question> theQuestion = questionDao.findById(id);
        if(theQuestion.isPresent()){
            Question updatedQuestion = theQuestion.get();
            updatedQuestion.setQuestion(question.getQuestion());
            updatedQuestion.setChoices(question.getChoices());
            updatedQuestion.setCorrectAnswers(question.getCorrectAnswers());
            return questionDao.save(updatedQuestion);
        }else {
            throw new NoSuchElementException();
        }


    }

    @Override
    public void deleteQuestion(Long id) {
        questionDao.deleteById(id);
    }

    @Override
    public List<Question> getQuestionsForUser(Integer numOfQuestions, String subject) {
        Pageable pageable = PageRequest.of(0, numOfQuestions);
        return questionDao.findBySubject(subject,pageable).getContent();
    }
}
