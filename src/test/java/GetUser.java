import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetUser {

    @Test
    public void givenExistingPUserIdWhenGetUserThenPetFoundTest(){
        given()
                .when()
                .pathParam("id", 123)
                .get("https://petstore.swagger.io/v2/pet/{id}")
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}
