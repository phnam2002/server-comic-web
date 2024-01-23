package com.npn.prj.model.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.npn.prj.model.Chapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ChapterDTO {
	
	private Long id;

	private Long chap;

	private String title;

	protected Date updatedAt;

	private Long viewCount;
	
	public void loadFromEntity(Chapter chapter) {
//		List<PageDTO> pageRes = new ArrayList<PageDTO>();
//		for (PageChapter p : chapter.getPage()) {
//			PageDTO pageDTO = new PageDTO();
//			pageDTO.loadFromEntity(p);
//			pageRes.add(pageDTO);
//		}
//		this.id = chapter.getId();
//		this.chap = chapter.getChap();
//		this.title = chapter.getTitle();
//		this.updatedAt = chapter.getUpdatedAt();
//		this.viewCount = chapter.getViewCount();
//		this.page = pageRes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChap() {
		return chap;
	}

	public void setChap(Long chap) {
		this.chap = chap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	
	
}
