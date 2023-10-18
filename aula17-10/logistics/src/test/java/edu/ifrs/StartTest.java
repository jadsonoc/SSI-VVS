package edu.ifrs;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import org.junit.jupiter.api.Test;

import edu.ifrs.business.Load;
import edu.ifrs.business.Vehicle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hamcrest.Matchers;

@QuarkusTest
public class StartTest {

    /* 
    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }
    */

    @Test
    public void testGetVehicleEndpoint200OK() {
        given()
                .when().get("/logistics/getVehicle/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetVehiclesEndpoint200OK() {
        given()
                .when().get("/logistics/getVehicles")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetVehiclesEndpointContainsAtLeastOne() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/logistics/getVehicles")
                .then()
                .assertThat()
                .body("$.size()", greaterThan(0));
    }

    @Test
    public void testGetVehiclesEndpointContainsCorrectAmmount() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/logistics/getVehicles")
                .then()
                .assertThat()
                .body("$.size()", is(2));
    }

    @Test
    public void testGetVehiclesEndpointFirstVehicle() {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
                .when()
                .get("/logistics/getVehicles")
                .then()
                .statusCode(200)
                .and()
                .body("[0].maximumWeightLimit", equalTo(200))
                .and()
                .body("[0].loads.weight[0]", equalTo(50))
                .and()
                .body("[0].loads.weight[1]", equalTo(50));
    }

    /* A ideia pareceu boa, mas não funcionou */
    // @Test
    // public void testGetVehiclesEndpointFirstVehicle() {
    //     JsonPath path = given()
    //             .header("Accept", "application/json")
    //             .get("/logistics/getVehicles")
    //             .andReturn().jsonPath();

    //     List<Vehicle> responseVehicles = path.getList("$", Vehicle.class);
    //     Vehicle esperado1 = new Vehicle(200);
    //             esperado1.addWeight(new Load(50));
    //             esperado1.addWeight(new Load(50));

    //     assertEquals(esperado1, responseVehicles.get(0));
    // }

}