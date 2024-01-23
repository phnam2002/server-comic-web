package com.npn.prj.model;

import java.util.Date;

import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chapters")
public class Chapter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private Long chap;

	private String title;
	
    @Column(name="UPDATED_AT")
    @CreatedDate
    protected Date updatedAt;
    
	@Column(name = "view_count")
	private Long viewCount;
	
	@Column(name = "content_path")
	private String contentPath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMIC_ID")
//	@JsonIgnore
	private Comic comic;

	public Chapter(Long id, Long chap, String title, Date updatedAt, Long viewCount, String contentPath) {
		super();
		this.id = id;
		this.chap = chap;
		this.title = title;
		this.updatedAt = updatedAt;
		this.viewCount = viewCount;
		this.contentPath = contentPath;
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

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}


	
}
