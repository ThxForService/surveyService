package com.thxforservice.data;

import com.thxforservice.survey.constants.SurveyType;
import com.thxforservice.survey.entities.Question;
import com.thxforservice.survey.repositories.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@SpringBootTest
public class DataTransfer {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private ResourceLoader resourceLoader;  // 리소스 로더 주입

    @Test
    void tranfer() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:static/files/survey.txt");
       // int COMPULSION =0, EVASION = 0, STRESS = 0, INTERNET_ADDICTION = 0, EATING_DISORDER = 0;
        try (InputStream inputStream = resource.getInputStream();
             Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split("\\|\\|");
                String type = data[0];
                String q = data[1];

                Question item = Question.builder()
                        .questionText(q)
                        .testType(SurveyType.valueOf(type))
                        .build();

                repository.saveAndFlush(item);
            }
        }
    }
}