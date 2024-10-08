package com.thxforservice.survey.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.thxforservice.global.CommonSearch;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import com.thxforservice.survey.constants.SurveyType;
import com.thxforservice.survey.entities.Answer;
import com.thxforservice.survey.entities.Question;
import com.thxforservice.survey.services.AnswerSaveService;
import com.thxforservice.survey.services.QuestionService;
import com.thxforservice.survey.services.ResultInfoService;

import java.util.List;

@Tag(name = "Survey", description = "설문 API")
@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final QuestionService questionService;
    private final AnswerSaveService saveService;
    private final ResultInfoService infoService;
    private final Utils utils;

    @Operation(summary = "자가진단 심리검사 목록 조회", method = "GET")
    @ApiResponse(responseCode = "200", description = "심리검사 목록 조회")
    @GetMapping("/list")
    public JSONData getTestList() {
        List<String[]> data = SurveyType.getList();

        return new JSONData(data);
    }

    @Operation(summary = "심리검사 문항 조회", method = "GET")
    @ApiResponse(responseCode = "200", description = "검사 문항 조회")
    @Parameter(name = "testType", required = true, description = "경로변수, 심리검사 종류(type)", example = "stress")
    @GetMapping("/{type}")
    public JSONData getTestItems(@PathVariable("type") String type) {
        List<Question> items = questionService.getQuestions(SurveyType.valueOf(type));

        return new JSONData(items);
    }

    @Operation(summary = "심리검사 답변 저장", method = "POST")
    @ApiResponse(responseCode = "201", description = "답변 저장 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @PostMapping("/answer")
    public ResponseEntity<JSONData> saveTestAnswers(
            @Valid @RequestBody RequestAnswer answer, Errors errors) {

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        JSONData data = new JSONData();
        Answer result = saveService.save(answer);
        HttpStatus status = HttpStatus.CREATED;

        data.setData(result);
        data.setStatus(status);

        return ResponseEntity.status(status).body(data);
    }


    @Operation(summary = "심리검사 테스트 결과 목록", method = "GET")
    @ApiResponse(responseCode = "200", description = "검사 결과 목록 조회")
    @GetMapping("/answers")
    public JSONData Answers(CommonSearch search) {
        ListData<Answer> results = infoService.getList(search);
        return new JSONData(results);
    }

    @Operation(summary = "심리검사 테스트 결과 조회", method = "GET")
    @ApiResponse(responseCode = "200", description = "심리검사 결과 조회 성공")
    @ApiResponse(responseCode = "404", description = "검사 결과 찾을 수 없음")
    @Parameter(name = "resultId", required = true, description = "경로변수, 검사결과 일련번호(resultId)", example = "1234")
    @GetMapping("/answer/{resultId}")
    public JSONData Answer(@PathVariable("resultId") Long resultId) {
        Answer answer = infoService.get(resultId);

        return new JSONData(answer);
    }
}