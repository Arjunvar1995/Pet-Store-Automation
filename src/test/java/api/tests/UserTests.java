package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	//using this variable 'faker' we will be able to create dummy data
	
	User userPayload;
	//Creating a variable userPaylaod for the User POJO Class
	
	@BeforeClass
	public void prepareData() {
		
		faker=new Faker();
		//creating a faker Object
		//Similar to WedDriver driver=new chromeDriver();
		//Instead we are declaring it as a Global variable for the class and then creating an object seperately
		
		userPayload=new User();
		//Creating an Object
		
		//We are creating data using Faker and passing it to the POJO Class in parallel
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
				
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		
		Response response=UserEndPoints.createUserEP(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
				
	}
	
	@Test(priority=2)
	public void testRetrieveUser() {
		
		Response response=UserEndPoints.retrieveUserEP(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		
		userPayload.setEmail(faker.internet().safeEmailAddress());
		//We are generating new email address to update it to a new one
		
		Response response=UserEndPoints.updateUserEP(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		Response response=UserEndPoints.deleteUserEP(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Validating whether the user was deleted or not
		Response response1=UserEndPoints.retrieveUserEP(this.userPayload.getUsername());
		response1.then().log().all();
		
	}

}
