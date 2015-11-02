
public class Action {
	int action_id;
	String action_name;
	public int getId()
	{
		return action_id;
	}
	public String getName()
	{
		return action_name;
	}
	
	Action(int id, String name)
	{
		this.action_id = id;
		this.action_name = name;
	}
	
	public String toString()
	{
		return action_id+"\t"+action_name;
	}
}
