package com.ganzux.urjc.ssdd.postealo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganzux.urjc.ssdd.postealo.model.Tag;

/**
 * Interface with the methods to manage Tag Objects
 * @author ganzux
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

	/**
	 * Recover the Tags whose text is in anyone of the texts in the
	 * parameter (this method is case sensitive, so it is not the same
	 * TEST than Test).
	 * @param texts Collection of texts to find in the tag texts.
	 * @return The Tag List
	 */
	public List<Tag> findByTextIn(Collection<String> texts);
}
