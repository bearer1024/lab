package uk.ac.le.cs.CO3098.cw3.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.ac.le.cs.CO3098.cw3.domain.Hierarchy;

@Repository
public interface HierarchyRepository extends CrudRepository<Hierarchy,Integer> {

}
