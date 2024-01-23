package com.npn.prj.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.npn.prj.common.Constant.FwError;
import com.npn.prj.model.Chapter;
import com.npn.prj.model.Comic;
import com.npn.prj.model.FollowedComic;
import com.npn.prj.repository.ComicRepository;
import com.npn.prj.repository.FollowedComicRepository;
import com.npn.prj.repository.UserRepository;
import com.npn.prj.response.payload.BaseResponse;
import com.npn.prj.service.ComicService;
import com.npn.prj.service.FollowedComicServices;

@Service
public class FollowedComicServicesImpl implements FollowedComicServices {
	private static final Logger logger = LoggerFactory.getLogger(ComicServiceImpl.class);
	
	@Autowired
	public FollowedComicRepository flComicRepo;
	
	@Autowired
	public ComicRepository comicRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	public ComicService comicServ;

	@Override
	public BaseResponse delete(Long id) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
		try {
			var followedComic = flComicRepo.findById(id).orElse(null);
			if (followedComic == null) {
				baseResponse.setFwError(FwError.DLKHONGTONTAI);
				baseResponse.setData(followedComic);
				return baseResponse;
			}
			jpaDeleteExecute(id);
			baseResponse.setData(followedComic);
			Comic comicCur = followedComic.getComic();
			comicCur.setFollowed(comicCur.getFollowed() - 1);
			comicServ.update(comicCur);
		} catch (Exception e) {
			baseResponse.setFwError(FwError.KHONGTHANHCONG);
			logger.error("error", e);
		}
		return baseResponse;
	}

	@Override
	public BaseResponse update(Chapter chapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse getDetail(Long id) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu

		var userCur = userRepo.findById(id).orElse(null);
		if (userCur == null) {
			baseResponse.setFwError(FwError.DLKHONGTONTAI);
			return baseResponse;
		}
			baseResponse.setData(flComicRepo.findByUserId(id));
		return baseResponse;
	}

	@Override
	public BaseResponse create(FollowedComic flComic) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu
//		try {
//			var comicCur = comicRepo.findById(flComic.getComic().getId()).orElse(null);
//			var userCur = userRepo.findById(flComic.getUser().getId()).orElse(null);
//			if (comicCur == null || userCur == null) {
//				baseResponse.setFwError(FwError.DLKHONGTONTAI);
//				baseResponse.setData(comicCur);
//				return baseResponse;
//			}
//			comicCur.setFollowed(comicCur.getFollowed() + 1);
//			comicServ.update(comicCur);
//			var followedComicBuilder = FollowedComic.builder()
//					.user(flComic.getUser())
//					.comic(flComic.getComic())
//					.build(); // mô tả
//			jpaExecute(followedComicBuilder);
//			baseResponse.setData(followedComicBuilder);
//		} catch (Exception e) {
//			baseResponse.setFwError(FwError.KHONGTHANHCONG);
//			logger.error("error", e);
//		}
		return baseResponse;
	}

	@Override
	public BaseResponse search(Long userId,Long comicId) {
		System.out.println(userId);
		System.out.println(comicId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setFwError(FwError.THANHCONG); // TODO : Lay tham so xu ly song ngu

		var followedComic = flComicRepo.findByUserIdAndComicId(userId,comicId).orElse(null);
		if (followedComic == null) {
			baseResponse.setFwError(FwError.DLKHONGTONTAI);
			return baseResponse;
		}
			baseResponse.setData(followedComic);
		return baseResponse;
	}

	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void jpaExecute(FollowedComic flComic) {
		flComicRepo.save(flComic);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void jpaDeleteExecute(Long id) {
		flComicRepo.deleteById(id);
	}

}
