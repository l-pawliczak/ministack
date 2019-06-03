package pl.lpawliczak.mininstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lpawliczak.mininstack.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
