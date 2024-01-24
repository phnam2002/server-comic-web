package com.example.demo.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.common.Constant.FwError;
import com.example.demo.model.Comic;
import com.example.demo.request.ComicRequest;
import com.example.demo.request.ComicSearchRequest;
import com.example.demo.response.payload.BaseResponse;
import com.example.demo.service.ComicService;
import com.example.demo.service.impl.ComicServiceImpl;

@RestController
@RequestMapping("/comic")
public class ComicController {

	@Autowired
	private ComicService cService;

	@Autowired
	private ComicServiceImpl cService1;

	@GetMapping("{id}") // lấy chi tiết loại sản phẩm
	public ResponseEntity<BaseResponse> getDetail(@PathVariable Long id) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = cService.getDetail(id);
		return ResponseEntity.ok(baseResponse);
	}

	@GetMapping // Tim kiem theo nhieu dieu kien
	public ResponseEntity<BaseResponse> searchAll(ComicSearchRequest comicSearchRequest, Pageable pageable) {// Tao obj
		BaseResponse baseResponse = cService.search(comicSearchRequest, pageable);
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/search-select2")
	public ResponseEntity<BaseResponse> searchSelect2(ComicSearchRequest comicSearchRequest, Pageable pageable) { 
		BaseResponse baseResponse=cService1.searchSelect2(comicSearchRequest, pageable);
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/searchMost") // Tim kiem theo nhieu dieu kien
	public ResponseEntity<BaseResponse> searchMostView() {
		BaseResponse baseResponse = cService1.searchMostViewCount();
		return ResponseEntity.ok(baseResponse);
	}

	@PostMapping("/add") // Tao moi
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BaseResponse> create(@RequestPart("data") @Valid ComicRequest comicRequest,
			@RequestParam(name = "file", required = false) MultipartFile file) { // Tao obj moi neu obj // request khac
																					// // entity
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		if (!ObjectUtils.isEmpty(file)) {
			try {
				Blob blob = BlobProxy.generateProxy(file.getBytes());
				comicRequest.setImage(blob);
			} catch (Exception e) {
				baseResponse.setFwError(FwError.KHONGTHANHCONG);
			}
		}
		baseResponse = cService.create(comicRequest);
		return ResponseEntity.ok(baseResponse);
	}

	@PutMapping("/update") // Cap nhat
	public ResponseEntity<BaseResponse> update(@RequestPart("data") @Valid Comic comicRequest,
			@RequestParam(name = "file", required = false) MultipartFile file) { // Tao obj moi neu obj
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			if (!ObjectUtils.isEmpty(file)) {
				Blob blob = BlobProxy.generateProxy(file.getBytes());
				comicRequest.setImage(blob);
			}
		} catch (Exception e) {
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
		}
		baseResponse = cService.update(comicRequest);
		return ResponseEntity.ok(baseResponse);
	}

	@DeleteMapping("/delete/{id}") // Xoa
	public ResponseEntity<BaseResponse> delete(@PathVariable long id) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = cService.delete(id);
		return ResponseEntity.ok(baseResponse);
	}
}
