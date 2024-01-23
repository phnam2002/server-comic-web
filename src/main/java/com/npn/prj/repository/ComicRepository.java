package com.npn.prj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.npn.prj.model.Chapter;
import com.npn.prj.model.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>, JpaSpecificationExecutor<Comic>{
	@Query(value = "select *  from comic c order by view_count desc LIMIT 7", nativeQuery = true)
	List<Comic> searchMostViewComic();
	
	@Query(value = "select com.*  from comic com inner join chapter chap on com.id = chap.comic_id order by chap.updated_at desc", nativeQuery = true)
	List<Comic> orderBYComic();
}
