package xyz.sangdam.psychologicalTest.entities;

import jakarta.persistence.*;
import lombok.Data;
import xyz.sangdam.psychologicalTest.constants.PsychologicalTestType;

@Data
@Entity
public class TestResultInfo {

    @Id
    @Column(length = 10)
    private Long resultInfoId; // 검사 정보 일련번호

    @Column(length = 7)
    private Long startScore; // 기준 시작 점수

    @Column(length = 7)
    private Long endScore; // 기준 종료 점수

    @Lob
    private String resultDescription; // 결과에 대한 설명

    @Enumerated(EnumType.STRING)
    @Column(length=30)
    private PsychologicalTestType testType;
}
