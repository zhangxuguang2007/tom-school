package com.tom.school.model.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "sys_user")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystemUser implements Serializable {

	private static final long serialVersionUID = -7537206161063738544L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "user_name", length = 30, nullable = false, unique = true)
	private String name;
	@Column(name = "password", length = 32, nullable = false)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SystemUser [id=" + id + ", name=" + name + ", password="
				+ password + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SystemUser)) {
			return false;
		}
		SystemUser otherUser = (SystemUser) other;
		return this.id.equals(otherUser.id) && this.name.equals(otherUser.name)
				&& this.password.equals(otherUser.password);
	}
}
