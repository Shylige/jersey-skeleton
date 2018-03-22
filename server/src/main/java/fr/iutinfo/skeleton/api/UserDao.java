package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
    @SqlUpdate("create table users (id integer primary key autoincrement, nom varchar(100), login varchar(100), email varchar(100), passwdHash varchar(64), salt varchar(64), search varchar(1024), prenom text, adresse text, tel char(10))")
    void createUserTable();
    

    @SqlUpdate("insert into users (nom,login,email, passwdHash, salt, search,prenom,adresse,tel) values (:nom, :login, :email, :passwdHash, :salt, :search, :prenom,:adresse,:tel)")
    @GetGeneratedKeys
    int insert(@BindBean() User user);

    @SqlQuery("select * from users where login = :login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByLogin(@Bind("login") String login);

    @SqlQuery("select * from users where search like :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> search(@Bind("nom") String nom);

    @SqlUpdate("drop table if exists users")
    void dropUserTable();

    @SqlUpdate("delete from users where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from users order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();

    @SqlQuery("select * from users where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findById(@Bind("id") int id);

    void close();
}
