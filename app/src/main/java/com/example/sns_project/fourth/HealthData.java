package com.example.sns_project.fourth;

import java.io.Serializable;

public class HealthData implements Serializable{
	String idx = "";
	String sex = "";
	String health = "";
	String img = "";
	String use = "";

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public HealthData(String idx, String sex, String health, String img, String use, String con) {
		this.idx = idx;
		this.sex = sex;
		this.health = health;
		this.img = img;
		this.use = use;
		this.con = con;
	}

	String con = "";









}
