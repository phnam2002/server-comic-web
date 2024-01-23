package com.npn.prj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.npn.prj.model.FollowedComic;

@Repository
public interface FollowedComicRepository extends JpaRepository<FollowedComic, Long>, JpaSpecificationExecutor<FollowedComic>{

	@Query(value = "select ufc.*  from user_followed_comic ufc where ufc.user_id = :userId order by created_at desc", nativeQuery = true)
	List<FollowedComic> findByUserId(Long userId);
	
	@Query(value = "select ufc.*  from user_followed_comic ufc where ufc.user_id = :userId && ufc.comic_id = :comicId", nativeQuery = true)
	Optional<FollowedComic> findByUserIdAndComicId(Long userId,Long comicId);
}
