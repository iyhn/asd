package com.wongnai.interview.movie;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class InvertedIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    protected InvertedIndex() {
    }

    private String keyword;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn
    private Movie movie;

    public Long getId() {
        return id;
    }

    public InvertedIndex(String keyword){
        this.keyword = keyword;
    }

    public InvertedIndex(String keyword,Movie movie){
        this.keyword = keyword;
        this.movie = movie;
    }


}
