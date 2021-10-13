package com.zg.encurtador.domain.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ShortenedLink {
	@Id
	@NotNull
	@Size(max = 255)
	private String shortened;
	
	@NotNull
	@Size(max = 255)
	private String original;
	
	public ShortenedLink() {}

	public ShortenedLink(String shortened, String original) {
		this.shortened = shortened;
		this.original = original;
	}

	public String getShortened() {
		return shortened;
	}

	public void setShortened(String shortened) {
		this.shortened = shortened;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	@Override
	public String toString() {
		return "ShortenedLink [shortened=" + shortened + ", original=" + original + "]";
	}
	
	
}
