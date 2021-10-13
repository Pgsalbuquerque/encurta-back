package com.zg.encurtador.domain.dtos.shortenedlink;

public class ShortenedLinkResponse {
	private String Link;
	
	public ShortenedLinkResponse() {
	}

	public ShortenedLinkResponse(String link) {
		Link = link;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}
	
	
}	
