package com.example.demo.controller;

import java.sql.Blob;
import java.util.Set;

import javax.servlet.MultipartConfigElement;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.Constant.FwError;
import com.example.demo.model.Chapter;
import com.example.demo.model.Comic;
import com.example.demo.model.DTO.ChapterDTO;
import com.example.demo.request.ChapterRequest;
import com.example.demo.request.ComicSearchRequest;
import com.example.demo.response.payload.BaseResponse;
import com.example.demo.service.ChapterService;
import com.example.demo.service.ComicService;
import com.example.demo.service.impl.ChapterServiceImpl;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
	
	@Autowired
	private ChapterService chaptService;
	
	@Autowired
	private ChapterServiceImpl chapServiceImpl;
	
	@GetMapping("{id}") // lấy chi tiết loại sản phẩm
	public ResponseEntity<BaseResponse> getDetail(@PathVariable long id) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = chaptService.getDetail(id);
		return ResponseEntity.ok(baseResponse);
	}
	
	@PostMapping("/search") // lấy chi tiết loại sản phẩm
	public ResponseEntity<BaseResponse> getAllByChap(@RequestBody Chapter chapter) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = chapServiceImpl.search(chapter);
		return ResponseEntity.ok(baseResponse);
	}

	@GetMapping // Tim kiem theo nhieu dieu kien
	public ResponseEntity<BaseResponse> searchAll(ChapterDTO chapterSearchRequest, Pageable pageable) { // Tao obj moi obj request khac entity
		BaseResponse baseResponse = chapServiceImpl.search1(chapterSearchRequest, pageable);
		return ResponseEntity.ok(baseResponse);
	}
	
	@PostMapping("/add") // Tao moi
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BaseResponse> create(@RequestBody Chapter chapterRequest) { // Tao obj moi neu obj // request khac
																	// entity
		BaseResponse baseResponse=chaptService.create(chapterRequest);
		return ResponseEntity.ok(baseResponse);
	}
//	
//	@PutMapping // Cap nhat
//	public ResponseEntity<BaseResponse> update(@RequestPart("data") @Valid Comic comicRequest,
//			@RequestParam(name = "file", required = false) MultipartFile file) { // Tao obj moi neu obj
//		BaseResponse baseResponse = new BaseResponse();
//		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
//		try {
//			if (!ObjectUtils.isEmpty(file)) {
//				Blob blob = BlobProxy.generateProxy(file.getBytes());
//				comicRequest.setImage(blob);
//			}
//		} catch (Exception e) {
//			baseResponse.setFwError(FwError.KHONGTHANHCONG);
//		}
//		baseResponse = cService.update(comicRequest);
//		return ResponseEntity.ok(baseResponse);
//	}
//	
//	@DeleteMapping("{id}") // Xoa
//	public ResponseEntity<BaseResponse> delete(@PathVariable long id) { // Tao obj moi neu obj request khac entity
//		BaseResponse baseResponse = cService.delete(id);
//		return ResponseEntity.ok(baseResponse);
//	}

}
