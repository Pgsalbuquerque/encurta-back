package com.zg.encurtador.domain.services;

import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zg.encurtador.domain.dtos.shortenedlink.ShortenedLinkRequest;
import com.zg.encurtador.domain.dtos.shortenedlink.ShortenedLinkResponse;
import com.zg.encurtador.domain.models.ShortenedLink;
import com.zg.encurtador.domain.repositories.ShortenedLinkRepository;
import com.zg.encurtador.web.response.exceptions.ResponseException;

@Service
public class ShortenedLinkService {
	@Autowired
	private ShortenedLinkRepository shortenedLinkRepository;
	
	private String generateShortenLink() {
		String shortenedLink = "";
		
		for (int count = 0; count < 5; count++) {
			Random random = new Random();
			int i = random.nextInt(75) + 48;
			while (i >= 58 && i <= 64 || i >= 91 && i <= 96) {
				i = random.nextInt(75) + 48;
			}
			shortenedLink += (char) (i);
		}
		return shortenedLink;
	}
	
	public ResponseEntity<ShortenedLinkResponse> shorten(ShortenedLinkRequest original) {
		String originalLink = original.getLink();
		
		String possiblelink = generateShortenLink();
		
		int count = 0;
		while (count < 5 && shortenedLinkRepository.getByShortened(possiblelink) != null) {
			possiblelink = generateShortenLink();
			count++;
		}
		
		if (shortenedLinkRepository.getByShortened(possiblelink) != null) {
			throw new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, try again");
		}
		
		ShortenedLink shortenedlink = new ShortenedLink(possiblelink, originalLink);
		shortenedLinkRepository.save(shortenedlink);
		
		ShortenedLinkResponse shortenedLinkResponse = new ShortenedLinkResponse(possiblelink);
		return ResponseEntity.ok(shortenedLinkResponse);
	}
	
	@Cacheable("shortened")
    public ResponseEntity<ShortenedLinkResponse> getOriginal(String shortened, HttpServletResponse response) {
    	ShortenedLink shortenedLink = shortenedLinkRepository.getByShortened(shortened);
    	System.out.println("entrei");
    	if (shortenedLink == null) {
    		throw new ResponseException(HttpStatus.BAD_REQUEST, "Invalid Link");
    	}
    	
    	ShortenedLinkResponse shortenedLinkResponse = new ShortenedLinkResponse(shortenedLink.getOriginal());
    	response.addHeader("Cache-Control", "s-maxage=3600, stale-while-validade");
    	return ResponseEntity.ok(shortenedLinkResponse);
    }
}
