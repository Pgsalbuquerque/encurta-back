package com.zg.encurtador.domain.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public ResponseEntity<ShortenedLinkResponse> shorten(ShortenedLinkRequest original) {
		String originalLink = original.getLink();
		
		String possiblelink = UUID.randomUUID().toString();
		
		int count = 0;
		while (count < 5 && shortenedLinkRepository.getByShortened(possiblelink) != null) {
			possiblelink = UUID.randomUUID().toString();
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
}
