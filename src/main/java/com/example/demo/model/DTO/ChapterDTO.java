package com.example.demo.model.DTO;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import com.example.demo.model.Chapter;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
		this.id = chapter.getId();
		this.chap = chapter.getChap();
		this.title = chapter.getTitle();
		this.updatedAt = chapter.getUpdatedAt();
		this.viewCount = chapter.getViewCount();
//		this.page = pageRes;
	}
	
}
