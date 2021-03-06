package pet;

import dataGenerator.PetTestDataGenerator;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pojo.pet.ApiResponse;
import pojo.pet.Pet;
import testBases.SuiteTestBase;
import static io.restassured.RestAssured.given;

public class CreatePetTests extends SuiteTestBase {

    private Pet actualPet;

    @Test
    public void shouldPetCreatedAfterPostMethod() {

        Pet pet = new PetTestDataGenerator().generatePet();

        actualPet = given()
                .body(pet)
                .contentType("application/json")
                .when()
                .post("pet")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Pet.class);

        Assertions.assertThat(actualPet).usingRecursiveComparison().isEqualTo(pet);
    }

    @AfterMethod
    public void cleanUpAfterTest(){
        ApiResponse apiResponse = given().contentType("application/json")
                .when()
                .delete("pet/{petId}", actualPet.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ApiResponse.class);

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(HttpStatus.SC_OK);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(actualPet.getId().toString());

        Assertions.assertThat(apiResponse).usingRecursiveComparison().isEqualTo(expectedApiResponse);

    }
}
