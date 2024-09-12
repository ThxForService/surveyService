package xyz.sangdam.psychologicalTest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.sangdam.psychologicalTest.entities.Answer;

public interface AnswerRepository extends JpaRepository<AnswerRepository, Long>, QuerydslPredicateExecutor<Answer> {
}