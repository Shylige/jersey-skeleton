package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.CommandeDto;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/commande")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandeResource {
	final static Logger logger = LoggerFactory.getLogger(CommandeResource.class);
	private static CommandeDao dao = getDbi().open(CommandeDao.class);

	public CommandeResource() throws SQLException {
		if (!tableExist("produits")) {
			logger.debug("Create table commande");
			dao.createCommandeTable();
			dao.insert(new Commande(1,1,1,"test",1,true,"bleu","primtemps",false));
		}

	}

	@POST
	public CommandeDto createCommande(CommandeDto c) {
		Commande commande = new Commande();
		commande.initFromDto(c);
		int id = dao.insert(commande);
		c.setId(id);
		return c;
	}

	@GET
	@Path("/{id}")
	public CommandeDto getCommande(@PathParam("id") int id) {
		Commande c = dao.findById(id);
		logger.debug("Search Produits with query: " + id);
		if(c==null) {
			throw new WebApplicationException(404);
		}
		return c.convertToDto();
	}

	@GET
	@RolesAllowed({"admin"})
	public List<CommandeDto> getAllCommande() {
		List<Commande> c=null;
		c = dao.all();
		logger.debug("Search produit");
		return c.stream().map(Commande::convertToDto).collect(Collectors.toList());
	}

	@DELETE
	@Path("/{id}")
	public void deleteCommande(@PathParam("id") int id) {
		dao.delete(id);
	}

}
