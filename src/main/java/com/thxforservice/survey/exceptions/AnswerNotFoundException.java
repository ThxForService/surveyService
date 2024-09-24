package com.thxforservice.survey.exceptions;

import org.springframework.http.HttpStatus;
import com.thxforservice.global.exceptions.CommonException;

public class AnswerNotFoundException extends CommonException {
    public AnswerNotFoundException() {
        super("NotFound.answer", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
