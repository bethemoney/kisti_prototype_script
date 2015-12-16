package DBtoVector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class DBtoVector {
	public static void main(String []args){
		
		Connection con = null;

		try {
			int rangeOfYear = Integer.parseInt(args[0]); //0
			String DBout_Path=args[1];
			String DB_IP=args[2];
			String DB_Name=args[3];
			String DB_ID=args[4];
			String DB_PW=args[5];

			
			Calendar oCalendar = Calendar.getInstance();
			int todayYear=oCalendar.get(Calendar.YEAR);
			todayYear=2014;
			
			int intervalYear = rangeOfYear/3;
			con = DriverManager.getConnection("jdbc:mysql://"+DB_IP+"/"+DB_Name,DB_ID, DB_PW);
			String[] dbTables = {"kisti_healthcare","kisti_nano","kisti_semiconductor"};

			String[] filePrefix={"health","nano","semiconductor"};
			String[] fileInfix= {"paper","patent","report"};
			
			
			for(int i=0;i<3;i++)	//area
			{
				for(int j=0;j<3;j++) //type
				{
					for(int k=0;k<3;k++)	//year
					{
						String filePostfix1 = (todayYear- rangeOfYear+(intervalYear*k)+1)+"";
						String filePostfix2 = (todayYear- rangeOfYear+(intervalYear*(k+1)))+"";
						String filePostfix=filePostfix1.substring(2)+filePostfix2.substring(2);
						String fileName = filePrefix[i]+"_"+fileInfix[j]+"_"+filePostfix;
						String outputPath = DBout_Path+"/"+fileName;
						
						BufferedWriter bWriter = new BufferedWriter(new FileWriter(outputPath));
						
						java.sql.Statement st = null;
						ResultSet rs = null;
						
						st = con.createStatement();
						String query = "SELECT * FROM "+ dbTables[i] + 
						" WHERE pubyear>="+(todayYear- rangeOfYear+(intervalYear*k)+1)+
						" AND pubyear<="+(todayYear- rangeOfYear+(intervalYear*(k+1))+
						" AND doctype='"+fileInfix[j]+"'");
						
						rs = st.executeQuery(query);
						
						
						while (rs.next()) {
							String str = rs.getNString(1) + "\t" + rs.getNString(3);
							bWriter.write(str+"\n");
						}
						bWriter.close();

						//executeShell(word2VecPath,outputPath+".txt",Vectors_Path+"/"+fileName+".bin");
					}
				}
				String filePostfix="all";
				String fileName = filePrefix[i]+"_"+filePostfix;
				String outputPath = DBout_Path+"/"+fileName;
				
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(outputPath));
				
				java.sql.Statement st = null;
				ResultSet rs = null;
				
				st = con.createStatement();
				String query = "SELECT * FROM "+ dbTables[i];
				
				rs = st.executeQuery(query);
				
				
				while (rs.next()) {
					String str = rs.getNString(1) + "\t" + rs.getNString(3);
					bWriter.write(str+"\n");
				}
				bWriter.close();

			}
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
