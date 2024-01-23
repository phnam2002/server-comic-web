package com.example.demo.service;

import com.example.demo.model.Chapter;
import com.example.demo.model.FollowedComic;
import com.example.demo.response.payload.BaseResponse;

public interface FollowedComicServices {

	BaseResponse delete(Long userId);
	
	BaseResponse update(Chapter chapter);
	
	BaseResponse getDetail(Long id);
	
	BaseResponse create(FollowedComic flComic);
	
	BaseResponse search(Long userId,Long comicId);
}
