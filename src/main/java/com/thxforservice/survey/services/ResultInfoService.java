package com.thxforservice.survey.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.thxforservice.global.CommonSearch;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.member.entities.Member;
import com.thxforservice.survey.entities.Answer;
import com.thxforservice.survey.entities.QAnswer;
import com.thxforservice.survey.exceptions.AnswerNotFoundException;
import com.thxforservice.survey.repositories.AnswerRepository;

import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class ResultInfoService {
    private final HttpServletRequest request;
    private final AnswerRepository answerRepository;
    private final MemberUtil memberUtil;
    private final ObjectMapper om;

    public Answer get(Long resultId) {
        Member student = memberUtil.getMember();

        BooleanBuilder builder = new BooleanBuilder();
        QAnswer answer = QAnswer.answer;
        builder.and(answer.studentNo.eq(student.getStdntNo())
                        .and(answer.resultId.eq(resultId)));

        Answer item = answerRepository.findOne(builder)
                .orElseThrow(AnswerNotFoundException::new);

        addInfo(item);

        return item;
    }

    public ListData<Answer> getList(CommonSearch search) {
        Member student = memberUtil.getMember();
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 10 : limit;

        BooleanBuilder andBuilder = new BooleanBuilder();
        QAnswer answer = QAnswer.answer;
        andBuilder.and(answer.studentNo.eq(student.getStdntNo()));

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("testDate")));

        Page<Answer> data = answerRepository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10, limit, request);

        List<Answer> items = data.getContent();
        items.forEach(this::addInfo);

        return new ListData<>(items, pagination);
    }

    private void addInfo(Answer item) {
        /* 점수대별 설명 */
        Map<String, String> result = item.getTestType().getResult(item.getScore());
        item.setResult(result);

        /* 질문 답변 기록 */
        String questionAndAnswer = item.getQuestionAndAnswer();
        if (StringUtils.hasText(questionAndAnswer)) {
            try {
                Map<Long, Integer> _questionAndAnswer = om.readValue(questionAndAnswer, new TypeReference<>() {});
                item.set_questionAndAnswer(_questionAndAnswer);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}