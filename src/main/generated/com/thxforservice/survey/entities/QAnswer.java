package com.thxforservice.survey.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnswer is a Querydsl query type for Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = 797461186L;

    public static final QAnswer answer = new QAnswer("answer");

    public final StringPath email = createString("email");

    public final StringPath questionAndAnswer = createString("questionAndAnswer");

    public final NumberPath<Long> resultId = createNumber("resultId", Long.class);

    public final NumberPath<Long> score = createNumber("score", Long.class);

    public final DateTimePath<java.time.LocalDateTime> testDate = createDateTime("testDate", java.time.LocalDateTime.class);

    public final EnumPath<com.thxforservice.survey.constants.SurveyType> testType = createEnum("testType", com.thxforservice.survey.constants.SurveyType.class);

    public QAnswer(String variable) {
        super(Answer.class, forVariable(variable));
    }

    public QAnswer(Path<? extends Answer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnswer(PathMetadata metadata) {
        super(Answer.class, metadata);
    }

}

