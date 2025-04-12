package HealthProject.Eunoia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import HealthProject.Eunoia.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

