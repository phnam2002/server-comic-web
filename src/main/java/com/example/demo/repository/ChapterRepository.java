package com.example.demo.repository;

import java.util.List;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Chapter;
import com.example.demo.model.Comic;

public interface ChapterRepository extends JpaRepository<Chapter, Long>, JpaSpecificationExecutor<Chapter>{
	
	@Query(value = "select * from chapter c  where c.comic_id = :comicId order by chap desc LIMIT 3", nativeQuery = true)
	List<Chapter> find3ByComicId(@Param("comicId") Long comicId);
	
	@Query(value = "select * from chapter c  where c.comic_id = :comicId order by chap desc", nativeQuery = true)
	List<Chapter> findByComicId(@Param("comicId") Long comicId);
	
	@Query(value = "select * from chapter c  where c.comic_id = :comicId and c.chap LIKE %:chap% order by chap desc" , nativeQuery = true)
	Chapter findByChap(@Param("comicId") Long comicId,@Param("chap") Long chap);

//	@Query(value = "select * from chapter c  where c.comic_id = :comicId order by chap desc", nativeQuery = true)
//	Chapter findByChapterId(Long id);
	
//	@Query(value = "select * from chapter c  where c.comic_id = :comicId and c.chap = :chap", nativeQuery = true)
//	Chapter findByComicIdAndChapterChap(@Param("comicId") Long comicId,@Param("chap") Long chap);
}
