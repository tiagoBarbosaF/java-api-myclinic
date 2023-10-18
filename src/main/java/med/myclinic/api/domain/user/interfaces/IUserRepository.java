package med.myclinic.api.domain.user.interfaces;

import med.myclinic.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
