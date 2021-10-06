package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** A @Controller annotáció jelzi a Springnek, hogy ez az osztály egy MVC Controller lesz.*/
/** A Controller rétegg az adatbázisból kinyert, majd azok átalakítása során nyert adatokat veszi át, és adja tovább
 * a megjelenítési rétegnek (azaz magának a weboldalnak), amelyeket a user lát.*/

/** Model View Controller -->> a szoftvertervezésben használatos programtervezési minta. Összetett, sok adatot a
 * felhasználó elé táró számítógépes alkalmazásokban gyakori fejlesztői kívánalom az adathoz (modell) és a felhasználói
 * felülethez (nézet) tartozó dolgok szétválasztása, hogy a felhasználói felület ne befolyásolja az adatkezelést, és az
 * adatok átszervezhetők legyenek a felhasználói felület változtatása nélkül. A modell-nézet-vezérlő ezt úgy éri el,
 * hogy elkülöníti az adatok elérését és az üzleti logikát az adatok megjelenítésétől és a felhasználói interakciótól
 * egy közbülső összetevő, a vezérlő bevezetésével.*/
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** @GetMapping annotáció jelzi hogy HTTP GET kérések kiszolgálását fogja végezni ez a metódus, méghozzá a root útvonalon.
     *
     * Amikor a böngésző címsorába beírunk egy http(s) kezdetű címet, akkor a böngészőnk az adott címre egy GET kérést küld.
     * Mivel a gyökér (/) útvonalhoz tartozó Java metódus  @GetMapping-gel van felvéve, így kizárólag a GET típusú HTTP
     * kéréseket fogadja A kéréssel átadhatunk paramétereket is, amik az URL-ben meg is jelennek. A paraméterek lekezeléséhez
     * csak konstruktorban fel kell sorolunk őket, @RequestParam annotációval.*/
    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers(){
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    /** A @PostMapping annotáció a HTTP POST kéréseket kezeli. Ez egy annotáció,
     * amely a @RequestMapping (method = RequestMethod.POST)-ként működik.*/
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>("User is created.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("You have successfully deleted your account.");
    }

    @GetMapping("/findUser/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id){
        return new ResponseEntity(userService.findUser(id), HttpStatus.OK);
    }

}
