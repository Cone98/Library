import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChangePasswordDao {
public static int save(String name,String password,String newPassword){
	int status=0;
	try{
		Connection con=DB.getConnection();
		PreparedStatement ps2=con.prepareStatement("update users set lozinka=? where ime=? and lozinka =?");
		ps2.setString(1,newPassword);
		ps2.setString(2,name);
		ps2.setString(3,password);
		
		status=ps2.executeUpdate();
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
	public static boolean validate(String name,String password){
		boolean status=false;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from users where ime=? and lozinka=?");
			ps.setString(1,name);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}


}
