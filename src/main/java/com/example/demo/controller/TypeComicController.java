package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TypeComic;
import com.example.demo.service.TypeComicService;

@RestController
@RequestMapping("/typeComic")
public class TypeComicController {

	@Autowired
	private TypeComicService tComicService;
	
	@GetMapping("{id}") // lấy chi tiết loại sản phẩm
	public Optional<TypeComic> getDetail(@PathVariable Long id) { // Tao obj moi neu obj request khac entity
		return tComicService.getDetail(id);
	}

	@GetMapping // Tim kiem theo nhieu dieu kien
	public List<TypeComic> searchAll() {// Tao obj
		return tComicService.findAll();
	}
}
