package com.thxforservice.survey.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.thxforservice.survey.constants.SurveyType;
import com.thxforservice.survey.entities.QQuestion;
import com.thxforservice.survey.entities.Question;
import com.thxforservice.survey.repositories.QuestionRepository;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository repository;

    /**
     * 심리검사 문항 조회
     *
     * @return
     */
    public List<Question> getQuestions(SurveyType type) {
        QQuestion question = QQuestion.question;
        List<Question> items = (List<Question>)repository.findAll(question.testType.eq(type), Sort.by(asc("questionId")));

        return items;
    }


}