package book.rest.application;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.process.ProcessLog.Level;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import book.rest.application.model.Book;

/**
 * @author Jatin
 */
@Component(property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/books",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=books.Rest" }, service = Application.class)
public class BookRestApplication extends Application {

	private static final Log _log = LogFactoryUtil.getLog(BookRestApplication.class);

	/*
	 * Api which add book entry in liferay object of Book
	 * 
	 * @Param Book book - it's a pojo( plain old Java object) class
	 */
	@POST
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addbook")
	public String addBook(Book book) {
		System.out.println("Method is called");
		System.out.println("Book Detail : " + book.toString());
		
		// formating the date in ISO
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String publishDate = dateFormat.format(book.getPublishDate());
		
		// create a JSONObject to add to book detail 
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("bookName", book.getBookName());
		jsonObject.put("author", book.getAuthor());
		jsonObject.put("publishDate", publishDate);
		jsonObject.put("bookType", book.getBookType());
		jsonObject.put("price", book.getPrice());
		jsonObject.put("createdBy", book.getScreenName());
		String requestBody = jsonObject.toString();
		String apiURL = "http://localhost:8080/o/c/books/";
		callApi(apiURL, "POST", requestBody);

		return "Book Added SuccessFully";
	}

	/*
	 * Api which edit particular book entry in liferay object
	 * 
	 * @Param Book book -  it's a pojo( plain old Java object) class
	 * 
	 * @Param long bookId - it's book Id of particular book object to edit book
	 */
	@PUT
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/editbook/{bookId}")
	public String editBook(Book book, @PathParam("bookId") long bookId) {
		System.out.println("Method is called" + bookId);
		System.out.println("Book Detail : " + book.toString());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String publishDate = dateFormat.format(book.getPublishDate());

		//create a json object
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("bookName", book.getBookName());
		jsonObject.put("author", book.getAuthor());
		jsonObject.put("publishDate", publishDate);
		jsonObject.put("bookType", book.getBookType());
		jsonObject.put("price", book.getPrice());
		jsonObject.put("createdBy", book.getScreenName());
		String requestBody = jsonObject.toString();
		String apiURL = "http://localhost:8080/o/c/books/" + bookId;
		callApi(apiURL, "PUT", requestBody);

		return "Book Updated SuccessFully";
	}

	/*
	 * Api which delete particular entry based on bookId in liferay object of Book
	 * 
	 * @Param long bookId
	 */
	@DELETE
	@Produces("text/plain")
	@Path("/deletebook/{bookId}")
	public String deleteBook(@PathParam("bookId") long bookId) {
		System.out.println("Method is called");

		String apiURL = "http://localhost:8080/o/c/books/" + bookId;
		callApi(apiURL, "DELETE", null);

		return "Book Deleted SuccessFully";
	}

	/*
	 * API which Search book by author name and return particular book which matche
	 * author name
	 * 
	 * @Param String author - which search book by author name
	 */

	@GET
	@Path("/searchByAuthor")
	public Response searchBooksByAuthor(@QueryParam("author") String author) {
		try {
			if (Validator.isNull(author)) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Author name is required").build();
			}

			long objectDefinitionId = 53415;

			// Create the dynamic query
			DynamicQuery dynamicQuery = _objectEntryLocalService.dynamicQuery();
			dynamicQuery.add(PropertyFactoryUtil.forName("objectDefinitionId").eq(objectDefinitionId));

			// Execute the query
			List<ObjectEntry> objectEntries = _objectEntryLocalService.dynamicQuery(dynamicQuery);
			// Filter by author in ObjectEntries
			List<ObjectEntry> filteredEntries = objectEntries.stream().filter(entry -> {
				String entryAuthor = (String) entry.getValues().get("author");
				return entryAuthor != null && entryAuthor.equalsIgnoreCase(author);
			}).collect(Collectors.toList());

			// Check if there are any filtered entries empty
			if (filteredEntries.isEmpty()) {
				return Response.status(Response.Status.OK).entity("No book data available").build();
			}

			// Build JSON response
			JSONObject response = JSONFactoryUtil.createJSONObject();
			for (ObjectEntry objectEntry : filteredEntries) {
				// Create a new JSONObject for each objectEntry
				JSONObject bookJson = JSONFactoryUtil.createJSONObject();
				bookJson.put("author", objectEntry.getValues().get("author"));
				bookJson.put("bookName", objectEntry.getValues().get("bookName"));
				bookJson.put("bookType", objectEntry.getValues().get("bookType"));
				bookJson.put("price", objectEntry.getValues().get("price"));
				bookJson.put("publishDate", objectEntry.getValues().get("publishDate"));
				bookJson.put("createdBy", objectEntry.getValues().get("createdBy"));

				// Add the JSONObject to the response using the objectEntry's ID as the key
				response.put(String.valueOf(objectEntry.getObjectEntryId()), bookJson);
			}

			return Response.ok(response.toString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	/*
	 * Common Method For Call Api
	 * 
	 * @Param String apiUrl - it's take a api url for call
	 * 
	 * @Param String method - it's method mean's api Http method like GET,PUT,Post
	 * like...
	 * 
	 * @Param String requestBody - it's take request body form of jsonobject
	 */
	public String callApi(String apiUrl, String method, String requestBody) {
		StringBuilder response = new StringBuilder();
		// Provide email and password for authentication of api
		String email = "test@liferay.com";
		String password = "123";
		String auth = email + ":" + password;
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
		String authHeader = "Basic " + encodedAuth;

		try {
			URL url = new URL(apiUrl);
			// HttpURLConnection which help to connect to url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", authHeader);
			
			// to chech if requestBody is empty or not 
			if (requestBody != null && !requestBody.isEmpty()) {
				try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
					writer.write(requestBody);
					writer.flush();
				}
			}
			
			// Check if Status code for response is ok or 204
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == 204) {
				_log.info("API call successful");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				while ((inputLine = bufferedReader.readLine()) != null) {
					response.append(inputLine);
				}
				bufferedReader.close();
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
		} catch (Exception e) {
			_log.error("Error while calling API", e);
			return null;
		}

		return response.toString();
	}
	

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;
}