package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    final static Logger logger = LoggerFactory.getLogger(UserResource.class);
    private static UserDao dao = getDbi().open(UserDao.class);

    public UserResource() throws SQLException {
        if (!tableExist("users")) {
            logger.debug("Crate table users");
            dao.createUserTable();
            User admin = new User(1, "admin", "admin", "admin", "admin", "admin", "admin");
            admin.setPassword("admin");
            dao.insert(new User(0, "Margaret Thatcher", "la Dame de fer", "test", "LOL", "pp", "AHHHH"));
            dao.insert(admin);
            
        }
    }

    @POST
    public UserDto createUser(UserDto dto) {
    	List<UserDto> users=getAllUsers(null);
    	boolean ok=true;
    	for(UserDto u:users) {
    		if(u.getLogin().equals(dto.getLogin())) {
    			ok=false;
    		}
    	}
    	
    	if(!ok)return null;
    	
        User user = new User();
        user.initFromDto(dto);
        user.resetPasswordHash();
        int id = dao.insert(user);
        dto.setId(id);
        return dto;
    }

    @GET
    @Path("/{login}")
    public UserDto getUser(@PathParam("login") String login) {
        User user = dao.findByLogin(login);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        return user.convertToDto();
    }

    @GET
    @RolesAllowed({"admin"})
    public List<UserDto> getAllUsers(@QueryParam("q") String query) {
        List<User> users;
        if (query == null) {
            users = dao.all();
        } else {
            logger.debug("Search users with query: " + query);
            users = dao.search("%" + query + "%");
        }
        return users.stream().map(User::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public void deleteUser(@PathParam("id") int id) {
        dao.delete(id);
    }

}
