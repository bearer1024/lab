package uk.ac.le.cs.CO3098.cw3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.le.cs.CO3098.cw3.domain.UMTClass;

@Repository	
public interface UMTClassRepository extends CrudRepository<UMTClass,Integer>{

}
