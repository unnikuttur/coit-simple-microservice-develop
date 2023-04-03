package com.sa.web;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sa.web.dto.SentenceDto;
import com.sa.web.dto.SentimentDto;
import com.sa.web.model.Sentiment;
import com.sa.web.repository.SentimentRepository;

@CrossOrigin(origins = "*")
@RestController
public class SentimentController {

    @Value("${sa.logic.api.url}")
    private String saLogicApiUrl;
    @Autowired
    private SentimentRepository repo;

    @PostMapping("/sentiment")
    public SentimentDto sentimentAnalysis(@RequestBody SentenceDto sentenceDto) {
        RestTemplate restTemplate = new RestTemplate();
        
        

        SentimentDto dto= restTemplate.postForEntity(saLogicApiUrl + "/analyse/sentiment",
                sentenceDto, SentimentDto.class)
                .getBody();
		try {
			Sentiment sentiment = new Sentiment();
			sentiment.setMessage(dto.getSentence());
			sentiment.setPolariy(dto.getPolarity());
			repo.save(sentiment);
		} catch (Exception ae) {
			System.out.println(ae);
		}
        
        return dto;
        
        
       
    }

    @GetMapping("/testHealth")
    public void testHealth() {
    }
    @GetMapping("/sentimentList/{firstRow}/{noOfRows}")
    public List<SentimentDto> getSentimentList(@PathVariable("firstRow") int firstRow, @PathVariable("noOfRows") int noOfRows) {
    	
    	Pageable  paging=new PageRequest(firstRow, noOfRows, new Sort(Sort.Direction.DESC, "id")); // older version 1.5.2 RELEASE
    	
		Page<Sentiment>pageResult=repo.findAll(paging);
		if(pageResult.hasContent())
		{
			return pageResult.getContent().stream().filter(Objects::nonNull).map(entity->{
				
				SentimentDto dto=new SentimentDto();				
				dto.setSentence(entity.getMessage());
				dto.setPolarity(entity.getPolariy());
				return dto;
				
			}).collect(Collectors.toList());
			
		}
		
			return Collections.EMPTY_LIST;
    }
        
}


