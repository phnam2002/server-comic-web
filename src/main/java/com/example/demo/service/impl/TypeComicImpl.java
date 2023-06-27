package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TypeComic;
import com.example.demo.repository.TypeComicRepository;
import com.example.demo.response.payload.BaseResponse;
import com.example.demo.service.TypeComicService;

@Service
public class TypeComicImpl implements TypeComicService{

	@Autowired
	private TypeComicRepository typeComicRepo;
	
	@Override
	public Optional<TypeComic> getDetail(Long id) {
		return typeComicRepo.findById(id);
	}

	@Override
	public List<TypeComic> findAll() {
		return typeComicRepo.findAll();
	}

}
