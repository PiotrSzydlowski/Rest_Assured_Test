package pet;

import dataGenerator.PetTestDataGenerator;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.pet.ApiResponse;
import pojo.pet.Pet;
import testBases.SuiteTestBase;

import static io.restassured.RestAssured.given;

public class DeletePetTest extends SuiteTestBase {

    Pet pet;

    @BeforeMethod
    public void beforeDeleteTest(){
      pet = new PetTestDataGenerator().generatePet();
        given()
                .body(pet)
                .contentType("application/json")
                .when()
                .post("pet")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void deleteExistingPet(){
        given()
                .when()
                .delete("pet/{petId}", pet.getId())
                .then()
                .statusCode(HttpStatus.SC_OK);
        given()
                .pathParam("id", pet.getId())
                .when()
                .get("pet/{id}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
