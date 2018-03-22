/*package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.GenericType;
import java.util.List;

public class Helper {
    private final static Logger logger = LoggerFactory.getLogger(Helper.class);
    private static final UserDao dao = BDDFactory.getDbi().open(UserDao.class);
    private static final ImageDao daoImg = BDDFactory.getDbi().open(ImageDao.class);
    static GenericType<List<UserDto>> listUserResponseType = new GenericType<List<UserDto>>() {
    };

    public static void initDb() {
    	System.out.println("TEST INITDB");
        dao.dropUserTable();
        dao.createUserTable();
        daoImg.dropImageTable();
        daoImg.createImageTable();
        System.out.println("TEST FIN INITDB");
    }
    // test

    static User createUserWithName(String name) {
        User user = new User(0, null, name, null, null, null, null);
        return createUser(user);
    }
    
    static User createUserWithFirstName(String name, String firstName) {
        User user = new User(0, null, name, firstName, null, null, null);
        return createUser(user);
    }

    static User createUserWithLogin(String name, String login) {
        User user = new User(0, login, name, null, null, null, null);
        return createUser(user);
    }

    static User createUserWithEmail(String name, String email) {
        User user = new User(0, null, name, null, email, null, null);
        user.setEmail(email);
        return createUser(user);
    }

    public static User createUserWithPassword(String name, String passwd, String salt) {
        User user = new User(0, null, name, null, null, null, null);
        user.setSalt(salt);
        user.setPassword(passwd);
        logger.debug("createUserWithPassword Hash : " + user.getPasswdHash());
        return createUser(user);
    }

    private static User createUser(User user) {
        int id = dao.insert(user);
        user.setId(id);
        return user;
    }


    private static User createFullUSer(String name, String firstName, String alias, String email, String adresse, String tel, String paswword) {
        User user = new User(0, null, name, firstName, null, adresse, tel);
        user.setAlias(alias);
        user.setEmail(email);
        user.setPassword(paswword);
        int id = dao.insert(user);
        user.setId(id);
        return user;
    }

    static void createRms() {
        createFullUSer("Richard", "Stallman", "RMS", "rms@fsf.org","Rue Didier Deschamps","0607080910", "gnuPaswword");
    }

    static User createRob() {
        return createFullUSer("Robert", "Capillo", "rob", "rob@fsf.org", "Rue Laurent Blanc", "0612345678", "paswword");
    }

    static User createLinus() {
        return createFullUSer("Linus", "Torvalds", "linus", "linus@linux.org", "Rue Alfred fred", "0611821800", "paswword");
    }

    static User createIan() {
        return createFullUSer("Ian", "Murdock", "debian", "ian@debian.org", "Rue de HellKitchen", "0752258964", "mot de passe");
    }
}
*/