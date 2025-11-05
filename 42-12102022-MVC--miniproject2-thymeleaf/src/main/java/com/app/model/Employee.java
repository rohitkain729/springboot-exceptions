package com.app.model;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "EMP")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE EMP SET STATUS='INACTIVE'WHERE ENO=?") // query for soft deletion
@Where(clause = "STATUS <> 'INACTIVE'") // implicit condtion on curd operation to avoid softly deleted records
//@RequiredArgsConstructor
public class Employee implements Serializable {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "emp_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen1")
	private Integer eno;
	@Column(length = 20)
	private String ename;
	@Column(length = 20)
	private String job;
	@Column(length = 20)
	private Double sal;
	@Column(length = 30)
	private Integer deptno;
//	@NonNull
	private String status = "ACTIVE";

	public Employee(Integer eno, String ename, String job, Double sal, Integer deptno) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.job = job;
		this.sal = sal;
		this.deptno = deptno;
	}

}
