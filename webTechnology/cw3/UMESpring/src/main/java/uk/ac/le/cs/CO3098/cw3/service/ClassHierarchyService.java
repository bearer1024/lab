package uk.ac.le.cs.CO3098.cw3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.le.cs.CO3098.cw3.domain.ClassHierarchy;
import uk.ac.le.cs.CO3098.cw3.repository.CLASS_HIERARCHYRepository;
@Service
public class ClassHierarchyService {

	@Autowired
	private  CLASS_HIERARCHYRepository hierarchyRepository;
	
	public Object findAllClasses(){
		return hierarchyRepository.findAll();
	}
	
	public void deleteByClassName(String className){
//		hierarchyRepository.delete();
	}
	public void save(ClassHierarchy h){
		hierarchyRepository.save(h);
		
	}
}