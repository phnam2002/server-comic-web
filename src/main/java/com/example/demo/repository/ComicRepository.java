package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Chapter;
import com.example.demo.model.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>, JpaSpecificationExecutor<Comic>{
	@Query(value = "select *  from comics c order by view_count desc LIMIT 7", nativeQuery = true)
	List<Comic> searchMostViewComic();
	
	@Query(value = "select com.*  from comics com inner join chapters chap on com.id = chap.comic_id order by chap.updated_at desc", nativeQuery = true)
	List<Comic> orderBYComic();
}
