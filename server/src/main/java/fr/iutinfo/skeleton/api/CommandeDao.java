package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface CommandeDao {
	@SqlUpdate("create table commandes (id integer primary key autoincrement, idClient integer, idProduit integer,prenom text, idImage integer, regard boolean, couleur text,typo text, format boolean)")
	void createCommandeTable();
	
	@SqlUpdate("drop table if exists commandes")
    void dropCommandeTable();

	@SqlQuery("select * from commandes where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Commande findById(@Bind("id") int id);
	
	@SqlQuery("select * from commandes order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Commande> all();
	
	@SqlQuery("select * from commandes where idClient = :idClient")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Commande> findByidClient(@Bind("idClient") int idClient);

	@SqlUpdate("insert into commandes (idClient,idProduit,prenom,idImage,regard,couleur,typo,format) values (:idClient,:idProduit,:prenom,:idImage,:regard,:couleur,:typo,:format)")
	@GetGeneratedKeys
	int insert(@BindBean() Commande c);

	@SqlUpdate("delete from commandes where id = :id")
	void delete(@Bind("id") int id);
}
