package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker fake ;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		fake=new Faker();
		userPayload=new User();
		
		userPayload.setId(fake.idNumber().hashCode());
		userPayload.setUsername(fake.name().username());
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().safeEmailAddress());
		userPayload.setPassword(fake.internet().password(5,10));
		userPayload.setPhone(fake.phoneNumber().cellPhone());
		
		//logs
		logger =LogManager.getLogger(this.getClass());
	}
	@Test(priority = 1)
	public void testPostuser(){
		logger.info("Creating user");
		Response response=UserEndPoints2.createUser(userPayload);
		response.then().log().all();	
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" user created ");
		}
	
	@Test(priority = 2)
	public void testGetUser() {
		logger.info("Reading user");
		Response response=UserEndPoints2.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user info is displayed");
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("Updating user");
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().safeEmailAddress());
		userPayload.setPassword(fake.internet().password(5,10));
		
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user is Updated");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("deleting user");
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user is deleted");
	}
}
