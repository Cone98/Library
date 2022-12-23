import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchDao {

	public static int search(String bookname){
		int status=0;
		int quantity=0,issued=0;
		try{
			Connection con=DB.getConnection();
			
			PreparedStatement ps=con.prepareStatement("select dostupno,izdato from books where ime=?");
			ps.setString(1,bookname);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				quantity=rs.getInt("dostupno");
				issued=rs.getInt("izdato");
			}
			
			status = quantity;
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
}
