package com.thxforservice.survey.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thxforservice.member.entities.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.survey.constants.SurveyType;
import com.thxforservice.survey.controllers.RequestAnswer;
import com.thxforservice.survey.entities.Answer;
import com.thxforservice.survey.repositories.AnswerRepository;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnswerSaveService {

    private final AnswerRepository answerRepository;
    private final MemberUtil memberUtil;
    private final ObjectMapper om;

    @Transactional
    public void save(RequestAnswer form)  {
        Map<Long, Integer> test = form.getAnswers();

        String answerData = null;
        try {
            answerData = om.writeValueAsString(test);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Member member = memberUtil.getMember();
        Answer answer = Answer.builder()
                .questionAndAnswer(answerData)
                .email(member.getEmail())
                .username(member.getUsername())
                .testType(SurveyType.valueOf(form.getTestType()))
                .testDate(LocalDateTime.now())
                .build();

        calculateScore(answer, test);

        answerRepository.save(answer);
    }

    public void calculateScore(Answer answer, Map<Long, Integer> questionAnswerMap) {

        long totalScore = questionAnswerMap.values().stream().mapToLong(Integer::longValue).sum();
        answer.setScore(totalScore);
    }
}