package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.User;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * Ez egy interface(felület), amit kibővítünk a CrudRepository-val, ami metódusokat biztosít nekünk.
 * Ezeket implementálni tudjuk az osztályunkba. pl.: save, saveAll, findById, existsById, findAll,
 * findById, count, delete, deleteById, deleteAll stb... a <> közé be kell írni az
 * osztály/entitás/generikus nevét, és az id tipusát Így tudunk majd könnyen interakciókat végezni
 * az adatbázisunkon bármilyen metódus megírrása nélkül, mert ezeket a metódusokat implementálni
 * tudjuk az osztályunkba, mintha az osztályon belül írtuk volna meg azokat. ==>> CRUD - Create,
 * Read, Update, Delete
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

  /*
   * Override-olja a Crud repository findAll metódusát, hogy List-et adjon vissza. Erre azért van
   * szükség, hogy egy listát kaphassunk az össze User-ből.
   */
  List<User> findAll();

  Optional<User> findByUsername(String userName);

  /* @Transactional */
  Optional<User> deleteUsersById(Long id);

}
