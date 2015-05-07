package com.ganzux.urjc.ssdd.pandemonium;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganzux.urjc.ssdd.pandemonium.model.Text;

/**
 * Interface with the methods to manage Text Objects
 * @author ganzux
 */
public interface TextRepository extends JpaRepository<Text, Long> {

	/**
	 * Recover the Text that match with the company
	 * @param company the company of the Text that we want to find
	 * @return The Text List
	 */
	public List<Text> findByCompany(String company);
	
}