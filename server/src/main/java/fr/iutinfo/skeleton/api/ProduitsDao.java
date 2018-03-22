package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface ProduitsDao {
	@SqlUpdate("create table produits (id integer primary key autoincrement, nom text, prix int, image text, description text)")
	void createProduitsTable();
	
	@SqlUpdate("drop table if exists produits")
    void dropProduitsTable();

	@SqlQuery("select * from produits where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Produits findById(@Bind("id") int id);
	
	@SqlQuery("select * from produits order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Produits> all();

	@SqlUpdate("insert into produits (nom,prix,image,description) values (:nom,:prix,:image,:description)")
	@GetGeneratedKeys
	int insert(@BindBean() Produits p);

	@SqlUpdate("delete from produits where id = :id")
	void delete(@Bind("id") int id);
}
