package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "REST_TOURIST_JPA")
@RequiredArgsConstructor
public class Tourist {
	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "TID_SEQ", initialValue = 10, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen1")
	
	private Integer tid;
	@Column(length = 30)
	@NonNull
	private String tname;
	@Column(length = 30)
	@NonNull
	private String taddrs;
	@Column(length = 30)
	@NonNull
	private String plan;
	@NonNull
	private Double budget;
}
