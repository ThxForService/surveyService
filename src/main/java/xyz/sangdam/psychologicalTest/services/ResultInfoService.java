package xyz.sangdam.psychologicalTest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.global.CommonSearch;
import xyz.sangdam.global.ListData;
import xyz.sangdam.psychologicalTest.entities.Answer;

@Service
@RequiredArgsConstructor
public class ResultInfoService {

    public Answer get(Long resultId) {

        return null;
    }

    public ListData<Answer> getList(CommonSearch search) {

        return null;
    }

}