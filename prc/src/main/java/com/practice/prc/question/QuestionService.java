package com.practice.prc.question;

import com.practice.prc.answer.AnswerEntity;
import com.practice.prc.exception.DataNotFoundException;
import com.practice.prc.user.SiteUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private Specification<QuestionEntity> search(String kw){
        return new Specification<>(){
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<QuestionEntity> q, CriteriaQuery<?> query, CriteriaBuilder cb){
                query.distinct(true);
                Join<QuestionEntity, SiteUserEntity> u1 = q.join("author", JoinType.LEFT);
                Join<QuestionEntity, AnswerEntity> a = q.join("answerList", JoinType.LEFT);
                Join<AnswerEntity, SiteUserEntity> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"),
                        cb.like(q.get("content"), "%" + kw + "%"),
                        cb.like(u1.get("username"), "%" + kw + "%"),
                        cb.like(a.get("content"), "%" + kw + "%"),
                        cb.like(u2.get("username"), "%" + kw + "%"));
            }
        };
    }

    public Page<QuestionEntity> getQuestionList(int page, String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 15, Sort.by(sorts));
        Specification<QuestionEntity> spec = search(kw);
        return this.questionRepository.findAll(spec, pageable);
    }

    public QuestionEntity getQuestion(Integer id){
        Optional<QuestionEntity> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("question is not found");
        }
    }

    public void saveQuestion(String subject, String content, SiteUserEntity user){
        QuestionEntity question = new QuestionEntity();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setAuthor(user);
        this.questionRepository.save(question);
    }

    public void modifyQuestion(QuestionEntity question, String subject, String content){
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void deleteQuestion(QuestionEntity question){
        this.questionRepository.delete(question);
    }

    public void voteQuestion(QuestionEntity question, SiteUserEntity siteUser){
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }
}
