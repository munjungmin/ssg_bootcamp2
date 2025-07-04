package com.sinse.memberapp.model;

import java.util.List;

import lombok.Data;

@Data
public class Dept {
	private int deptno;
	private String dname;
	private String loc;
	
	//하나의 부서는 여러명의 사원을 포함할 수 있다. 일대다
	//조인이 아니라 관계에 집중해라
	private List<Emp> empList; 
}
