package com.chandan.learning.springrestwebservice.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the user")
@Entity
public class User
{
	@Id
	@GeneratedValue
	private Integer id;

	@ApiModelProperty(notes = "Name should be at least 2 characters.")
	@Size(min = 2, message = "Name should be at least 2 characters.")
	private String name;

	@ApiModelProperty(notes = "Date of birth should be past")
	@Past(message = "Date of birth should be past")
	private Date dateOfBirth;
	
	protected User()
	{}
	

	public User(Integer id, String name, Date dateOfBirth)
	{
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
	}
}
