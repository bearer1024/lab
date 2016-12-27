package uk.ac.le.cs.CO3098.cw3.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.ac.le.cs.CO3098.cw3.domain.ClassHierarchy;

@Repository
public interface CLASS_HIERARCHYRepository extends CrudRepository<ClassHierarchy,Integer> {

}
