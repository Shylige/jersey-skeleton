package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ImageDto;
import fr.iutinfo.skeleton.common.dto.UserDto;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/image")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageResource {
	final static Logger logger = LoggerFactory.getLogger(ImageResource.class);
	private static ImageDao dao = getDbi().open(ImageDao.class);

	public ImageResource() throws SQLException {
		System.out.println("\n\n\nnew IMAGE\n\n\n");
		if (!tableExist("images")) {
			logger.debug("Crate table images");
			dao.createImageTable();
			dao.insert(new Image(1,1,"http://www.sudinfo.be/sites/default/files/dpistyles_v2/sp_4_3_inline/2017/11/09/node_20297/66382/public/2017/11/09/B9713764736Z.1_20171109161639_000+GH2A4J3V2.1-0.jpg?itok=gd6BLDgx"));
		}
		
	}
	
    @POST
    public ImageDto createImage(ImageDto img) {
        Image image = new Image();
        image.initFromDto(img);
        int id = dao.insert(image);
        img.setId(id);
        return img;
    }
	 
	@GET
	@Path("/solo/{id}")
	public ImageDto getImage(@PathParam("id") int id) {
		Image image = dao.findById(id);
		if (image == null) {
			throw new WebApplicationException(404);
		}
		return image.convertToDto();
	}

	@GET
	@Path("/{idClient}")
	public List<ImageDto> getAllImages(@QueryParam("idClient") int idClient) {
		List<Image> res;
		logger.debug("Search all Image with query: " + idClient);
		res= dao.getAllImage(idClient);
		return res.stream().map(Image::convertToDto).collect(Collectors.toList());
	}
	
	@GET
    @RolesAllowed({"admin"})
    public List<ImageDto> getAllUsers(@QueryParam("q") String query) {
        List<Image> image=null;
        if (query == null) {
            image = dao.all();
        } else {
            logger.debug("Search users with query: " + query);
            try {
            	image = dao.getAllImage(Integer.parseInt(query));
            }catch(Exception e) {
            	
            }
        }
        return image.stream().map(Image::convertToDto).collect(Collectors.toList());
    }

	@DELETE
	@Path("/{id}")
	public void deleteImage(@PathParam("id") int id) {
		dao.delete(id);
	}

}
