import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.base.Activity;
import model.base.Contact;
import model.repository.classes.RepositoryActivityFile;
import model.repository.classes.RepositoryContactFile;
import model.repository.interfaces.RepositoryActivity;
import model.repository.interfaces.RepositoryContact;

import org.junit.Before;
import org.junit.Test;

public class AfisActivityTest {

	private RepositoryActivity rep;

	@Before
	public void setUp() throws Exception {
		RepositoryContact repcon = new RepositoryContactFile();
		rep = new RepositoryActivityFile(repcon);
	}

	@Test
	public void testCase1() {
		for (Activity act : rep.getActivities())
			rep.removeActivity(act);

		Calendar c = Calendar.getInstance();
		c.set(2013, Calendar.MARCH, 20, 12, 0);
		Date start = c.getTime();

		c.set(2013, Calendar.MARCH, 20, 12, 30);
		Date end = c.getTime();

		Activity act = new Activity(1, start, end, "name1",
                new LinkedList<Contact>(), "description2");

		rep.addActivity(act);

		c.set(2013, Calendar.MARCH, 20);

		List<Activity> result = null;
		try {
			result = rep.activitiesOnDate("name1", c.getTime());
		} catch (Exception e) {
			fail();
		}
		assertEquals(1, result.size());
	}

	@Test
	public void testCase2() {
		for (Activity act : rep.getActivities())
			rep.removeActivity(act);

		Calendar c = Calendar.getInstance();
		c.set(2013, Calendar.MARCH, 20, 12, 00);
		Date start = c.getTime();

		c.set(2013, Calendar.MARCH, 20, 12, 30);
		Date end = c.getTime();

		Activity act = new Activity(1, start, end, "name1",
                new LinkedList<Contact>(), "description2");

		rep.addActivity(act);

		c.set(2013, Calendar.MARCH, 20);
		try {
			rep.activitiesOnDate(((Object) 1).toString(), c.getTime());
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCase3() {
		for (Activity act : rep.getActivities())
			rep.removeActivity(act);

		try {
			rep.activitiesOnDate("name1", (Date)(Object)"ASD");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCase4() {
		for (Activity act : rep.getActivities())
			rep.removeActivity(act);

		try {
			rep.addActivity((Activity)(Object)1);
			
			Calendar c = Calendar.getInstance();
			c.set(2013, Calendar.MARCH, 20);
			rep.activitiesOnDate("name1", c.getTime());
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCase5() {
		for (Activity act : rep.getActivities())
			rep.removeActivity(act);
	
		Calendar c = Calendar.getInstance();
		c.set(2013, Calendar.MARCH, 20);
		List<Activity> result = null;
		try {
			result = rep.activitiesOnDate("name1", c.getTime());
		} catch (Exception e) {
			fail();
		}

		assertEquals(0, result.size());
	}


	@Test
	public void testUnitValid() {
		// Black-box valid -> nume un caracter

		for (Activity act : rep.getActivities())
			rep.removeActivity(act);

		Calendar c = Calendar.getInstance();
		c.set(2013, Calendar.MARCH, 20);
		try {
			List<Activity> result = rep.activitiesOnDate("a", c.getTime());
			assertEquals(0, result.size());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testUnitInvalid() {
		// Black-box invalid -> nume empty
		for (Activity act : rep.getActivities())
			rep.removeActivity(act);

		Calendar c = Calendar.getInstance();
		c.set(2013, Calendar.MARCH, 20);
		try {
			List<Activity> result = rep.activitiesOnDate("", c.getTime());
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nume gol");
		}
	}
}
