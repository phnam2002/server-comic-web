package com.example.demo.request;

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

import org.hibernate.annotations.CreationTimestamp;

import com.example.demo.model.Chapter;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class ComicSearchRequest {
	private Long id;

	private Long code;

	private String name;

	private String type;

	private String author;

    protected Date publishedAt;

	private String description;
	
	private Blob image;

	private String status;
	
	private Long viewCount;
	
    protected Date updatedAt;
    
    protected Date chapterUpdatedAt;
}
