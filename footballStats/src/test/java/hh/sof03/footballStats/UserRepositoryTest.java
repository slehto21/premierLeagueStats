package hh.sof03.footballStats;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.footballStats.domain.User;
import hh.sof03.footballStats.domain.UserRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	 @Test
	    public void testFindByUsername() {
	        // Olemassa oleva käyttäjä
	        User user = userRepository.findByUsername("user");
	        assertThat(user).isNotNull();

	        // Käyttäjä, jota ei ole
	        User nonExistentUser = userRepository.findByUsername("NonExistentUser");
	        assertThat(nonExistentUser).isNull();
	    }

	    @Test
	    public void testCreateAndDeleteUser() {
	        // Uuden käyttäjän luonti
	        User user = new User("password", "newuser@example.com", "USER", "newuser");
	        userRepository.save(user);
	        assertThat(user.getId()).isNotNull();

	        // Käyttäjän poisto
	        userRepository.delete(user);
	        User deletedUser = userRepository.findById(user.getId()).orElse(null);
	        assertThat(deletedUser).isNull();
	    }
	
}
