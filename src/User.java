
public class User {
	int user_id;
	String user_name;
	int role_id;
	public int getId()
	{
		return user_id;
	}
	
	public String getName()
	{
		return user_name;
	}
	
	User(int id, String name, int ri)
	{
		this.user_id = id;
		this.user_name = name;
		this.role_id = ri;
	}
	public int getRoleId()
	{
		return role_id;
	}
	public String toString()
	{
		return user_id+"\t"+user_name;
	}
}
