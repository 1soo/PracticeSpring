package com.practice.prc;

import com.practice.prc.answer.AnswerEntity;
import com.practice.prc.answer.AnswerRepository;
import com.practice.prc.question.QuestionEntity;
import com.practice.prc.question.QuestionRepository;
import com.practice.prc.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
class PrcApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Test
    void testJPA() {
        for(int i = 1; i <= 300; i++){
            String subject = String.format("테스트 데이터입니다 : [%03d]", i);
            String content = "내용 무";
            this.questionService.saveQuestion(subject, content, null);
        }
    }

}
