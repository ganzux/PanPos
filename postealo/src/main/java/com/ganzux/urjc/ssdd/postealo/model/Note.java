package com.ganzux.urjc.ssdd.postealo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class that represents a Note Entity
 * @author ganzux
 */
@Entity
public class Note {

	///////////////////////////////////////////////////////////////
	//                        Attributes                         //
	///////////////////////////////////////////////////////////////
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String author;
	
	private String text;

	// CascadeType.ALL fuerza a insertar los objetos Tag en la bbdd si no lo estaban ya
	// Y a recuperarlos cuando se cargue este objeto
	@OneToMany(cascade=CascadeType.ALL)
	private List<Tag> tags;
	/*@ElementCollection 
	private List<String> tags;*/

	///////////////////////////////////////////////////////////////
	//                        /Attributes                        //
	///////////////////////////////////////////////////////////////



	///////////////////////////////////////////////////////////////
	//               Getter, Setter and Constructors             //
	///////////////////////////////////////////////////////////////
	public Note() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public Note(long id, String author, String text, List<Tag> tags) {
		super();
		this.id = id;
		this.author = author;
		this.text = text;
		this.tags = tags;
	}
	public void addTag(String tagText){
		if (tags == null)
			tags = new ArrayList<Tag>();
		Tag tagObject = new Tag(tagText);
		tags.add(tagObject);
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", author=" + author + ", text=" + text
				+ ", tags=" + tags + "]";
	}
	///////////////////////////////////////////////////////////////
	//              /Getter, Setter and Constructors             //
	///////////////////////////////////////////////////////////////
	
}
