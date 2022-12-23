import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RequestDao {
public static int save(String oznaka,String name,int userID){
	int status=0;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into requests(oznaka,ime,korisnickiID) values(?,?,?)");
		ps.setString(1,oznaka);
		ps.setString(2,name);
		ps.setInt(3,userID);
		status=ps.executeUpdate();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static boolean CheckIssued(String bookcallno,int userid){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from issuebooks where oznaka=? and korisnickiID=?");
		ps.setString(1,bookcallno);
		ps.setInt(2,userid);
		ResultSet rs=ps.executeQuery();
		status=rs.next();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static boolean checkBook(String bookcallno){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from books where oznaka=?");
		ps.setString(1,bookcallno);
	    ResultSet rs=ps.executeQuery();
		status=rs.next();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static boolean checkReq(String bookcallno, int userID){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from requests where oznaka=? and korisnickiID=?");
		ps.setString(1,bookcallno);
		ps.setInt(2,userID);
	    ResultSet rs=ps.executeQuery();
		status=rs.next();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static int checkQuantity(String bookcallno){
	int status=0;
	int quantity=0;
	try{
		Connection con=DB.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select dostupno from books where oznaka=?");
		ps.setString(1,bookcallno);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			quantity=rs.getInt("dostupno");
			
		}
		
		if(quantity>0)
		return 1;
	}catch(Exception e){System.out.println(e);}
	return status;

}
}
