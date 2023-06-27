package com.example.demo.service.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.example.demo.common.Constant.FwError;
import com.example.demo.model.Chapter;
import com.example.demo.model.Comic;
import com.example.demo.model.DTO.ChapterDTO;
import com.example.demo.model.DTO.ComicDTO;
import com.example.demo.repository.ChapterRepository;
import com.example.demo.repository.ComicRepository;
import com.example.demo.request.ChapterRequest;
import com.example.demo.response.ComicResponse;
import com.example.demo.response.payload.BaseResponse;
import com.example.demo.service.ChapterService;

@Service
public class ChapterServiceImpl implements ChapterService {
	private static final Logger logger = LoggerFactory.getLogger(ComicServiceImpl.class);

	@Autowired
	public ChapterRepository chapterRepo;

	@Autowired
	public ComicRepository comicRepo;

	@Autowired
	public ComicServiceImpl comicService;

	@Override
	public BaseResponse delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse update(Chapter chapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse search(Chapter chapter) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu

//		var comicCur = comicRepo.findById(comicId).orElse(null);
//		if (comicCur == null) {
//			baseResponse.setFwError(FwError.DLKHONGTONTAI);
//			return baseResponse;
//		}
		if(chapter.getChap() != null) {
			var chaptCur = chapterRepo.findByChap(chapter.getComic().getId(),chapter.getChap());
			baseResponse.setData(chaptCur);
		}else {
			var chaptCur = chapterRepo.findByComicId(chapter.getComic().getId());
			baseResponse.setData(chaptCur);
		}

		return baseResponse;

//		BaseResponse baseResponse = new BaseResponse();
//		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
//		try {
//			Specification<Chapter> specification = new Specification<Chapter>() {
//				/**
//				 * 
//				 */
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				public Predicate toPredicate(Root<Chapter> root, CriteriaQuery<?> query,
//						CriteriaBuilder criteriaBuilder) {
//					List<Predicate> predicates = new ArrayList<>();
//
//					if (!ObjectUtils.isEmpty(chapter.getChap())) {
//						predicates.add(criteriaBuilder
//								.and(criteriaBuilder.like(root.get("chap"), "%" + chapter.getChap() + "%")));
//					}
//
//					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("comic"), id)));
//
//					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//				}
//			};
//			List<Chapter> page = chapterRepo.findAll(specification);
//
//			baseResponse.setData(page);
//		} catch (Exception e) {
//			System.out.println(e);
//			baseResponse.setFwError(FwError.KHONGTHANHCONG);
//			logger.error("error", e);
//		}
//		return baseResponse;
	}

	@Override
	public BaseResponse getDetail(Long id) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu

		var chaptCur = chapterRepo.findById(id).orElse(null);
		if (chaptCur == null) {
			baseResponse.setFwError(FwError.DLKHONGTONTAI);
			return baseResponse;
		}
		baseResponse.setData(chaptCur);
		return baseResponse;
	}

	@Override
	public BaseResponse create(Chapter chapter) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			var comicCur = comicRepo.findById(chapter.getComic().getId()).orElse(null);
			if (comicCur == null) {
				baseResponse.setFwError(FwError.DLKHONGTONTAI);
				baseResponse.setData(comicCur);
				return baseResponse;
			}
			var chapterBuilder = Chapter.builder()
					.chap(chapter.getChap())
					.title(chapter.getTitle())
					.contentPath(chapter.getContentPath())
					.comic(chapter.getComic())
					.build(); // mô tả
			jpaExecute(chapterBuilder);
			baseResponse.setData(chapterBuilder);
		} catch (Exception e) {
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			logger.error("error", e);
		}
		return baseResponse;
	}

//	@Override
//	public BaseResponse search(Chapter chapter, Pageable pageable) {
//		BaseResponse baseResponse = new BaseResponse();
//		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
//		try {
//			Specification<Chapter> specification = new Specification<Chapter>() {
//				/**
//				 * 
//				 */
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				public Predicate toPredicate(Root<Chapter> root, CriteriaQuery<?> query,
//						CriteriaBuilder criteriaBuilder) {
//					List<Predicate> predicates = new ArrayList<>();
//					if (!ObjectUtils.isEmpty(chapter.getComic())) {
//						predicates.add(criteriaBuilder
//								.and(criteriaBuilder.equal(root.get("comic"), chapter.getComic().getId())));
//					}
//					if (!ObjectUtils.isEmpty(chapter.getChap())) {
//						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("chap"), chapter.getChap())));
//					}
//					if (!ObjectUtils.isEmpty(chapter.getTitle())) {
//						predicates
//								.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")),
//										"%" + chapter.getTitle().toUpperCase() + "%")));
//					}
//					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//				}
//			};
//			Page<Chapter> page = chapterRepo.findAll(specification, pageable).map(item -> {
//				Chapter chapterResponse = Chapter.builder().id(item.getId()).chap(item.getChap()).title(item.getTitle())
//						.comic(item.getComic()).build(); // tiện ích
//
//				if (item.getUpdatedAt() != null) {
//					chapterResponse.setUpdatedAt(item.getUpdatedAt());
//				}
//				return chapterResponse;
//			});
////			Page<Object> page = sysErrorDefineRepository.findAll(specification, pageable)
////					.map(item -> SysErrorDefineResponse.builder().code(item.getCode()).function(item.getFunction())
////							.content(item.getContent()).contentEn(item.getContentEn()).updatedAt(item.getUpdatedAt())
////							.updatedBy(userToResponseConvert.convert(item.getUpdatedBy())).build());
//			baseResponse.setData(page);
//		} catch (Exception e) {
//			baseResponse.setFwError(FwError.KHONGTHANHCONG);
//			System.out.println(e);
////			logger.error("error", e);
//		}
//		return baseResponse;
//	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long jpaExecute(Chapter chapter) {
		chapterRepo.saveAndFlush(chapter);
		return chapter.getId();
	}

//private Chapter toChapterResponse(Chapter chapter) throws SQLException, IOException {
//		
////		byte[] image = null;
////		if (chapter..getImage() != null) {
////			Blob blob = chapter.getImage();
////
////			int blobLength = (int) blob.length();  
////			image = blob.getBytes(1, blobLength);
////		}
//		
//	Chapter chapterResponse = Chapter.builder()
//				.id(chapter.getId())
//				.chap(chapter.getChap())
//				.title(chapter.getTitle())
//				.viewCount(chapter.getViewCount())
////				.page(chapter.getPage())
////				.comic(chapter.getComic())
//				.build(); // tiện ích
//		if (chapter.getUpdatedAt() != null) {
//			chapterResponse.setUpdatedAt(chapter.getUpdatedAt());
//		}
//		return chapterResponse;
//	}
}
