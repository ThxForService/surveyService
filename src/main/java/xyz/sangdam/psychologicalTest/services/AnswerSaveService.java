package xyz.sangdam.psychologicalTest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.psychologicalTest.controllers.RequestAnswer;
import xyz.sangdam.psychologicalTest.entities.Answer;
import xyz.sangdam.psychologicalTest.entities.Question;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnswerSaveService {

    public void save(RequestAnswer form) {

        Map<Long, Integer> test = form.getAnswers();
        Answer answer = Answer.builder()
                .questionAndAnswer(test.toString())
                .build();

    }

}