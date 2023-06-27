package com.example.demo.model.DTO;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.data.annotation.CreatedDate;

import com.example.demo.model.Chapter;
import com.example.demo.model.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ComicDTO {

	private Long id;

	private Long code;

	private String name;

	private String type;

	private String author;

    protected Date publishedAt;

	private String description;
	
	private byte[] image;

	private String status;
	
	private Long viewCount;
	
    protected Date updatedAt;
    
    private List<ChapterDTO> chapter;
}
