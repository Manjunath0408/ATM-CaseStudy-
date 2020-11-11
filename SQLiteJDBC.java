import java.sql.*;

class SQLiteJDBC {
   public static Connection c = null;
   public static void getConn() {
	  try {
	     Class.forName("org.sqlite.JDBC");
	     c = DriverManager.getConnection("jdbc:sqlite:test.db");
	  } catch ( Exception e ) {
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	     System.exit(0);
	  }
	  System.out.println("Opened database successfully");
   }
   public static void createTable() {
	   Statement stmt = null;
	   try {
		   stmt = c.createStatement();
		   String sql = "CREATE TABLE ACCOUNTS "+
				   		  "(ACCOUNTNUMBER INT NOT NULL,"+
				   		  " PASSWORD INT NOT NULL,"+
				   		  " NAME String NOT NULL,"+
				   		  " BANKNAME String NOT NULL,"+
				   		  " BALANCE INT,"+
				   		  " TRANSACTIONS String,"+
				   		  " PRIMARY KEY(ACCOUNTNUMBER))";
		   stmt.executeUpdate(sql);
	   }
	   catch(Exception e) {
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
	   }
   }
   public static void insertIntoTable(int accountNumber,int passWord,String bankName,String name) {
	   Statement stmt = null;
	   try {
		 stmt = c.createStatement();
		 String sql = "INSERT INTO ACCOUNTS (ACCOUNTNUMBER,PASSWORD,NAME,BANKNAME,BALANCE) "+
				 	  "VALUES ("+accountNumber+", "+passWord+", '"+name+"', '"+bankName+"', "+0+" );";
		 System.out.println(sql);
		 stmt.execute(sql);
	   }
	   catch(Exception e) {
		 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	     System.exit(0);  
	   }
   }
   public static void printTable() {
	   Statement stmt = null;
	   try {
		   stmt = c.createStatement();
		   String sql = "SELECT * FROM ACCOUNTS;";
		   ResultSet rs = stmt.executeQuery(sql);
		   while(rs.next()) {
			   int accountNumber = rs.getInt("ACCOUNTNUMBER");
			   System.out.println(accountNumber);
		   }
	   }
	   catch(Exception e) {
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0); 
	   }
   }
   public static void updateTransactions(int accountNumber,String T) {
	   Statement stmt = null;
	   try {
		  stmt = c.createStatement();
		  String sql = "SELECT TRANSACTIONS FROM ACCOUNTS WHERE ACCOUNTNUMBER="+accountNumber+";";
          ResultSet nrs = stmt.executeQuery(sql); 
          String trans = nrs.getString("TRANSACTIONS");
          if(trans.equals(null))trans="";
          trans += T+"|";
          System.out.println(trans);
		  Statement stmt2 = c.createStatement();
		  sql = "UPDATE ACCOUNTS SET TRANSACTIONS ='"+trans+"' WHERE ACCOUNTNUMBER="+accountNumber+";";
		  stmt2.execute(sql);
	   }
	   catch(Exception e) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);  
	   }
   }
   public static void clearTable(String name) {
	   Statement stmt = null;
	   try {
		  stmt = c.createStatement();
		  String sql = "DELETE FROM ACCOUNTS WHERE NAME='"+name+"';";
		  System.out.println(sql);
		  stmt.execute(sql);
	   }
	   catch(Exception e) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);  
	   }
   }
}


//SQLiteJDBC.getConn();
////SQLiteJDBC.createTable();
//SQLiteJDBC.printTable();
////SQLiteJDBC.clearTable("DFS");
//SQLiteJDBC.updateTransactions(11111, "ADDEDCASH");
