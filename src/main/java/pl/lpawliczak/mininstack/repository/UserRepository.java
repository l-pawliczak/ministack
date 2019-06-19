package pl.lpawliczak.mininstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lpawliczak.mininstack.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
