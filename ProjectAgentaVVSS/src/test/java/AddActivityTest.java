import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.base.Activity;
import model.repository.classes.RepositoryActivityMock;
import model.repository.interfaces.RepositoryActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddActivityTest {
	private Activity act;
	private RepositoryActivity rep;
	
	@Before
	public void setUp() throws Exception {
		rep = new RepositoryActivityMock();
	}
	
	@Test
	public void testCase1()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
                    null,
					"Lunch break");
			rep.addActivity(act);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertTrue(1 == rep.count());
	}
	
	@Test
	public void testCase2()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
			
			act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
                    null,
					"Lunch break");
			rep.addActivity(act);
			
			act = new Activity(1, df.parse("03/21/2013 12:00"), df.parse("03/21/2013 13:00"), "name1",
                    null,
					"Lunch break");
			rep.addActivity(act);
		}
		catch(Exception e){}	
		int c = rep.count();
		assertEquals(1, c);
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);
	}
	
	@Test
	public void testCase3()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
			
			act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
                    null,
					"Lunch break");
			rep.addActivity(act);
			
			act = new Activity(1, df.parse("03/20/2013 12:30"), df.parse("03/20/2013 13:30"), "name1",
                    null,
					"Lunch break");
			assertFalse(rep.addActivity(act));
		}
		catch(Exception e){}	
		assertTrue( 1 == rep.count());
		rep.saveActivities();
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);
	}
	
	@Test
	public void testCase4()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
			
			act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
                    null,
					"Lunch break");
			rep.addActivity(act);
			
			act = new Activity(1, df.parse("03/20/2013 13:30"), df.parse("03/20/2013 14:00"), "name1",
                    null,
					"Curs");
			rep.addActivity(act);
			
			act = new Activity(1, df.parse("03/20/2013 13:30"), df.parse("03/20/2013 14:30"), "name1",
                    null,
					"Lunch break");
			assertFalse(rep.addActivity(act));			
		}
		catch(Exception e){}
		assertEquals(1, rep.count());
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);
	}
	
	@Test
	public void testCase5()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
			
			act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
                    null,
					"Lunch break");
			rep.addActivity(act);
			
			assertFalse(rep.addActivity(act));			
		}
		catch(Exception e){}	
		assertTrue( 1 == rep.count());
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);
	}

	@Test
	public void testWBT1() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);

		try {
			act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
					null,
					"Lunch break");

			assertTrue(rep.addActivity(act));
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
		} catch (ParseException ignore) {
			fail();
		}
	}

	@Test
	public void testWBT2() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);

		try {
			act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
					null,
					"Lunch break");

			rep.addActivity(act);
			act.setName("dddd");
			assertFalse(rep.addActivity(act));
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
		} catch (ParseException ignore) {
			fail();
		}
	}
	@Test
	public void testWBT3() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);

		try {
			act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
					null,
					"Lunch break");

			rep.addActivity(act);

			act.setStart(df.parse("03/20/2019 12:00"));
			act.setDuration(df.parse("03/20/2019 13:00"));
			act.setName("name");
			assertFalse(rep.addActivity(act));
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
		} catch (ParseException ignore) {
			fail();
		}
	}
}
