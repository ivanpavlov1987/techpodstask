package API.tests;

import API.CRUDOperations;
import API.pojo.UserDetailsResponse;
import API.pojo.UserDetailsUpdatedResponse;
import API.pojo.UsersPageResponse;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class APITests {

    private final static String url = "https://reqres.in/api/users/";
    private static int createdUserId;

    @Test
    public void getUserList() {
        Response response = CRUDOperations.apiGetCall(url);
        Assert.assertEquals(response.statusCode(), 200);
        Gson g = new Gson();
        List<UsersPageResponse> userList = Arrays.asList(g.fromJson(response.prettyPrint(), UsersPageResponse.class));
        Assert.assertFalse(userList.get(0).data.isEmpty());
    }

    @Test
    public void postUser() {
        UserDetailsResponse user = generateUser();
        Gson g = new Gson();
        String jsonUser = g.toJson(user);
        Response response = CRUDOperations.apiPostCall(url, jsonUser);
        Assert.assertEquals(response.statusCode(), 201);
        UserDetailsResponse createdUser = g.fromJson(response.prettyPrint(), UserDetailsResponse.class);
        createdUserId = createdUser.getId();
        Assert.assertEquals(createdUser.getFirstName(), user.getFirstName());
    }

    @Test(dependsOnMethods={"postUser"})
    public void updateUser() {
        UserDetailsResponse user = generateUser();
        user.setFirstName("ivan");
        Gson g = new Gson();
        String jsonUser = g.toJson(user);
        Response response = CRUDOperations.apiPutCall(url + createdUserId, jsonUser);
        Assert.assertEquals(response.statusCode(), 200);
        UserDetailsUpdatedResponse updatedUser = g.fromJson(response.prettyPrint(), UserDetailsUpdatedResponse.class);
        Assert.assertEquals(updatedUser.getFirstName(), user.getFirstName());
    }

    @Test(dependsOnMethods={"updateUser"})
    public void deleteUser() {
        Response response = CRUDOperations.apiDeleteCall(url + createdUserId, "");
        Assert.assertEquals(response.statusCode(), 204);
    }

    private UserDetailsResponse generateUser() {
        UserDetailsResponse user = new UserDetailsResponse();
        user.setEmail("someone@something.com");
        user.setFirstName("some");
        user.setLastName("one");
        user.setAvatar("www.qwerty.com/img1");
        return user;
    }
}

