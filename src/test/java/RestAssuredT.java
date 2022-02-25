import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;


public class RestAssuredT {
    @Test
    public void testGetAllUsers() {
        String responseBody =
                RestAssured
                        .given()
                        .baseUri("https://gorest.co.in")
                        .when()
                        .get("/public/v2/users")
                        .then()
                        .statusCode(200)
                        .extract().asString();
        System.out.println("responseBody = " + responseBody);
    }

    @Test
    public void testGetUserWithId() {
        String str = String.valueOf(RestAssured
                .given()
                .baseUri("https://gorest.co.in")
                .when()
                .get("/public/v2/users?id=3941")
                .then()
                .statusCode(200));
    }

    @Test
    public void testCreateUser() {
        final String body =
                "{\"id\":1111,\"name\":\"name4 name\",\"email\":\"email4@yopmail.com\",\"gender\":\"male\",\"status\":\"active\"}";
        String responseData = RestAssured
                .given().baseUri("https://gorest.co.in")
                .body(body)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 7f80b8a5e2a657a568eae1c819b4a64fd76b14f1db749d005c9a9c7ab41690fa")
                .when()
                .post("/public/v2/users")
                .then()
                .statusCode(201)
                .extract().asString();
        System.out.println("Response Data " + responseData);
    }

    @Test
    public void testDeleteUser() {
        RestAssured
                .given()
                .baseUri("https://gorest.co.in")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 7f80b8a5e2a657a568eae1c819b4a64fd76b14f1db749d005c9a9c7ab41690fa")
                .log().all()
                .when()
                .delete("/public/v2/users?id=1111")
                .then()
                .log().all()
                .statusCode(200);
    }

}
