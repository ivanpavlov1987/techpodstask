package API;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CRUDOperations {
    public static Response apiGetCall(String url) {
        RequestSpecification request = given().when();
        return request.get(url);
    }

    public static Response apiPostCall(String url, String requestBody) {
        RequestSpecification request = given();
        request.contentType(ContentType.JSON)
                .body(requestBody);
        return request.post(url);
    }

    public static Response apiPutCall(String url, String requestBody) {
        RequestSpecification request = given();
        request.contentType(ContentType.JSON)
                .body(requestBody);
        return request.put(url);
    }

    public static Response apiDeleteCall(String url, String requestBody) {
        RequestSpecification request = given();
        request.contentType(ContentType.JSON)
                .body(requestBody);
        return request.delete(url);
    }
}
