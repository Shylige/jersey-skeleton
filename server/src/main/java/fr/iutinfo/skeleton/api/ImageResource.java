package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageResource {
	final static Logger logger = LoggerFactory.getLogger(ImageResource.class);
	private static ImageDao dao = getDbi().open(ImageDao.class);

	public ImageResource() throws SQLException {
		if (!tableExist("images")) {
			logger.debug("Crate table images");
			dao.createImageTable();
			dao.insert(new Image(1,1,"http://www.sudinfo.be/sites/default/files/dpistyles_v2/sp_4_3_inline/2017/11/09/node_20297/66382/public/2017/11/09/B9713764736Z.1_20171109161639_000+GH2A4J3V2.1-0.jpg?itok=gd6BLDgx"));
		}
	}
	
    @POST
    public Image createImage(Image img) {
        Image image = new Image();
        int id = dao.insert(image);
        image.setId(id);
        return image;
    }
	 
	@GET
	@Path("/{id}")
	public Image getImage(@PathParam("id") int id) {
		Image image = dao.findById(id);
		if (image == null) {
			throw new WebApplicationException(404);
		}
		return image;
	}

	@GET
	public List<Image> getAllImages(@QueryParam("idClient") int idClient) {
		List<Image> res;
		logger.debug("Search all Image with query: " + idClient);
		res= dao.getAllImage(idClient);
		return res;
	}

	@DELETE
	@Path("/{id}")
	public void deleteUser(@PathParam("id") int id) {
		dao.delete(id);
	}

}
