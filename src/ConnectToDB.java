
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.Random;
 public class ConnectToDB {
	 Connection conn=null;
	 ArrayList<User> getUserQuery()
	 {
		 ArrayList<User> userList = new ArrayList<User>();
		 getConn();
		 try {
			Statement statement = conn.createStatement();
			String sql = "select * from user";
			ResultSet rs = statement.executeQuery(sql);  
			
			             while(rs.next())
			             {
			            	 int userId = rs.getInt(1);
			            	 String userName = rs.getString(2);
			                 User user = new User(userId, userName,rs.getInt(3));
			                 userList.add(user);
			             }
			             closeAll(rs,statement,conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return userList;
	 }
	 
	 ArrayList<Action> getActionList()
	 {
		 ArrayList<Action> actionList = new ArrayList<Action>();
		 getConn();
		 try {
			Statement statement = conn.createStatement();
			String sql = "select * from permission";
			ResultSet rs = statement.executeQuery(sql);  
			             while(rs.next())
			             {
			            	 int actionId = rs.getInt(1);
			            	 String actionName = rs.getString(2);
			                 Action action = new Action(actionId, actionName);
			                 actionList.add(action);
			             }
			             closeAll(rs,statement,conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return actionList;
	 }
	 
	 ArrayList<Object> getObjectList()
	 {
		 ArrayList<Object> objectList = new ArrayList<Object>();
		 getConn();
		 try {
			Statement statement = conn.createStatement();
			String sql = "select * from object";
			ResultSet rs = statement.executeQuery(sql);  
			             while(rs.next())
			             {
			            	 int objectId = rs.getInt(1);
			            	 String objectName = rs.getString(2);
			            	 Object object = new Object(objectId, objectName,rs.getInt(3));
			                 objectList.add(object);
			             }
			             closeAll(rs,statement,conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return objectList;
	 }
 public Connection getConn()
 {
  
  try {
   Class.forName("com.mysql.jdbc.Driver");
  } catch (ClassNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  String url="jdbc:mysql://127.0.0.1:3306/rbac";
  try {
   conn=DriverManager.getConnection(url, "root", "QSCesz123");
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }  
  return conn;
 }
 public void closeAll(ResultSet rs,Statement stat,Connection conn)
 {
  if(rs!=null)
   try {
    rs.close();
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  if(stat!=null)
   try {
    stat.close();
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  if(conn!=null)
   try {
    conn.close();
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
 }

public boolean validPermission(int roleId, int actionId, int own) {
	// TODO Auto-generated method stub
	getConn();
	 try {
		Statement statement = conn.createStatement();
		String sql = "select  *from role_permission where role_id ="+roleId+
				" And Permission_Id="+actionId;
		ResultSet rs = statement.executeQuery(sql);  
		if(rs.getRow() == 1)
			return rs.getInt(3)==1;
		
		             while(rs.next())
		             {
		            	 if(rs.getInt(4)==own)
		            		 return rs.getInt(3)==1;
		             }
		             closeAll(rs,statement,conn);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

public void operateAction(User user, Action action, Object object,
		String content, User user2) {
	// TODO Auto-generated method stub
	getConn();
	 try {
		Statement statement = conn.createStatement();
		ResultSet rs = null;
		switch(action.getId())
		{
		//create a object owned by user
		case 1:
		{
			Random r = new Random();
			int newObject = r.nextInt()%20+5;
			String sql = "INSERT INTO Object VALUES ("+newObject+",'"+content+"',"+user.getId()+")";
			int i = statement.executeUpdate(sql);
			//System.out.println(rs.toString());
			System.out.println(i);
			break;
		}
		//query and print
		case 2:
		{
			System.out.println(object.toString());
			break;
		}
		//update, string content
		case 3:
		{
			String sql = "UPDATE Object SET object_content = '"+content+"' WHERE object_id ="+object.getId();
			statement.executeUpdate(sql);
			//System.out.println(rs.toString());
			break;
		}
		//delete
		case 4:
		{
			String sql = "DELETE FROM Object WHERE object_id ="+object.object_id;
			statement.executeUpdate(sql);
			break;
		}
		case 5:
		{
			String sql = "INSERT INTO User VALUES ("+user2.getId()+",'"+user2.getName()+"',"+user2.getRoleId()+")";
			statement.executeUpdate(sql);
			break;
		}
		}
		
		//closeAll(rs,statement,conn);
	 } catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
}
}
 