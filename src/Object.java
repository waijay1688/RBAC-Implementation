
public class Object {
	int object_id;
	String content;
	int owner_id;
	public int getId()
	{
		return object_id;
	}
	public String getName()
	{
		return content;
	}
	public String toString()
	{
		return object_id+"\t"+content+"\t"+owner_id;
	}
	public int getOwnerId()
	{
		return this.owner_id;
	}
	Object(int id, String name, int id2)
	{
		this.object_id = id;
		this.content = name;
		this.owner_id = id2;
	}
}
