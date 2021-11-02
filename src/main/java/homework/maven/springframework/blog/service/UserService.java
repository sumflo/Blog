package homework.maven.springframework.blog.service;

import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.model.registration.token.ConfirmationToken;
import homework.maven.springframework.blog.repositories.UserRepository;
import homework.maven.springframework.blog.service.registration.token.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
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

  private final static String USER_NOT_FOUND_MSG = "User %s not found.";
  private UserRepository userRepository;
  private ConfirmationTokenService confirmationTokenService;

  PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository,
      ConfirmationTokenService confirmationTokenService) {
    this.userRepository = userRepository;
    this.confirmationTokenService = confirmationTokenService;
    this.passwordEncoder = passwordEncoder;
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

  /* Mivel lehet, hogy nincs olyan id-val rendelkező user, használni kell az Optional osztályt. */
  public Optional<User> findUser(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByUsername(String userEmail){
    return userRepository.findByUsername(userEmail);
  }

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
  }

  /* A KÍSÉRLET RÉSZE*/
/*  public ConfirmationToken signUpUser(User user){

    Optional<User> currentUser = userRepository.findByUsername(user.getUsername());

    if (currentUser.isPresent()){
      throw new IllegalStateException("This email is already used.");
    }else{
      *//* uk *//*
      //return createToken(currentUser.get());
    }

    String encodedPassword = passwordEncoder.encode(user.getPassword());
    encodePassword(user, currentUser);
    user.setPassword(encodedPassword);
    userRepository.save(user);

    *//*
    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15), user);
    confirmationTokenService.saveConfirmationToken(confirmationToken);
    *//*

    return createToken(user);
  }*/

  public ConfirmationToken signUpUser(User user) throws IllegalStateException{

    if (userRepository.findByUsername(user.getUsername()).isPresent()){
      throw new IllegalStateException("This email is already used.");
    }

    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    userRepository.save(user);

    return createToken(user);
  }

  private ConfirmationToken createToken(User user){
    String token =  UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15), user);
    confirmationTokenService.saveConfirmationToken(confirmationToken);
    return confirmationToken;
  }

  public void enableUser(String userName) {
    userRepository.findByUsername(userName).
        orElseThrow(() -> new IllegalStateException("User not found."))
        .setEnabled(true);
  }

}
