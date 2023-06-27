package com.example.demo.service;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.Chapter;
import com.example.demo.model.Comic;
import com.example.demo.request.ChapterRequest;
import com.example.demo.request.ComicSearchRequest;
import com.example.demo.response.payload.BaseResponse;

public interface ChapterService {
	
	BaseResponse delete(Long id);
	
	BaseResponse update(Chapter chapter);
	
	BaseResponse getDetail(Long id);
	
	BaseResponse create(Chapter chapter);
	
	BaseResponse search(Chapter chapter);

}
