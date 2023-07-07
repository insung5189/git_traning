package com.example.challenge.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
//    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;
//    @ManyToOne
//    private SiteUser author;
//    private LocalDateTime modifyDate;
//    @ManyToMany
//    Set<SiteUser> voter;
}
