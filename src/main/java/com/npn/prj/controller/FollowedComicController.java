package com.npn.prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.npn.prj.model.FollowedComic;
import com.npn.prj.response.payload.BaseResponse;
import com.npn.prj.service.FollowedComicServices;


@RestController
@RequestMapping("/follow-comic")
@CrossOrigin(origins = "http://localhost:4200")
public class FollowedComicController {
	
	@Autowired
	private FollowedComicServices flComicService;
	
	@GetMapping("{id}") // lấy chi tiết loại sản phẩm
	public ResponseEntity<BaseResponse> getDetail(@PathVariable long id) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = flComicService.getDetail(id);
		return ResponseEntity.ok(baseResponse);
	}
//	
	@GetMapping("/search") // lấy chi tiết loại sản phẩm
	public ResponseEntity<BaseResponse> getAllByChap(Long userId,Long comicId) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = flComicService.search(userId,comicId);
		return ResponseEntity.ok(baseResponse);
	}

//	@GetMapping // Tim kiem theo nhieu dieu kien
//	public ResponseEntity<BaseResponse> searchAll(Chapter chapterSearchRequest, Pageable pageable) { // Tao obj moi obj request khac entity
//		BaseResponse baseResponse = chaptService.search(chapterSearchRequest, pageable);
//		return ResponseEntity.ok(baseResponse);
//	}
	
	@PostMapping("/add") // Tao moi
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BaseResponse> create(@RequestBody FollowedComic followedComicRequest) { // Tao obj moi neu obj // request khac
																	// entity
		BaseResponse baseResponse=flComicService.create(followedComicRequest);
		return ResponseEntity.ok(baseResponse);
	}

	@DeleteMapping("{id}") // Xoa
	public ResponseEntity<BaseResponse> delete(@PathVariable long id) { // Tao obj moi neu obj request khac entity
		BaseResponse baseResponse = flComicService.delete(id);
		return ResponseEntity.ok(baseResponse);
	}

}
