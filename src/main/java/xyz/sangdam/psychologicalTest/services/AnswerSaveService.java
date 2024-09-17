package xyz.sangdam.psychologicalTest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Student;
import xyz.sangdam.psychologicalTest.constants.PsychologicalTestType;
import xyz.sangdam.psychologicalTest.controllers.RequestAnswer;
import xyz.sangdam.psychologicalTest.entities.Answer;
import xyz.sangdam.psychologicalTest.repositories.AnswerRepository;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnswerSaveService {

    private final AnswerRepository answerRepository;
    private final MemberUtil memberUtil;
    private final ObjectMapper om;

    @Transactional
    public void save(RequestAnswer form) throws Exception {
        Map<Long, Integer> test = form.getAnswers();
        Student student = memberUtil.getMember();
        String answerData = om.writeValueAsString(test);

        // Create Answer object with initial values
        Answer answer = Answer.builder()
                .questionAndAnswer(answerData)
                .studentNo(memberUtil.getStudentNo())
                .testType(PsychologicalTestType.valueOf(form.getTestType()))
                .testDate(LocalDateTime.now())
                .build();

        calculateScore(answer);

        answerRepository.save(answer);
    }

    public void calculateScore(Answer answer) throws Exception {
        Map<Long, Integer> questionAnswerMap = om.readValue(answer.getQuestionAndAnswer(), Map.class);

        long totalScore = questionAnswerMap.values().stream().mapToLong(Integer::longValue).sum();
        answer.setScore(totalScore);
    }
}