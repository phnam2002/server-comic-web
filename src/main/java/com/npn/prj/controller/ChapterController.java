package com.npn.prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.npn.prj.model.Chapter;
import com.npn.prj.model.DTO.ChapterDTO;
import com.npn.prj.response.payload.BaseResponse;
import com.npn.prj.service.ChapterService;
import com.npn.prj.service.impl.ChapterServiceImpl;

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
