package com.npn.prj.service;

import org.springframework.data.domain.Pageable;

import com.npn.prj.model.Chapter;
import com.npn.prj.model.Comic;
import com.npn.prj.request.ChapterRequest;
import com.npn.prj.request.ComicSearchRequest;
import com.npn.prj.response.payload.BaseResponse;

public interface ChapterService {
	
	BaseResponse delete(Long id);
	
	BaseResponse update(Chapter chapter);
	
	BaseResponse getDetail(Long id);
	
	BaseResponse create(Chapter chapter);
	
	BaseResponse search(Chapter chapter);

}
