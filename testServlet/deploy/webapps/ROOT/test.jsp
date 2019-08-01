<%@ page contentType="text/html; charset=gb2312" %> 
<%@ page language="java" %> 
<%@ page import="com.mysql.jdbc.Driver" %> 
<%@ page import="java.sql.*" %> 
<% 
//加载驱动程序 
String driverName="com.mysql.jdbc.Driver"; 
//数据库信息
String userName="root"; 
//密码 
String userPasswd="111111"; 
//数据库名 
String dbName="credential"; 
//表名 
String tableName="credential_info"; 
//将数据库信息字符串连接成为一个完整的url（也可以直接写成url，分开写是明了可维护性强） 
 
String url="jdbc:mysql://192.168.99.101/"+dbName+"?user="+userName+"&password="+userPasswd; 
Class.forName("com.mysql.jdbc.Driver").newInstance(); 
Connection conn=DriverManager.getConnection(url); 
Statement stmt = conn.createStatement(); 
String sql="SELECT * FROM "+tableName; 
ResultSet rs = stmt.executeQuery(sql); 
out.print("id"); 
out.print("|"); 
out.print("name"); 
out.print("|"); 
out.print("phone"); 
out.print("<br>"); 
while(rs.next()) { 
out.print(rs.getString(1)+" "); 
out.print("|"); 
out.print(rs.getString(2)+" "); 
out.print("|"); 
out.print(rs.getString(3)); 
out.print("<br>"); 
} 
out.print("<br>"); 
out.print("ok, Database Query Successd!"); 
rs.close(); 
stmt.close(); 
conn.close(); 
%>
