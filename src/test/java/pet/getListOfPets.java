package pet;

import dataGenerator.PetTestDataGenerator;
import dataGenerator.pets.PetStatus;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.pet.Pet;
import testBases.SuiteTestBase;
import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class getListOfPets extends SuiteTestBase {

    private List<Pet> availablePetsList = new LinkedList<>();

    @BeforeMethod
    public void beforeGetListMethod() {
        for (int i = 0; i < 3; i++) {
            Pet generatedPet = new PetTestDataGenerator().generatePet();
            generatedPet.setStatus(PetStatus.SOLD.getStatus());
            given()
                    .body(generatedPet)
                    .contentType("application/json")
                    .when()
                    .post("pet")
                    .then()
                    .statusCode(HttpStatus.SC_OK);
            availablePetsList.add(generatedPet);
        }
    }

    @Test
    public void getPetsList(){
        Pet[] ListOfPetsWithSoldStatus = given()
                .queryParam("status", PetStatus.SOLD.getStatus())
                .when()
                .get("pet/findByStatus")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Pet[].class);

        Assertions.assertThat(ListOfPetsWithSoldStatus)
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(availablePetsList);
    }

    @AfterMethod
    public void clenUpAfterTest(){
        availablePetsList.forEach(pet ->{
            given()
                    .when()
                    .delete("pet/{petId}", pet.getId())
                    .then()
                    .statusCode(HttpStatus.SC_OK);
        });

    }
}
