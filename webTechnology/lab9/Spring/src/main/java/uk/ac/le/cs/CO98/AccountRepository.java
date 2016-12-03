package uk.ac.le.cs.CO98;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.ac.le.cs.CO98.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {
	

}
