package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface ImageDao {
	@SqlUpdate("create table images (id integer primary key autoincrement, idClient int, url text)")
	void createImageTable();
	
	@SqlUpdate("drop table if exists images")
    void dropImageTable();

	@SqlQuery("select * from images where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Image findById(@Bind("id") int id);

	@SqlQuery("select * from images where idClient = :idClient")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Image> getAllImage(@Bind("idClient") int idClient);

	@SqlUpdate("insert into images (idClient,url) values (:idClient,:url)")
	@GetGeneratedKeys
	int insert(@BindBean() Image image);

	@SqlUpdate("delete from users where id = :id")
	void delete(@Bind("id") int id);
}
