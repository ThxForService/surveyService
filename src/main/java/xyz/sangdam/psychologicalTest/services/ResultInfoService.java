package xyz.sangdam.psychologicalTest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.global.ListData;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Student;
import xyz.sangdam.psychologicalTest.entities.Answer;
import xyz.sangdam.psychologicalTest.repositories.AnswerRepository;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;

@Service
@RequiredArgsConstructor
public class ResultInfoService {
    private final AnswerRepository answerRepository;
    private final MemberUtil memberUtil;

    public Answer get(Long resultId) {

        return null;
    }

    public ListData<Answer> getList() {
        Student student = memberUtil.getMember();
        ListData<Answer> results = answerRepository.findByStudentId(student.getStdntNo());
        return results;
    }
}