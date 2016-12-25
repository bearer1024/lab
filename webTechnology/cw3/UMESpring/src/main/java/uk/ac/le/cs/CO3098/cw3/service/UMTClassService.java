package uk.ac.le.cs.CO3098.cw3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.le.cs.CO3098.cw3.domain.UMTClass;
import uk.ac.le.cs.CO3098.cw3.repository.UMTClassRepository;

@Service
public class UMTClassService {

	@Autowired
	private UMTClassRepository umtClassRepository;
	
	public Object findAllClasses(){
		return umtClassRepository.findAll();
	}
}
