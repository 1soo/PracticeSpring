package com.practice.prc.answer;


import com.practice.prc.question.QuestionEntity;
import com.practice.prc.user.SiteUserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private QuestionEntity question;

    @ManyToOne
    private SiteUserEntity author;

    @ManyToMany
    Set<SiteUserEntity> voter;
}
