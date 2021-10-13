package com.zg.encurtador.web.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zg.encurtador.domain.dtos.shortenedlink.ShortenedLinkRequest;
import com.zg.encurtador.domain.dtos.shortenedlink.ShortenedLinkResponse;
import com.zg.encurtador.domain.services.ShortenedLinkService;

@RestController
public class ShortenLinkController {
	@Autowired
	private ShortenedLinkService shortenedLinkService;
	
	@PostMapping("shorten")
	public ResponseEntity<ShortenedLinkResponse> shorten(@RequestBody @Valid ShortenedLinkRequest original) {
		return shortenedLinkService.shorten(original);
	}
	
	@GetMapping("original")
	public ResponseEntity<ShortenedLinkResponse> getOriginal(@RequestParam(name = "shortened") String shortened, HttpServletResponse response) {
		return shortenedLinkService.getOriginal(shortened, response);
	}
	
}
