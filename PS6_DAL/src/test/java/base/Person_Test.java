package base;

import static org.junit.Assert.*;



import java.util.Date;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
public class Person_Test {

	private static PersonDomainModel per1= new PersonDomainModel();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	per1.setPersonID(UUID.randomUUID());
	
	per1.setFirstName("Brock");
	
	per1.setBirthday(new Date(0));
	per1.setLastName("Palmer");
	per1.setCity("Ellicott");
	per1.setPostalCode(21042);
	per1.setStreet("Some street");
	PersonDAL.addPerson(per1);
	//testing adding
	PersonDomainModel per2 = PersonDAL.getPerson(per1.getPersonID());
	assertNotNull(per2);
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	PersonDAL.deletePerson(per1.getPersonID());

	}
	@Test
	public void getPerson(){
		PersonDomainModel test= PersonDAL.getPerson(per1.getPersonID());
		assertTrue(per1.getFirstName()==test.getFirstName());
	}
	
	@Test
	public void getAllPersons() {
		ArrayList<PersonDomainModel> per= PersonDAL.getPersons();
		assertNotNull(per);		
		
	}
	@Test
	public void updatePerson(){
		PersonDomainModel per2 = PersonDAL.getPerson(per1.getPersonID());
		per2.setFirstName("Billy Bob Joe");
		PersonDAL.updatePerson(per2);
		assertNotEquals(per2.getFirstName(), per1.getFirstName());
	}
	@Test
	public void deletePerson(){
		PersonDAL.deletePerson(per1.getPersonID());
		//creates a person and which shouldn't exsist
		PersonDomainModel per4 = PersonDAL.getPerson(per1.getPersonID());
		// must assert Null sense in this case per4 doesn't exsist
		assertNull(per4);
	
	}

}
