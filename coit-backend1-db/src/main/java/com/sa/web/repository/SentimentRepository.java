package com.sa.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.web.model.Sentiment;

public interface SentimentRepository extends JpaRepository<Sentiment, Long>{

}
