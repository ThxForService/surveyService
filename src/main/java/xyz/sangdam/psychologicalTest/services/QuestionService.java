package xyz.sangdam.psychologicalTest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xyz.sangdam.psychologicalTest.constants.PsychologicalTestType;
import xyz.sangdam.psychologicalTest.entities.QTestQuestion;
import xyz.sangdam.psychologicalTest.entities.Question;
import xyz.sangdam.psychologicalTest.repositories.QuestionRepository;

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
    public List<Question> getQuestions(PsychologicalTestType type) {
        QTestQuestion testQuestion = QTestQuestion.testQuestion;
        List<Question> items = (List<Question>)repository.findAll(testQuestion.testType.eq(type), Sort.by(asc("questionId")));

        return items;
    }


}