import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidFormatException;

import model.base.Contact;
import model.repository.classes.RepositoryContactMock;
import model.repository.interfaces.RepositoryContact;


public class AddContactTest {

	private Contact con;
	private RepositoryContact rep;
	@Before
	public void setUp() {
		rep = new RepositoryContactMock();
	}
	
	@Test
	public void testCase_1()
	{
		try {
			con = new Contact("name2", "address1", "+4071122334455", "alex@user.com");
		} catch (InvalidFormatException e) {
			fail();
		}
		int n = rep.count();
		rep.addContact(con);
		for(Contact c : rep.getContacts())
			if (c.equals(con))
			{
				assertTrue(true);
				break;
			}
		assertEquals(n + 1, rep.count());
	}
	
	@Test
	public void testCase_2()
	{
		try{
			rep.addContact((Contact) new Object());
			fail();
		}
		catch(Exception e)
		{
			assertTrue(true);
		}	
	}
	
	@Test
	public void testCase_3()
	{
		for(Contact c : rep.getContacts())
			rep.removeContact(c);
		
		try {
			con = new Contact("nam2e", "address1", "+071122334455", "alex@alex.com");
			rep.addContact(con);
		} catch (InvalidFormatException e) {
			fail();
		}
		int n  = rep.count();
		if (n == 1) 
			if (con.equals(rep.getContacts().get(0))) assertTrue(true);
			else fail();
		else fail();
	}

	@Test
	public void testCase1_ECP() {
		try {
			con = new Contact("name33", "address1", "+4071122334455", "alex@user.com");
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testCase2_ECP() {
		try {
			con = new Contact("name44", "address1", "+4071122334455", "");
			fail();
		} catch (Exception e) {
			assertEquals(e.getCause().getMessage(), "Error message - Empty email");
		}
	}
	@Test
	public void testCase3_ECP() {
		try {
			con = new Contact("", "address1", "+4071122334455", "alex@user.com");
			fail();
		} catch (Exception e) {
			assertEquals(e.getCause().getMessage(), "Error message - Empty name");
		}
	}
	@Test
	public void testCase4_ECP() {
		try {
			con = new Contact("name44", "address1", "+4071122334455", "alex32132132131");
			fail();
		} catch (Exception e) {
			assertEquals(e.getCause().getMessage(), "Error message - Incorect email");
		}
	}

	@Test
	public void testCase1_BVA() {
		try {
			con = new Contact("1234", "address1", "+4071122334455", "alex@user.com");
			fail();
		} catch (Exception e) {
			assertEquals(e.getCause().getMessage(), "Error message - Name length error");
		}
	}
	@Test
	public void testCase2_BVA() {
		try {
			con = new Contact("12345", "address1", "+4071122334455", "alex@user.com");
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testCase3_BVA() {
		try {
			con = new Contact("123456", "address1", "+4071122334455", "alex@user.com");
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testCase4_BVA() {
		String generatedString = new RandomString(149).nextString();

		try {
			con = new Contact(generatedString, "address1", "+4071122334455", "alex@user.com");
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testCase5_BVA() {
		String generatedString = new RandomString(150).nextString();

		try {
			con = new Contact(generatedString, "address1", "+4071122334455", "alex@user.com");
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testCase6_BVA() {
		String generatedString = new RandomString(151).nextString();

		try {
			con = new Contact(generatedString, "address1", "+4071122334455", "alex@user.com");
			fail();
		} catch (Exception e) {
			assertEquals(e.getCause().getMessage(), "Error message - Name length error");
		}
	}
	@Test
	public void testCase7_BVA() {
		try {
			con = new Contact("name4", "address1", "+4071122334455", "a@abb.com");
			fail();
		} catch (Exception e) {
			assertEquals(e.getCause().getMessage(), "Error message - Email length error");
		}
	}
	@Test
	public void testCase8_BVA() {
		try {
			con = new Contact("name4", "address1", "+4071122334455", "a@abbc.com");
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testCase9_BVA() {
		try {
			con = new Contact("name4", "address1", "+4071122334455", "a@abbc1.com");
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testCase10_BVA() {
		String user = new RandomString(50).nextString();
		String host = new RandomString(44).nextString();

		try {
			con = new Contact("name4", "address1", "+4071122334455",
					user + "@" + host + ".com");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testCase11_BVA() {
		String user = new RandomString(50).nextString();
		String host = new RandomString(45).nextString();

		try {
			con = new Contact("name4", "address1", "+4071122334455",
					user + "@" + host + ".com");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testCase12_BVA() {
		String user = new RandomString(50).nextString();
		String host = new RandomString(46).nextString();

		try {
			con = new Contact("name4", "address1", "+4071122334455",
					user + "@" + host + ".com");
			fail();
		} catch (Exception e) {
			assertEquals(e.getCause().getMessage(), "Error message - Email length error");
		}
	}
}
