package com.hcl.user.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.user.model.User;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserRepositoryTest {

  @Autowired
  private transient UserRepository userRepository;

  private User user;

  public void setUserRepository(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Before
  public void setUp() {
    this.user = new User("poovi", "poovi", "poovalagan", "jaga", new Date());
  }

  
  @Test
	public void testValidateUserSuccess() {		
		userRepository.save(user);
		User userObj = userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		Assert.assertEquals(userObj.getUserId(), user.getUserId());
		
	}
}
