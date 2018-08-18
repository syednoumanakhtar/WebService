package Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.POST;

import java.util.ArrayList;
import java.util.*;

import javax.ws.rs.Consumes;

@Path("/webservice")
public class JettyServerRequest {

	/*
	 * Request: curl -XGET '127.0.0.1:4090/webservice/get'
	 */
	@GET
	@Path("get")
	@Produces({"text/plain"})
	public String test() {
		return "Welcome to WebService system !";
	}

	/*
	 * Request: curl -H "Content-Type: application/json" -XPOST '127.0.0.1:4090/webservice/post' -d '{"name":"value"}'
	 */
	@POST
	@Path("post")
	@Produces({"application/json","application/xml","text/plain"})
	@Consumes({"application/json","application/xml"})    
	public String  processRequestPayload(String request) {

		System.out.println("Request PayLoad: "+request);
		return "Welcome to WebService system !";
	}
	
	/*
	 * Request: curl -XGET '127.0.0.1:4090/webservice/getparam?q=helloworld'
	 */
	@GET
	@Path("getparam")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response paramMethod(@QueryParam("q") String paramq) {

		System.out.println("Request PayLoad: "+paramq);
		
		ResponseBuilder clientResponse = Response.noContent();
		clientResponse.status(Response.Status.ACCEPTED);
		clientResponse.entity("Welcome to WebService system ! Param: "+paramq);
		
		return clientResponse.build();
	}
	
	@GET
	@Path("getjson")
	@Produces({MediaType.APPLICATION_JSON})
	public Response helloJson() {

		String responseBody = null;
		ObjectMapper mapper = new ObjectMapper();
		List<Student> studentList = new ArrayList<Student>();
		
		Student s1 = new Student();
		s1.setName("Nouman");
		s1.setAge(26);
		
		Student s2 = new Student();
		s2.setName("Ankur");
		s2.setAge(24);
		
		studentList.add(s1);
		studentList.add(s2);
		
		try {
			responseBody = mapper.writeValueAsString(studentList);
			System.out.println("Response: "+responseBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		/*
		GenericEntity<List<Student>> list = new GenericEntity<List<Student>>(studentList) {};
		return Response.ok(list).build();
		*/
		
		ResponseBuilder clientResponse = Response.noContent();
		clientResponse.status(Response.Status.OK);
		clientResponse.entity(responseBody);
		
		return clientResponse.build();
	}

}
