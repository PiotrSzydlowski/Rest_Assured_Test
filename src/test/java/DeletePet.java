import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeletePet {

    @Test
    public void deletePet(){
        given()
                .when()
                .pathParam("id", 123)
                .delete("https://petstore.swagger.io/v2/pet/{id}")
                .then()
                .log()
                .status()
                .statusCode(200);
        given()
                .when()
                .log()
                .uri()
                .pathParam("id", 123)
                .then()
                .statusCode(404);
    }
}
