package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.TypeComic;

public interface TypeComicService {
	
	Optional<TypeComic> getDetail(Long id);
	List<TypeComic> findAll();
}
