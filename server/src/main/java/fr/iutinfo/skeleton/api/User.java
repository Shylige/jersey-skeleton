package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class User implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);
    private static User anonymous = new User(-1, "Anonymous", "anonym", "anonym","anonym", "anonym", "anonym");
    private String nom;
    private String prenom;
    private String login;
    private int id = 0;
    private String email;
    private String password;
    private String passwdHash;
    private String salt;
    private String search;
    private String tel;
    private String adresse;
/*
    public User(int id, String name) {
        this.id = id;
        this.nom = name;
    }
*/
    public User(int id, String login, String nom, String prenom, String email, String adresse, String tel) {
        this.id = id;
        this.nom = nom;
        this.login = login;
        this.adresse=adresse;
        this.prenom=prenom;
        this.tel=tel;
        this.email = email;
    }

    public User() {
    }

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public static User getAnonymousUser() {
        return anonymous;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        passwdHash = buildHash(password, getSalt());
        this.password = password;
    }

    private String buildHash(String password, String s) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password + s, Charsets.UTF_8);
        return hasher.hash().toString();
    }

    public boolean isGoodPassword(String password) {
        if (isAnonymous()) {
            return false;
        }
        String hash = buildHash(password, getSalt());
        return hash.equals(getPasswdHash());
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        User user = (User) arg;
        return nom.equals(user.nom) && login.equals(user.login) && email.equals(user.email) && passwdHash.equals(user.getPasswdHash()) && salt.equals((user.getSalt()));
    }

    @Override
    public String toString() {
        return id + ": " + login + ", " + nom + " <" + email + ">";
    }

    public String getAlias() {
        return login;
    }

    public void setAlias(String alias) {
        this.login = alias;
    }

    public String getSalt() {
        if (salt == null) {
            salt = generateSalt();
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putLong(random.nextLong());
        return hasher.hash().toString();
    }

    public void resetPasswordHash() {
        if (password != null && !password.isEmpty()) {
            setPassword(getPassword());
        }
    }

    public boolean isInUserGroup() {
        return !(id == anonymous.getId());
    }

    public boolean isAnonymous() {
        return this.getId() == getAnonymousUser().getId();
    }

    public String getSearch() {
        search = nom + " " + login + " " + email;
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void initFromDto(UserDto dto) {
    	System.out.println("\n\ninitFromDto\n\n");
    	this.setLogin(dto.getLogin());
        this.setEmail(dto.getEmail());
        this.setId(dto.getId());
        this.setNom(dto.getNom());
        this.setPassword(dto.getPassword());
        this.setAdresse(dto.getAdresse());
        this.setPrenom(dto.getPrenom());
        this.setTel(dto.getTel());
    }

    public UserDto convertToDto() {
    	System.out.println("\n\nConvertToDto\n\n");
        UserDto dto = new UserDto();
        dto.setLogin(this.getLogin());
        dto.setEmail(this.getEmail());
        dto.setId(this.getId());
        dto.setNom(this.getNom());
        dto.setPassword(this.getPassword());
        dto.setAdresse(this.getAdresse());
        dto.setTel(this.getTel());
        dto.setPrenom(this.getPrenom());
        return dto;
    }

	@Override
	public String getName() {
		return getNom();
	}
}
