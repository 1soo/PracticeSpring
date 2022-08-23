package com.practice.prc.answer;


import com.practice.prc.exception.DataNotFoundException;
import com.practice.prc.question.QuestionEntity;
import com.practice.prc.question.QuestionRepository;
import com.practice.prc.user.SiteUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerEntity saveAnswer(QuestionEntity question, String content, SiteUserEntity author){
        AnswerEntity answer = new AnswerEntity();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    public AnswerEntity getAnswer(Integer id){
        Optional<AnswerEntity> answer = this.answerRepository.findById(id);
        if(answer.isPresent()){
            return answer.get();
        }else{
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modifyAnswer(AnswerEntity answer, String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void deleteAnswer(AnswerEntity answer){
        this.answerRepository.delete(answer);
    }

    public void voteAnswer(AnswerEntity answer, SiteUserEntity siteUser){
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
