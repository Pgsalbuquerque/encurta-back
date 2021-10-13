package com.zg.encurtador.domain.dtos.shortenedlink;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ShortenedLinkRequest {
	
	@NotBlank
	@Size(min = 4, max = 255)
	@Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "Invalid Link")
	private String link;
	
	public ShortenedLinkRequest() {
	}

	public ShortenedLinkRequest(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
