package com.ganzux.urjc.ssdd.postealo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class that represents a Tag Entity
 * @author ganzux
 */
@Entity
public class Tag {

	///////////////////////////////////////////////////////////////
	//                        Attributes                         //
	///////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String text;
	///////////////////////////////////////////////////////////////
	//                        /Attributes                        //
	///////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////
	//               Getter, Setter and Constructors             //
	///////////////////////////////////////////////////////////////
	public Tag() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Tag(long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	public Tag(String text) {
		super();
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", text=" + text + "]";
	}
	///////////////////////////////////////////////////////////////
	//              /Getter, Setter and Constructors             //
	///////////////////////////////////////////////////////////////
}
