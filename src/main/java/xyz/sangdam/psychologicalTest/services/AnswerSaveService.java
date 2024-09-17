package xyz.sangdam.psychologicalTest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.psychologicalTest.controllers.RequestAnswer;
import xyz.sangdam.psychologicalTest.entities.Answer;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnswerSaveService {

    private final ObjectMapper om;

    public void save(RequestAnswer form) throws Exception {

        Map<Long, Integer> test = form.getAnswers();

        String answerData = om.writeValueAsString(test);

        // System.out.println("JSON Data: " + answerData);

        Answer answer = Answer.builder()
                .questionAndAnswer(answerData)
                .build();
    }

}