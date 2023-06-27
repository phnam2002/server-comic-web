package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.TypeComic;

public interface TypeComicRepository extends JpaRepository<TypeComic, Long>, JpaSpecificationExecutor<TypeComic>{

}
