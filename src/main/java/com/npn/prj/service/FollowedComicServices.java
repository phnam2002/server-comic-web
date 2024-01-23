package com.npn.prj.service;

import com.npn.prj.model.Chapter;
import com.npn.prj.model.FollowedComic;
import com.npn.prj.response.payload.BaseResponse;

public interface FollowedComicServices {

	BaseResponse delete(Long userId);
	
	BaseResponse update(Chapter chapter);
	
	BaseResponse getDetail(Long id);
	
	BaseResponse create(FollowedComic flComic);
	
	BaseResponse search(Long userId,Long comicId);
}
