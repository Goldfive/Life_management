package management;


public abstract class Metric extends Management {

	int id;
	String type;
	int value;
	String time;
	String extra;
	
	boolean isChanged = false;
	
	abstract void updateData();
	abstract void menu();
	
	public Metric matchMetric(Field f, String v)
	{
		switch (f)
		{
			case TYPE:
				return (this.type == v ? this : null);
			case EXTRA:
				return (this.extra == v ? this : null);
			case TIME:
				return (this.time == v ? this : null);
		default:
			break;
		}
		return null;	
	}
	
	public Metric matchMetric(Field f, int v)
	{
		switch (f)
		{
			case ID:
				return (this.id == v ? this : null);
			case VALUE:
				return (this.value == v ? this : null);
		default:
			break;
		}
		return null;	
	}
	
	public Metric loadData(int id, String type, int value, String time, String extra)
	{
		this.id = id;
		this.type = type;
		this.value = value;
		this.time = time;
		this.extra = extra;
		
		return this;
	}
}

enum Field
{
	ID, TYPE, VALUE, TIME, EXTRA;
}
