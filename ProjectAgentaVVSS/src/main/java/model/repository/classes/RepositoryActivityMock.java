package model.repository.classes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.base.Activity;
import model.repository.interfaces.RepositoryActivity;

public class RepositoryActivityMock implements RepositoryActivity {

	private List<Activity> activities;
	
	public RepositoryActivityMock()
	{
		activities = new LinkedList<Activity>();
//		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//		try {
//			Activity act = new Activity(df.parse("03/20/2013 12:00"), 
//					df.parse("03/20/2013 14:00"),
//					null,
//					"Meal break",
//					"Memo");
//			activities.add(act);
//			act = new Activity(df.parse("03/21/2013 12:00"), 
//					df.parse("03/21/2013 14:00"),
//					null,
//					"Meal break",
//					"Memo");
//			activities.add(act);
//			act = new Activity(df.parse("03/22/2013 12:00"), 
//					df.parse("03/22/2013 14:00"),
//					null,
//					"Meal break",
//					"Memo");
//			activities.add(act);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
	}
	
	@Override
	public List<Activity> getActivities() {
		return new LinkedList<Activity>(activities);
	}

	@Override
	public boolean addActivity(Activity activity) {
		int  i = 0;
		boolean conflicts = false;

		while( i < activities.size() )
		{
			if ( activities.get(i).getStart().compareTo(activity.getDuration()) < 0 &&
					activity.getStart().compareTo(activities.get(i).getDuration()) < 0 )
				conflicts = true;
			else
			if (activities.get(i).getName().contains(activity.getName()))
				conflicts = true;
			i++;
		}
		if ( !conflicts )
		{
			activities.add(activity);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeActivity(Activity activity) {
		int index = activities.indexOf(activity);
		if (index<0) return false;
		activities.remove(index);
		return true;
	}

	@Override
	public boolean saveActivities() {
		return true;
	}

	@Override
	public int count() {
		return activities.size();
	}

	@Override
	public List<Activity> activitiesByName(String name) {
		List<Activity> result = new LinkedList<>();
		for (Activity a : activities)
			if (a.getName().equals(name)) result.add(a);
		return result;
	}

	@Override
	public List<Activity> activitiesOnDate(String name, Date d) throws Exception {
		name = name.trim();
		if (name.length() == 0)
			throw new Exception("Nume gol");
		List<Activity> result = new LinkedList<>();
		for (Activity a : activities)
			if (a.getName().equals(name))
				if (a.getStart().compareTo(d) <= 0 && d.compareTo(a.getDuration()) <= 0 ) result.add(a);
		return result;
	}
}
