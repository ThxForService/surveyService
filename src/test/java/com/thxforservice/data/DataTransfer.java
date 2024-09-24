package com.thxforservice.data;

import com.thxforservice.survey.constants.SurveyType;
import com.thxforservice.survey.entities.Question;
import com.thxforservice.survey.repositories.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Scanner;

@SpringBootTest
public class DataTransfer {

    @Autowired
    private QuestionRepository repository;

    @Test
    void tranfer() throws Exception {
        Scanner sc = new Scanner(new File("D:/data/survey.txt"));
        //int EVASION = 0, STRESS = 0, INTERNET_ADDICTION = 0, EATING_DISORDER = 0;
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
