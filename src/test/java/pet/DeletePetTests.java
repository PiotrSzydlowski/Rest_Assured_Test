package pet;

import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBases.SuiteTestBase;

import static io.restassured.RestAssured.given;

public class DeletePetTests extends SuiteTestBase {

    private int nonExistingPetId;

    @BeforeMethod
    public void beforeDeleteTest(){
        nonExistingPetId = new Faker().number().numberBetween(1000, 1000);
        given()
                .when()
                .delete("pet/{petId}", nonExistingPetId);
    }

    @Test
    public void givenNonExistingPetWhenDeletingPetThenPetNotFoundTest(){
            given()
                    .when()
                    .delete("pet/{petId}", nonExistingPetId)
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
