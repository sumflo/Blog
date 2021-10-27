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

/**
 * Itt kerül implementálásra minden üzleti logika. A service döntéseket hoz, kalkulál, figyelembe
 * veszi a felhasználó jogosultságait, illetve összekapcsolja a presentation( felhasználói interfész
 * vezérlése) és a persistence(ez a réteg kommunikál az adatbázissal) réteget.
 */
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Kap egy usert valahonnan (akárhonnan), és elmenti
   */
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

  /**
   * Mivel lehet, hogy nincs olyan idval rendelkező user, használni kell az Optional osztályt.
   */
  public Optional<User> findUser(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByUsername(String name){
    return userRepository.findByUsername(name);
  }

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username).orElseThrow();
    return user;
  }

}
