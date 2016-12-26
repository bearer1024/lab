package uk.ac.le.cs.CO3098.cw3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.le.cs.CO3098.cw3.repository.HierarchyRepository;

	@Service
public class HierarchyService {

	@Autowired
	private  HierarchyRepository hierarchyRepository;
	
	public Object findAllClasses(){
		return hierarchyRepository.findAll();
	}
}