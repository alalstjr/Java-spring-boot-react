package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
/*
 * @Entity 기본 생성자는 필수(파라미터가 없는 public 또는 protected 생성자)
 * 자바는 생성자가 하나도 없으면 기본 생성자를 자동으로 만듬
 */
public class ProjectTask {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	// @Id는 해당 프로퍼티가 테이블의 주키(primary key) 역할을 한다
	
	@NotBlank(message = "요약은 비워 둘 수 없습니다.")
	/*
	null 허용 하지 않는다.
	“” 허용하지 않는다.
	” “(space) 허용하지 않는다.
	*/
	private String summary;	
	// 목차, 개요
	private String acceptanceCriteria; 
	// 인수검사 보시스템 검사 중 하나로, 시스템이 실제 운영 환경에서 사용될 준비가 되었는지 최종적으로 확인하는 단계이다. 
	private String status;
	
	public ProjectTask() {
		super();
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getSummary() {
		return summary;
	}

	public final void setSummary(String summary) {
		this.summary = summary;
	}

	public final String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}

	public final void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}
	
	
}
