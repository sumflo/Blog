package homework.maven.springframework.blog.service;

import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final static String USER_NOT_FOUND_MSG = "User %s not found.";
  private UserRepository userRepository;

  PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void addUser(User user) throws Exception {

    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      //TODO: Ex.Hand.
      throw new Exception();
    }else{
      User currentUser = new User();
      BeanUtils.copyProperties(user, currentUser);
      encodePassword(user, currentUser);
      userRepository.save(currentUser);
    }
  }

  private void encodePassword( User userData, User currentUser){
    currentUser.setPassword(passwordEncoder.encode(userData.getPassword()));
  }

  public void deleteUser(Long id) {
    if (userRepository.findById(id).isPresent()) {
      userRepository.deleteUsersById(id);
    } else {
      System.out.println("This userId does not exist.");
    }
  }

  public List<User> listUsers() {
    return userRepository.findAll();
  }

  public Optional<User> findUser(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByUsername(String userEmail){
    return userRepository.findByUsername(userEmail);
  }

 /* @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
  }*/

}
