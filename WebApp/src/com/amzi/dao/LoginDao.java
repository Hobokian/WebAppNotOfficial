package com.amzi.dao;  
  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  

public class LoginDao {  
    public static boolean validate(String name, String pass) {          
        boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "mydb";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
            pst = conn  
                    .prepareStatement("select * from userinfos where budgeNumber=? and password=?");  
            pst.setString(1, name);  
            pst.setString(2, pass);  
  
            rs = pst.executeQuery();  
            status = rs.next();  
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return status;  
    }  
    
    public static String getProvince()
    {
    	String provinces = "<select></select>";  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "mydb";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
            pst = conn  
                    .prepareStatement("select * from mydb.province");
            rs = pst.executeQuery();  
            provinces=formatResult(rs, "selectProvince");  
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return provinces;
    }
    
    public static String getCity()
    {
    	String provinces = "<select></select>";  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "mydb";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
            pst = conn  
                    .prepareStatement("SELECT * FROM mydb.cityloopup");
            rs = pst.executeQuery();  
            provinces=formatResult(rs, "selectCity");  
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return provinces;
    }
    
    public static boolean submitRecord(String email, String phone, String plateNumber, String URLlink,
    		String street, String streetNumber, String province, String city, String postalCode, String description)
    {
        Connection conn = null;  
        PreparedStatement pstLoc = null;  
        PreparedStatement pst = null;
        PreparedStatement pstReport = null; 
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "mydb";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        int location;
        try {  
        	
        	Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
            pstLoc = conn  
                    .prepareStatement("SELECT id FROM location WHERE " +
                    		"street_intersection=\"" + street + "\" AND " +
                    		"locationscol=\"" + streetNumber + "\" AND " +
                    		"postal_code=\"" + postalCode + "\" AND " +
                    		"cityLoopup_id=\"" + city + "\" AND " +
                    		"province_id=\"" + province + "\"");
            ;
            rs = pstLoc.executeQuery();
            if(!rs.next())
            {
            	rs.close();
            	pst = conn  
                    .prepareStatement("INSERT INTO location (street_intersection, locationscol,"+
                    		" postal_code, cityLoopup_id, province_id) VALUES (" +
                    		"\"" +street+ "\"," +
                    		"\"" +streetNumber+ "\"," +
                    		"\"" +postalCode+ "\"," +
                    		"\"" +city+ "\"," +
                    		"\"" +province+ "\")");
            	;
            	if(pst.executeUpdate()==1)
            	{
            		rs.close();
            		rs = pstLoc.executeQuery();
            		if(!rs.next())
            		{
            			return false;
            		}
            		
            	}
            	else return false;
            }
            location = rs.getInt(1);
            rs.close();
            String user;
            if(email.compareTo("")==1) user=phone;
            else user=email;
            pstReport = conn.prepareStatement("INSERT INTO incidentreports (user, plateNumber,"+
                    		" url, description, incident_status_id, location_id) VALUES (" +
                    		"\"" +user+ "\"," +
                    		"\"" +plateNumber+ "\"," +
                    		"\"" +URLlink+ "\"," +
                    		"\"" +description+ "\"," +
                    		"\"" +"1"+ "\"," +
                    		"\"" +location+ "\")");
            if(pstReport.executeUpdate()==1)return true;
            else return false;
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return false;
    }
    
    public static boolean checkAccessCode(String code, String province, String city) {          
        boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "mydb";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
            pst = conn  
                    .prepareStatement("select * from accessCode where code=? AND cityLoopup_id=? AND province_id=?");  
            pst.setString(1, code);  
            pst.setString(2, city);  
            pst.setString(3, province);  
  
            rs = pst.executeQuery();  
            status = rs.next();  
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return status;  
    }
    
    public static boolean addUser(String budge, String email, String phone, String userPassword, String province, String city) {          
        boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "mydb";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
            pst = conn.prepareStatement("INSERT INTO userinfos (budgeNumber, password,"+
            		" email, phoneNumber, cityLoopup_id, province_id) VALUES (" +
            		"\"" +budge+ "\"," +
            		"\"" +userPassword+ "\"," +
            		"\"" +email+ "\"," +
            		"\"" +phone+ "\"," +
            		"\"" +province+ "\"," +
            		"\"" +city+ "\")");
  
            if(pst.executeUpdate()==1)status=true;
            else status=false;
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return status;  
    }
    
    private static String formatResult(ResultSet rset, String name) throws SQLException {
        StringBuffer sb = new StringBuffer();
        sb.append("<select name=\""+ name +"\"> <option value=\" \"> </option> ");
        if(!rset.next()) 
        	return sb.toString();
        do 
        {  
        	sb.append("<option value=\"" + rset.getString(1) + "\">" + rset.getString(2) + "</option>\n");
        } while (rset.next());
        sb.append("</select>"); 
        return sb.toString();
    }
    
    public static String getIncidents()
    {
    	String table = "<table></table>" ;
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "mydb";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
            pst = conn  
                    .prepareStatement("SELECT * FROM mydb.incidentreports");
            rs = pst.executeQuery();  
            table=formatResultIncident(rs);  
  
        } catch (Exception e) {  
            System.out.println(e);  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return table;
    }
    
    private static String formatResultIncident(ResultSet rset) throws SQLException {
        StringBuffer sb = new StringBuffer();
        String popUp;
        sb.append("<table frame=\"box\" border=2 >");
        if(!rset.next()) 
        {
        	sb.append("<tr><td>there is no reports</td></tr></table>");
        	return sb.toString();
        }
        sb.append("<tr><td>user</td><td>plateNumber</td><td>description</td><td>watch</td></tr>");
        do 
        {  
        	popUp ="<button onclick=\"popUpWindow('"+rset.getString(4)+"','"+rset.getString(3)+"','"+rset.getString(5)+"')\">watch</button>";
        	sb.append("<tr><td>"+rset.getString(2)+"</td><td>"+rset.getString(3)+"</td><td>"+rset.getString(5)+"</td><td>"+ popUp +"</td></tr>");
        } while (rset.next());
        sb.append("</table>"); 
        return sb.toString();
    }
}