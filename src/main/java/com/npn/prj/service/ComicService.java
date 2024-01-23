package com.npn.prj.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.npn.prj.model.Comic;
import com.npn.prj.request.ComicRequest;
import com.npn.prj.request.ComicSearchRequest;
import com.npn.prj.response.payload.BaseResponse;

public interface ComicService {

//	Request saveDmCnDvSuDung(DmCnDvSuDung dmCnDvSuDung);
//
//	Request<String> deleteDmCnDvSuDung(DmCnDvSuDung dmCnDvSuDung);
//
//	Request updateDmCnDvSuDung(DmCnDvSuDung dmCnDvSuDung);
	
	BaseResponse delete(Long id);
	
	BaseResponse update(Comic comic);
	
	
	BaseResponse getDetail(Long id);
	
	BaseResponse create(ComicRequest comic);
	
	BaseResponse search(ComicSearchRequest comicSearchRequest, Pageable pageable);
}
