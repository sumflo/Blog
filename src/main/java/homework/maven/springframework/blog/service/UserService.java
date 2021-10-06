package homework.maven.springframework.blog.service;

import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Itt kerül implementálásra minden üzleti logika.
 * A service döntéseket hoz, kalkulál, figyelembe veszi a felhasználó jogosultságait, illetve összekapcsolja
 * a presentation( felhasználói interfész vezérlése) és a persistence(ez a réteg kommunikál az adatbázissal) réteget.*/
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Kap egy usert valahonnan (akárhonnan), és elmenti*/
    public void addUser(User user){

        if(userRepository.findByUserName(user.getUserName()).isPresent()){
            //TODO: Ex.Hand.
            System.out.println("This username is already used. Please choose another one.");
        }

        userRepository.save(new User(user.getUserName(), user.getPassword()));
    }

    public void deleteUser(Long id){
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteUsersById(id);
        }else{
            System.out.println("This userId does not exist.");
        }
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    /** Mivel lehet, hogy nincs olyan idval rendelkező user, használni kell az Optional osztályt.*/
    public Optional<User> findUser(Long id){
        return userRepository.findById(id);
    }

}
