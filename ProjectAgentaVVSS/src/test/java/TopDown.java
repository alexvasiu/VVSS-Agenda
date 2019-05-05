import model.base.Activity;
import model.base.Contact;
import model.repository.classes.RepositoryActivityFile;
import model.repository.classes.RepositoryContactFile;
import model.repository.interfaces.RepositoryActivity;
import model.repository.interfaces.RepositoryContact;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class TopDown {
    private RepositoryActivity repAct;
    private RepositoryContact repCon;

    @Before
    public void setup() throws Exception {
        repCon = new RepositoryContactFile();
        repAct = new RepositoryActivityFile(repCon);

        for (Activity a : repAct.getActivities())
            repAct.removeActivity(a);
    }

    @Test
    public void testF01() {
        String user = new RandomString(50).nextString();
        String host = new RandomString(46).nextString();

        try {
            Contact con = new Contact("name4", "address1", "+4071122334455",
                    user + "@" + host + ".com");
            fail();
        } catch (Exception e) {
            assertEquals(e.getCause().getMessage(), "Error message - Email length error");
        }
    }

    @Test
    public void testF02() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        for (Activity a : repAct.getActivities())
            repAct.removeActivity(a);

        try {
            Activity act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name1",
                    null,
                    "Lunch break");

            repAct.addActivity(act);
            act.setName("dddd");
            assertFalse(repAct.addActivity(act));
            for (Activity a : repAct.getActivities())
                repAct.removeActivity(a);
        } catch (ParseException ignore) {
            fail();
        }
    }

    @Test
    public void testF03() {
        for (Activity act : repAct.getActivities())
            repAct.removeActivity(act);

        Calendar c = Calendar.getInstance();
        c.set(2013, Calendar.MARCH, 20);
        try {
            List<Activity> result = repAct.activitiesOnDate("a", c.getTime());
            assertEquals(0, result.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void IntegrareA() {
        String user = new RandomString(50).nextString();
        String host = new RandomString(46).nextString();

        try {
            Contact con = new Contact("name4", "address1", "+4071122334455",
                    user + "@" + host + ".com");
            fail();
        } catch (Exception e) {
            assertEquals(e.getCause().getMessage(), "Error message - Email length error");
        }
    }

    @Test
    public void IntegrareAB() {
        boolean ok1 = false, ok2 = false;
        int noOfContacts = repCon.count();
        ArrayList<Contact> listOfContacts = new ArrayList<>();
        try {
            Contact con = new Contact("name33", "address1", "+4071122334455", "alex@user.com");
            repCon.addContact(con);
            listOfContacts.add(con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ok1 = noOfContacts + 1 == repCon.count();

        int noOfActivites = repAct.count();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        try {
            Activity act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name",
                    listOfContacts,
                    "Lunch break");

            repAct.addActivity(act);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ok2 = noOfActivites + 1 == repAct.count();
        assertTrue(ok1 && ok2);
    }

    @Test
    public void IntegrareABC() throws Exception {
        boolean ok1 = false, ok2 = false;
        int noOfContacts = repCon.count();
        ArrayList<Contact> listOfContacts = new ArrayList<>();
        try {
            Contact con = new Contact("name33", "address1", "+4071122334455", "alex@user.com");
            repCon.addContact(con);
            listOfContacts.add(con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ok1 = noOfContacts + 1 == repCon.count();

        int noOfActivites = repAct.count();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        try {
            Activity act = new Activity(1, df.parse("03/20/2013 12:00"), df.parse("03/20/2013 13:00"), "name",
                    listOfContacts,
                    "Lunch break");

            repAct.addActivity(act);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ok2 = noOfActivites + 1 == repAct.count();

        Calendar c = Calendar.getInstance();
        c.set(2013, Calendar.MARCH, 20);

        List<Activity> result = repAct.activitiesOnDate("name", c.getTime());
        assertTrue(result.size() == 1 && result.get(0).getId() == 1 && ok1 && ok2);
    }

}
