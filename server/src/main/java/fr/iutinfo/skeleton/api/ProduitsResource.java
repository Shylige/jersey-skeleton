package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ImageDto;
import fr.iutinfo.skeleton.common.dto.ProduitsDto;
import fr.iutinfo.skeleton.common.dto.UserDto;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/produit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProduitsResource {
	final static Logger logger = LoggerFactory.getLogger(ProduitsResource.class);
	private static ProduitsDao dao = getDbi().open(ProduitsDao.class);

	public ProduitsResource() throws SQLException {
		if (!tableExist("produits")) {
			logger.debug("Crate table produits");
			dao.createProduitsTable();
			
			//Ajout du premier produit présent sur le site
			dao.insert(new Produits(0,"Affiche – Portrait unique – Nu",33.00,"http://ceuxquinouslient.com/wp-content/uploads/2018/03/papou-600x849.png","Des portrait d'antan pour petits et grands.\n" + 
					"\n" + 
					"Silhouettes fabriquées à partir de vos photos et imprimées en haute qualité sur des feuilles Canson A4 (320 gr.) ou A3 (210 gr.). Livré sans leur cadre."));
			
			//Ajout du second produit présent sur le site
			dao.insert(new Produits(1, "Affiche – Portrait unique – Avec couronne", 38.00,"http://ceuxquinouslient.com/wp-content/uploads/2018/03/couronne-1-black-600x849.png", "Des portraits d'antan pour petits et grands.\n" +
					"\n" +
					"Silhouettes fabriquées à partir de vos photos et imprimées en haute qualité sur des feuilles Canson A4 (320 gr.) ou A3 (210 gr.). Livré sans leur cadre."));
			
			//Ajout du troisième produit présent sur le site
			dao.insert(new Produits(2, "Bois – Portrait unique – Nu", 42.00, "http://ceuxquinouslient.com/wp-content/uploads/2018/03/DSC_0835-600x401.jpg", "Des portrait d'antan pour petits et grands.\n" +
					"\n" +
					"Silhouettes fabriquées à partir de vos photos, gravées sur du bois découpé. En partenariat avec la Petite Créative."));
			
			//Ajout du quatrième produit présent sur le site
			dao.insert(new Produits(3, "Affiche – Portrait de groupe – Nu", 44.00, "http://ceuxquinouslient.com/wp-content/uploads/2018/03/Ceux-qui-nous-lient-soeurs-600x424.png", "Des portraits d'antan pour petits et grands.\n" +
					"\n" +
					"Silhouettes fabriquées à partir de vos photos et imprimées en haute qualité sur des feuilles Canson A4 (320gr.) pour 4 portraits max. ou A3 (210 gr.). Livré sans leur cadre. \n" + "Prix : 44 euros pour 2 profils (+11 euros par profil supplémentaire) \n"));
			
			//Ajout du cinquième produit présent sur le site
			dao.insert(new Produits(4, "Affiche – Portrait de groupe – Avec couronne", 44.00, "http://ceuxquinouslient.com/wp-content/uploads/2018/03/famille-vander-600x424.png", "Des portraits d'antan pour petits et grands.\n" +
					"\n" +
					"Silhouettes fabriquées à partir de vos photos et imprimées en haute qualité sur des feuilles Canson A3 (210 gr.). Livré sans leur cadre. \n" + "Prix : 44 euros pour 2 profils (+11 euros par profil supplémentaire) \n"));
			
			//Ajout du sixième produit présent sur le site
			dao.insert(new Produits(5, "Bois – Portrait unique – Avec couronne", 46.00, "http://ceuxquinouslient.com/wp-content/uploads/2018/03/DSC_0928-600x401.jpg", "Des portrait d'antan pour petits et grands.\n" +
					"\n" +
					"Silhouettes fabriquées à partir de vos photos gravé sur du bois découpé. En partenariat avec la Petite Créative."));
		}

	}

	@POST
	public ProduitsDto createProduits(ProduitsDto p) {
		Produits produit = new Produits();
		produit.initFromDto(p);
		int id = dao.insert(produit);
		p.setId(id);
		return p;
	}

	@GET
	@Path("/{id}")
	public ProduitsDto getProduits(@QueryParam("id") int id) {
		logger.debug("SearchProduits with query: " + id);
		Produits p=dao.findById(id);
		if(p==null) throw new WebApplicationException(404);
		return p.convertToDto();
	}

	@GET
	@RolesAllowed({"admin"})
	public List<ProduitsDto> getAllProduits() {
		List<Produits> p=null;
		p = dao.all();
		logger.debug("Search produit");
		return p.stream().map(Produits::convertToDto).collect(Collectors.toList());
	}

	@DELETE
	@Path("/{id}")
	public void deleteImage(@PathParam("id") int id) {
		dao.delete(id);
	}

}
