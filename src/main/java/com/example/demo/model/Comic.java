package com.example.demo.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "comic")
public class Comic{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Long id;

	private Long code;

	private String name;

	private String type;

	private String author;

    @Column(name="PUBLISHED_AT")
    protected Date publishedAt;

	private String description;
	
	@Lob()
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "IMAGE")
	@JsonIgnore
	private Blob image;

	private String status;
	
	@Column(name = "view_count")
	private Long viewCount;
	
	private Long commented;

	private Long followed;
	
    @Column(name = "CREATED_AT", updatable = false)
    @CreatedDate
    protected Date createdAt;
	
    @Column(name="UPDATED_AT")
    @LastModifiedDate
    protected Date updatedAt;

	public Comic(Long id, Long code, String name, String type, String author, Date publishedAt, String description,
			Blob image, String status, Long viewCount, Long commented, Long followed) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.author = author;
		this.publishedAt = publishedAt;
		this.description = description;
		this.image = image;
		this.status = status;
		this.viewCount = viewCount;
		this.commented = commented;
		this.followed = followed;
	}
}
