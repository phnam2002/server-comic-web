package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chapter")
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


	
}
