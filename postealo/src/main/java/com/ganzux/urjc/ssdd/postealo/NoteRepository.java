package com.ganzux.urjc.ssdd.postealo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganzux.urjc.ssdd.postealo.model.Note;

/**
 * Interface with the methods to manage Note Objects
 * @author ganzux
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

	/**
	 * Recover the Notes that match with the author
	 * @param author the author of the Notes that we want to find
	 * @return The Note List
	 */
	public List<Note> findByAuthor(String author);
	
}
