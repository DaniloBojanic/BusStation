package finale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import finale.dto.UserPasswordChangeDto;
import finale.model.User;

public interface UserService {
	
	Optional<User> findOne(Long id);

    List<User> findAll();

    Page<User> findAll(int pageNo);

    User save(User user);

    void delete(Long id);

    Optional<User> findbyKorisnickoIme(String userName);

    boolean changePassword(Long id, UserPasswordChangeDto userPasswordChange);

}
