package api.v1;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.ekipaenajst.entitete.*;
import org.ekipaenajst.beans.ParkirninaZrno;


import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

@Path("parkirnine")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class Server {

    private String frontendURL;

    @PostConstruct
    public void init() {

        Map<String,String> env = System.getenv();

        frontendURL = env.get("FRONTEND_URL");
    }

    @Inject
    private ParkirninaZrno parkirninaZrno;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response vrniParkirnine(){

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Parkirnina> parkirnine = parkirninaZrno.getParkirnine(query);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(parkirnine).build();
    }

    @GET
    @Path("{id}")
    public Response vrniParkirnino(@PathParam("id") int id){

        Parkirnina parkirnina = parkirninaZrno.getParkirninaById(id);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(parkirnina).build();
    }

    @POST
    public Response dodajParkirnino(Parkirnina parkirnina){

        parkirninaZrno.createParkirnina(parkirnina); //če kupiš več listkov hkrati je literally skillissue
        /*if (==null) {
            return Response.status(Response.Status.FOUND).build();
        }*/

        return Response.status(Response.Status.CREATED)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(parkirnina).build();
    }

    // ZA TE TRI NISM ZIHR KAJ VRNIT (bi moral bit ok??????????)
    @PUT
    // @Path(blabla)
    public Response posodobiParkirnino(Parkirnina parkirnina){

        parkirninaZrno.updateParkirnina(parkirnina);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(parkirnina).build();
    }


    @DELETE
    @Path("{id}")
    public Response izbrisiParkirnino(@PathParam("id") int id){

        Parkirnina parkirnina = parkirninaZrno.getParkirninaById(id);

        parkirninaZrno.deleteParkirnina(parkirnina);

        return Response.noContent()
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }
}
