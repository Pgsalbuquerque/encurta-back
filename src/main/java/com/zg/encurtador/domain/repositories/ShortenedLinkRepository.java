package com.zg.encurtador.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zg.encurtador.domain.models.ShortenedLink;

@Repository
public interface ShortenedLinkRepository extends JpaRepository<ShortenedLink, String> {
	ShortenedLink getByShortened(String shortened);
}
