package com.example.demo.service.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import com.example.demo.request.ComicRequest;
import com.example.demo.request.ComicSearchRequest;
import com.example.demo.response.payload.BaseResponse;
import com.example.demo.service.ComicService;

@Service
public class ComicServiceImpl implements ComicService {
	private static final Logger logger = LoggerFactory.getLogger(ComicServiceImpl.class);

	@Autowired
	public ComicRepository comicRepo;

	@Autowired
	public ChapterRepository chapterRepo;

	private String typeSearch;

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		return new MultipartConfigElement("");
	}

	public BaseResponse create(ComicRequest comic) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			var comicBuilder = Comic.builder()
					.status(comic.getStatus())
					.code(comic.getCode())
					.name(comic.getName())
					.description(comic.getDescription())
					.type(comic.getType())
					.author(comic.getAuthor())
					.publishedAt(comic.getPublishedAt())
					.image(comic.getImage())
					.build(); // tiện ích
			jpaExecute(comicBuilder);
			baseResponse.setData(comicBuilder);

		} catch (Exception e) {
			System.out.println("service");
			System.out.println(e);
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
//			logger.error("error", e);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse search(ComicSearchRequest comicSearchRequest, Pageable pageable) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			Specification<Comic> specification = new Specification<Comic>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<Comic> root, CriteriaQuery<?> query,
						CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();
					if (!ObjectUtils.isEmpty(comicSearchRequest.getName())) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
								"%" + comicSearchRequest.getName().toUpperCase() + "%")));
					}
					if (!ObjectUtils.isEmpty(comicSearchRequest.getType())) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("type")),
								"%" + comicSearchRequest.getType().toUpperCase() + "%")));
					}
					if (!ObjectUtils.isEmpty(comicSearchRequest.getStatus())) {
						predicates.add(criteriaBuilder.and(criteriaBuilder
								.equal(criteriaBuilder.upper(root.get("status")), comicSearchRequest.getStatus())));
					}
					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			};
			Page<ComicDTO> page = comicRepo.findAll(specification, pageable).map(item -> {

				ComicDTO comicResponse = new ComicDTO();
				try {
					comicResponse = toSearchComicResponse(item, typeSearch = "search");
				} catch (SQLException | IOException e) {
					baseResponse.setFwError(FwError.DLKHONGTONTAI);
				}
				return comicResponse;
			});

			baseResponse.setData(page);
		} catch (Exception e) {
			System.out.println(e);
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			logger.error("error", e);
		}
		return baseResponse;
	}
	
	public BaseResponse searchSelect2(ComicSearchRequest comicSearchRequest, Pageable pageable) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			Specification<Comic> specification = new Specification<Comic>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override
				public Predicate toPredicate(Root<Comic> root, CriteriaQuery<?> query,
						CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();
					if (!ObjectUtils.isEmpty(comicSearchRequest.getName())) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
								"%" + comicSearchRequest.getName().toUpperCase() + "%")));
					}

					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			};
			Page<ComicDTO> page = comicRepo.findAll(specification, pageable).map(item -> {

				ComicDTO comicResponse = new ComicDTO();
				try {
					comicResponse = toSearchComicResponse(item, typeSearch = "search");
				} catch (SQLException | IOException e) {
					baseResponse.setFwError(FwError.DLKHONGTONTAI);
				}
				return comicResponse;
			});

			baseResponse.setData(page);
		} catch (Exception e) {
			System.out.println(e);
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			logger.error("error", e);
		}
		return baseResponse;
	}
	
	public BaseResponse searchMostViewCount() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
			List<Comic> comics = comicRepo.searchMostViewComic();
				List<ComicDTO> comicDTOs = new ArrayList<ComicDTO>();
				try {
					for (Comic c : comics) {
						ComicDTO comicDTO  = new ComicDTO();
						comicDTO = toSearchComicResponse(c, typeSearch = "detail");
						comicDTOs.add(comicDTO);
					}
				} catch (SQLException | IOException e) {
					baseResponse.setFwError(FwError.DLKHONGTONTAI);
				}
			baseResponse.setData(comicDTOs);
		return baseResponse;
	}

	@Override
	public BaseResponse update(Comic comic) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			var comicCur = comicRepo.findById(comic.getId()).orElse(null);
			if (comicCur == null) {
				baseResponse.setFwError(FwError.DLKHONGTONTAI);
				return baseResponse;
			}
			comicCur.setName(comic.getName());
			comicCur.setStatus(comic.getStatus());
			comicCur.setCode(comic.getCode());
			comicCur.setType(comic.getType());
			comicCur.setPublishedAt(comic.getPublishedAt());
			comicCur.setDescription(comic.getDescription());
			if (comic.getImage() != null) {
				comicCur.setImage(comic.getImage());
			}
			comicCur.setAuthor(comic.getAuthor());

			jpaExecute(comicCur);
			baseResponse.setData(comicCur);

		} catch (Exception e) {
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			System.out.println(e);
//			logger.error("error", e);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse getDetail(Long id) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu

		var comicCur = comicRepo.findById(id).orElse(null);
		if (comicCur == null) {
			baseResponse.setFwError(FwError.DLKHONGTONTAI);
			return baseResponse;
		}
		try {
			baseResponse.setData(toSearchComicResponse(comicCur, typeSearch = "detail"));
		} catch (SQLException | IOException e) {
			baseResponse.setFwError(FwError.DLKHONGTONTAI);
			return baseResponse;
		}
		return baseResponse;
	}

	@Override
	public BaseResponse delete(Long id) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			var productCur = comicRepo.findById(id).orElse(null);
			if (productCur == null) {
				baseResponse.setFwError(FwError.DLKHONGTONTAI);
				baseResponse.setData(productCur);
				return baseResponse;
			}
			jpaDeleteExecute(id);
			baseResponse.setData(productCur);
		} catch (Exception e) {
			System.out.println(e);
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			logger.error("error", e);
		}
		return baseResponse;
	}

	private ComicDTO toSearchComicResponse(Comic comic, String typeSearch) throws SQLException, IOException {
		byte[] image = null;
		if (comic.getImage() != null) {
			Blob blob = comic.getImage();

			int blobLength = (int) blob.length();
			image = blob.getBytes(1, blobLength);
		}
		List<Chapter> chapters = new ArrayList<Chapter>();
		if (typeSearch == "search") {
			chapters = chapterRepo.find3ByComicId(comic.getId());
		}
		if (typeSearch == "detail") {
			chapters = chapterRepo.findByComicId(comic.getId());
		}
		List<ChapterDTO> chapter = new ArrayList<ChapterDTO>();

		for (Chapter c : chapters) {
			ChapterDTO chapterDTO = new ChapterDTO();
			chapterDTO.loadFromEntity(c);
			chapter.add(chapterDTO);
		}

		ComicDTO comicResponse = ComicDTO.builder().id(comic.getId()).status(comic.getStatus()).code(comic.getCode())
				.name(comic.getName()).description(comic.getDescription()).type(comic.getType())
				.viewCount(comic.getViewCount()).author(comic.getAuthor()).publishedAt(comic.getPublishedAt())
				.image(image).chapter(chapter).build(); // tiện
														// ích
		if (comic.getUpdatedAt() != null) {
			comicResponse.setUpdatedAt(comic.getUpdatedAt());
		}
		return comicResponse;
	}


//	public Comic byteToBlob(Comic comicResponse) throws SQLException, IOException {
//		Blob blob = null;
//		if (comicResponse.getImage() != null && comicResponse.getId() != null) {
//			blob = BlobProxy.generateProxy(comicResponse.getImage());
//		}
//
//		return Comic.builder().id(comicResponse.getId()).status(comicResponse.getStatus()).code(comicResponse.getCode())
//				.name(comicResponse.getName()).description(comicResponse.getDescription()).type(comicResponse.getType())
//				.author(comicResponse.getAuthor()).publishedAt(comicResponse.getPublishedAt()).image(blob).build(); // tiện
//																													// ích
//	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void jpaDeleteExecute(Long id) {
		comicRepo.deleteById(id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void jpaExecute(Comic comic) {
		comicRepo.save(comic);

	}
}
