package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Comic;
import com.example.demo.request.ComicRequest;
import com.example.demo.request.ComicSearchRequest;
import com.example.demo.response.payload.BaseResponse;

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
