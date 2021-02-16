import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FirstTest {


    @Test
    public void givenNonExistingPetIdWhenGetPetThenPetNotFoundTest() {
        given()
                .log()
                .uri()
                .when()
                .get("https://petstore.swagger.io/v2/pet/{param}", 0)
                .then()
                .log()
                .status()
                .statusCode(404);
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenPetFoundTest() {
        given()
                .log()
                .uri()
                .pathParam("id", 1)
                .when()
                .get("https://petstore.swagger.io/v2/pet/{id}")
                .then()
                .log()
                .status()
                .statusCode(200);
    }
}
