package xyz.sangdam.psychologicalTest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.sangdam.global.rests.JSONData;
import xyz.sangdam.psychologicalTest.constants.PsychologicalTestType;

@Tag(name = "PsychologicalTest", description = "심리검사 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/psychological-test")
public class PsychologicalTestController {

    @Operation(summary = "자가진단 심리검사 목록 조회", method = "GET")
    @ApiResponse(responseCode = "200", description = "심리검사 목록 조회")
    @GetMapping("/list")
    public JSONData getTestList() {

        return null;
    }

    @Operation(summary = "심리검사 문항 조회", method = "GET")
    @ApiResponse(responseCode = "200", description = "검사 문항 조회")
    @Parameter(name="testType", required = true, description = "경로변수, 심리검사 종류(testType)", example = "stress")
    @GetMapping("/{testType}")
    public ResponseEntity<JSONData> getTestItems(@PathVariable("testType") PsychologicalTestType testType) {
        switch (testType) {
            case COMPULSION:
                // 강박증 심리검사 문항 조회
                break;
            case EVASION:
                // 사회 공포/회피 심리검사 문항 조회
                break;
            case STRESS:
                // 스트레스 심리검사 문항 조회
                break;
            case INTERNET_ADDICTION:
                // 인터넷 중독 심리검사 문항 조회
                break;
            case EATING_DISORDER:
                // 식이장애 심리검사 문항 조회
                break;
            default:
                return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(null); // 테스트 문항을 반환
    }

    @Operation(summary = "심리검사 답변 저장", method = "POST")
    @ApiResponse(responseCode = "201", description = "답변 저장 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @Parameter(name = "testType", required = true, description = "경로변수, 심리검사 종류(testType)", example = "stress")
    @PostMapping("/{testType}")
    public ResponseEntity<JSONData> saveTestAnswers(
            @PathVariable("testType") PsychologicalTestType testType) {
    }

    @Operation(summary = "심리검사 결과 조회", method = "GET")
    @ApiResponse(responseCode = "200", description = "결과 조회 성공")
    @ApiResponse(responseCode = "404", description = "결과를 찾을 수 없음")
    @Parameter(name = "testType", required = true, description = "경로변수, 심리검사 종류(testType)", example = "stress")
    @GetMapping("/{testType}/result")
    
}

