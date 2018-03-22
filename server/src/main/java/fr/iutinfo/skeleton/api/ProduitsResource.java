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
		if (!tableExist("images")) {
			logger.debug("Crate table images");
			dao.createProduitsTable();
			dao.insert(new Produits(0,"Affiche – Portrait unique – Nu",33.00,"http://ceuxquinouslient.com/wp-content/uploads/2018/03/papou-600x849.png","Des portrait d'antan pour petits et grands.\n" + 
					"\n" + 
					"Silhouettes fabriquées à partir de vos photos et imprimées en haute qualité sur des feuilles Canson A4 (320 gr.) ou A3 (210 gr.). Livré sans leur cadre."));
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
		logger.debug("Search all Produits with query: " + id);
		return dao.findById(id).convertToDto();
	}
	
	@GET
    @RolesAllowed({"admin"})
    public List<ProduitsDto> getAllUsers(@QueryParam("q") String query) {
        List<Produits> image=null;
        if (query == null) {
            image = dao.all();
        } else {
            logger.debug("Search users with query: " + query);
            try {
            	image = dao.all();
            }catch(Exception e) {
            	
            }
        }
        return image.stream().map(Produits::convertToDto).collect(Collectors.toList());
    }

	@DELETE
	@Path("/{id}")
	public void deleteImage(@PathParam("id") int id) {
		dao.delete(id);
	}

}
