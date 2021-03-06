package uk.ac.le.cs.CO3098.cw3.service;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.le.cs.CO3098.cw3.domain.ClassHierarchy;
import uk.ac.le.cs.CO3098.cw3.domain.UMTClass;
import uk.ac.le.cs.CO3098.cw3.repository.CLASS_HIERARCHYRepository;
@Service
public class ClassHierarchyService {

	@Autowired
	private  CLASS_HIERARCHYRepository hierarchyRepository;
	
	public Object findAllClasses(){
		return hierarchyRepository.findAll();
	}
	
	public boolean setHierarchy(String classname){
		UMTClass umtClass = new UMTClass(classname);
		umtClass.addSuperclass(umtClass);
		return false;
	}
	
	public boolean deleteByClassName(String className){
//		hierarchyRepository.delete(entity);
		return false;
	}
	public void save(ClassHierarchy h){
		hierarchyRepository.save(h);
		
	}
	
	public void saveSuperClasses(Vector<ClassHierarchy> classes){
		hierarchyRepository.save(classes);
	}
}