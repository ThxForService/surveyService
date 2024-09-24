package com.thxforservice.survey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.thxforservice.global.ListData;
import com.thxforservice.survey.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>, QuerydslPredicateExecutor<Answer> {

    ListData<Answer> findByStudentNo(String studentNo);
}