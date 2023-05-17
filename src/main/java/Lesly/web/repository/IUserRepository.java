package Lesly.web.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import Lesly.web.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}