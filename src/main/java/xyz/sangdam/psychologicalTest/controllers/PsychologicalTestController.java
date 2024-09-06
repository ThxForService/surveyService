package xyz.sangdam.psychologicalTest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PsychologicalTest", description = "심리검사 API")
@RestController
@RequestMapping("/psychologicalTest")
@RequiredArgsConstructor
public class PsychologicalTestController {

}
