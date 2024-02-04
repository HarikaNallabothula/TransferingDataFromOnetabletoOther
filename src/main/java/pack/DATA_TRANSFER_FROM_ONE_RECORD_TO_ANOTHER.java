package pack;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/INSERT")
public class DATA_TRANSFER_FROM_ONE_RECORD_TO_ANOTHER extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    Connection con=null;
    con=HelperClass.putConnection();
	/*
	  try{ PreparedStatement pst = con.
	  prepareStatement("insert into equipment(equip_id,type,color,location,install_date) select*from playground where equip_id=(?)" ); pst.setInt(1, Integer.valueOf(request.getParameter("equip_id"))); int
	  count=pst.executeUpdate(); if(count==0)
	 {out.println("DATA IS DUPLICATE.....");}
	 else{out.println("DATA IS INSERTED INTO EQUIPMENT TABLE.....");}
	 
	  }catch (SQLException e) { e.printStackTrace();
	  out.println(" DATA IS DUPLICATE....."); }
	 */
    try{Integer equip_id=Integer.valueOf(request.getParameter("equip_id"));
			PreparedStatement	pstmt = con.prepareStatement("select * from equipment where equip_id="+equip_id+";");
	           ResultSet reslutset = pstmt.executeQuery(); 
  if(reslutset.next())     {
    			  out.println("DATA IS DUPLICATE..............");
    			           }
    else {
    	      PreparedStatement pst= con.prepareStatement("insert into equipment(equip_id,type,color,location,install_date) select*from playground where equip_id=(?)" ); 
    		  pst.setInt(1, equip_id);
    	       pst.executeUpdate(); 
    	       out.println("PLAYGROUND DATA ENTERED INTO EQUIPMENT.............");
         }
		}
		catch (SQLException e) {
			e.printStackTrace();
		     } 
		   }
         }