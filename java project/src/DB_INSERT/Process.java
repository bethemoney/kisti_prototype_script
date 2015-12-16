package DB_INSERT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Process {
	public static void NN_Function(ArrayList<String> Id, ArrayList<String> Title,ArrayList<String> Abstract, Scanner scan ) {
		System.out.println("NN_Function");
		while (true) {
			try {
				if (scan.nextLine().contains("kisitID")) {
					String temp_str = "";
					String[] split_str = new String[10];
					String Id_str = "";
					temp_str = scan.nextLine();
					split_str = temp_str.split("/");
					if(split_str.length == 2) {
						Id_str += split_str[0];
						Id.add(Id_str);
					}
					else if (split_str.length == 3) {
					Id_str += split_str[0]; // JAKO or NART
					split_str = split_str[1].split(" ");
					Id_str += split_str[1];
					Id.add(Id_str);
					}
					else {
						Id.add("?");
					}
					// System.out.println(Id_str);
				}
				if (scan.nextLine().contains("Title")) {
					String temp_str = "";
					String[] split_str = new String[1000];
					String[] NN_str = new String[100];
					String Keyword_Title = "";
					temp_str = scan.nextLine(); // 타이틀 제목을 복사함
					split_str = temp_str.split(" ");
					for (int i = 0; i < split_str.length; i++) {
						if(split_str[i].contains("/NNP") || split_str[i].contains("/NNG")) {
							NN_str = split_str[i].split("/NN");
							if(NN_str[0] != "ㆍ")
								Keyword_Title += NN_str[0] + " ";
						}
					}
					//System.out.println(Keyword_Title);
					Title.add(Keyword_Title);
				}

				if (scan.nextLine().contains("Abstract")) {
					String temp_str = "";
					String[] split_str = new String[1000];
					String[] NN_str = new String[100];
					String Keyword_Abs = "";
					temp_str = scan.nextLine(); // 타이틀 제목을 복사함
					split_str = temp_str.split(" ");
					for (int i = 0; i < split_str.length; i++) {
						if(split_str[i].contains("/NNP") || split_str[i].contains("/NNG")) {
							NN_str = split_str[i].split("/NN");
							if(NN_str[0] != "ㆍ")
								Keyword_Abs += NN_str[0] + " ";
						}
					}
					Abstract.add(Keyword_Abs);
				}
			} catch (Exception e) {
				break;
			}
		} // while
		
	}
	
	public static void DB_INSERT(ArrayList<String> Id, ArrayList<String> Title, ArrayList<String> Abstract, String DB_IP, String DB_ID, String DB_PW, String Doc_Type, String DB_Name) {
		System.out.println("DB_INSERT");
		try {
			Connection con = null;

			
			con = DriverManager.getConnection("jdbc:mysql://"+DB_IP, DB_ID, DB_PW);
		//	con = DriverManager.getConnection("jdbc:mysql://165.194.35.222/KISTI", "bethemoney", "1234");
			
			//String doctype = "PAPER"; // 논문
			//String doctype = "PATENT"; // 특허
			//String doctype = "REPORT"; // 보고서

			
				String query = "Insert into kisti_"+DB_Name+" (id, pubyear, terms, doctype)" + " values (?, ?, ?, ?)";
				for(int i=0; i<Id.size(); i++) {
					String terms = Title.get(i)+Abstract.get(i);
					int pubyear  = 0;
					if(terms.split(" ").length > 15) {
						if(Doc_Type.equals("paper") || Doc_Type.equals("report") ) {
							pubyear = Integer.parseInt(Id.get(i).substring(4, 8)); // 논문, 보고서 년도
							//System.out.println(pubyear);
						}
						else {
							pubyear = Integer.parseInt(Id.get(i).substring(5,9)); // 특허 년도
							//System.out.println(pubyear);
						}
					
						PreparedStatement preparedStmt = con.prepareStatement(query);
						preparedStmt.setString(1, Id.get(i));
						preparedStmt.setInt(2, pubyear);
						preparedStmt.setString(3,terms);
						preparedStmt.setString(4, Doc_Type);
						preparedStmt.execute();
						}
					
					
				}
				
				

					


			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
