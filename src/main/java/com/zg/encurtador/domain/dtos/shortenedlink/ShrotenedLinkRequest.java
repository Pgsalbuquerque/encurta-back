package com.zg.encurtador.domain.dtos.shortenedlink;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ShrotenedLinkRequest {
	
	@NotBlank
	@Size(min = 4, max = 255)
	@Pattern(regexp = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$", message = "Invalid Link")
	private String link;
	
	
	
	public ShrotenedLinkRequest() {
	}

	public ShrotenedLinkRequest(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
