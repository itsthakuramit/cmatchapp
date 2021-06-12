package com.hcl.user.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hcl.user.exception.UserAlreadyExistsException;
import com.hcl.user.model.User;
import com.hcl.user.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
  @Mock
  private transient UserRepository userRepo;

  private transient User user;

  @InjectMocks
  private transient UserServiceImpl userServiceImpl;

  transient Optional<User> options;

  @Before
  public void setupMock() {
    MockitoAnnotations.initMocks(this);
    this.user = new User("poovi@gmail.com", "poovalagan", "poovi", "thana", new Date());
    options = Optional.of(user);
  }

  @Test
  public void testMockCreation() {
    assertNotNull("JPA Repository injection fails", userRepo);
  }

  @Test
  public void testLoginSuccess() throws UserAlreadyExistsException {
    when(userRepo.findByUserId(user.getUserId())).thenReturn(user);
    User userResult = userRepo.findByUserId(user.getUserId());
    assertNotNull(userResult);
    when(userRepo.save(user)).thenReturn(user);
    verify(userRepo, times(1)).findByUserId(user.getUserId());
  }

  @Test
  public void testLoginFailure() throws UserAlreadyExistsException {
    when(userRepo.findById("superhero")).thenReturn(null);
    User userResult = userRepo.findByUserId("jana");
    assertNull(userResult);
  }
}
