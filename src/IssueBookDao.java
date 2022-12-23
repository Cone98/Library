import java.sql.*;

import javax.swing.JOptionPane;
public class IssueBookDao {
	
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
public static boolean CheckReq(String bookcallno,int userid){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from requests where oznaka=? and korisnickiID=?");
		ps.setString(1,bookcallno);
		ps.setInt(2,userid);
		ResultSet rs=ps.executeQuery();
		status=rs.next();
		
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
public static boolean deleteReq(String bookcallno,int userid){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("delete from requests where oznaka=? and korisnickiID=?");
		ps.setString(1,bookcallno);
		ps.setInt(2,userid);
	    ps.executeUpdate();
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static boolean checkUser(int id){
	boolean status=false;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from users where id=?");
		ps.setInt(1,id);
	    ResultSet rs=ps.executeQuery();
		status=rs.next();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static String[] getInfo(int id){
	
	String[] str = new String[2];
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from users where id=?");
		ps.setInt(1,id);
	    ResultSet rs=ps.executeQuery();
	    rs.next();
	    str[0]=rs.getString("ime");
	    str[1]=rs.getString("kontakt");
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	
	
	return str;
}

public static int save(String bookcallno,int userId,String userName,String userContact){
	int status=0;
	try{
		Connection con=DB.getConnection();
		
		status=updatebook(bookcallno);//updating quantity and issue
		
		if(status>0){
		PreparedStatement ps=con.prepareStatement("insert into issuebooks(oznaka,korisnickiID,korisnickoIme,kontakt) values(?,?,?,?)");
		ps.setString(1,bookcallno);
		ps.setInt(2,userId);
		ps.setString(3,userName);
		ps.setString(4,userContact);
		status=ps.executeUpdate();
		}
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static int updatebook(String bookcallno){
	int status=0;
	int quantity=0,issued=0;
	try{
		Connection con=DB.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select dostupno,izdato from books where oznaka=?");
		ps.setString(1,bookcallno);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			quantity=rs.getInt("dostupno");
			issued=rs.getInt("izdato");
		}
		
		if(quantity>0){
		PreparedStatement ps2=con.prepareStatement("update books set dostupno=?,izdato=? where oznaka=?");
		ps2.setInt(1,quantity-1);
		ps2.setInt(2,issued+1);
		ps2.setString(3,bookcallno);
		
		status=ps2.executeUpdate();
		}
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
}
