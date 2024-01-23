package com.npn.prj.response;

import java.util.Date;
import java.util.List;

import com.npn.prj.model.Chapter;
import com.npn.prj.model.DTO.ChapterDTO;
import com.npn.prj.request.ComicSearchRequest;

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
public class ComicResponse {
	private Long id;

	private Long code;

	private String name;

	private String type;

	private String author;

    protected Date publishedAt;

	private String description;

	private String status;
	
	private Long viewCount;
	
    protected Date updatedAt;
    
    private ChapterDTO chapter;
}
