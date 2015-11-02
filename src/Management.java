import java.util.ArrayList;


public class Management {
	static ArrayList<User> userList;
	static ArrayList<Action> actionList;
	static ArrayList<Object> objectList;
	static ConnectToDB ctd; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ctd = new ConnectToDB();
		userList = ctd.getUserQuery();
		actionList = ctd.getActionList();
		objectList = ctd.getObjectList();
		update(userList.get(0), actionList.get(0), null,"new object by user1", null);
		//update(userList.get(1), actionList.get(1), objectList.get(2),null, null);
		//update(userList.get(0), actionList.get(2), objectList.get(5),null, null);
		//update(userList.get(0), actionList.get(2), objectList.get(3),"new object updated by John", null);
		//update(userList.get(2), actionList.get(2), objectList.get(4),"new object updated by Alice", null);
		//update(userList.get(0), actionList.get(3), objectList.get(2),null, null);
		//update(userList.get(0), actionList.get(3), objectList.get(0),null, null);
		//update(userList.get(2), actionList.get(3), objectList.get(2),null, null);
		//User user2 = new User(5,"James",2);
		//update(userList.get(1), actionList.get(4), null,null, user2);
		//update(userList.get(3), actionList.get(4), null,null, user2);
		//printUserList();
		//printActionList();
	}
	
	static void printUserList()
	{
		for(User user:userList)
		{
			System.out.println(user.toString());
		}
	}
	
	static void printActionList()
	{
		for(Action action:actionList)
		{
			System.out.println(action.toString());
		}
	}
	
	static void update(User user, Action action, Object object,String content, User user2)
	{
		if(valid(user,action,object))
		{
			System.out.println("valid operation");
			ctd.operateAction(user,action, object,content, user2);
		}
		else
			System.out.println(user.getName()+" do not have the permission to take "+action.getName()+
					" operation.");
	}
	
	static boolean valid(User user, Action action, Object object)
	{
		int roleId = user.getRoleId();
		int actionId = action.getId();
		int own = 0; 
		if(object!=null&&user.getId() == object.getOwnerId())
			own = 1;
		return ctd.validPermission(roleId, actionId, own);
	}
	
	

}
