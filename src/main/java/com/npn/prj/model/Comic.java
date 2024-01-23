package com.npn.prj.model;

import java.sql.Blob;
import java.util.Date;

import jakarta.persistence.*;

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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comics")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Long getCommented() {
		return commented;
	}

	public void setCommented(Long commented) {
		this.commented = commented;
	}

	public Long getFollowed() {
		return followed;
	}

	public void setFollowed(Long followed) {
		this.followed = followed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
