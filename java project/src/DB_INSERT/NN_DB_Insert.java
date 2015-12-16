package DB_INSERT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class NN_DB_Insert {
	public static void main(String[] args) {

		File file = new File(args[0]); // 보건_논문, 보건_특허, 보건_보고서, 반도체_논문... 뭐 이런 txt가 들어감 ... 
		

	//	String DB_Name = args[1]; // 보건 ? 반도체? 나노 ?
		// healthcare, nano, semiconductor
	//	String Doc_Type = args[2]; // PAPER, PATENT, REPORT
		String DB_IP = args[1];
		String DB_ID = args[2];
		String DB_PW= args[3];
		
	//	File file = ""; // 보건_논문, 보건_특허, 보건_보고서, 반도체_논문... 뭐 이런 txt가 들어감 ... 
//		String DB_Name = "";; // 보건 ? 반도체? 나노 ?
//		String Doc_Type = ""; // PAPER, PATENT, REPORT
//		String DB_IP = "";
//		String DB_ID = "";;
//		String DB_PW= "";
			try {
				File [] listFiles= file.listFiles();
				for(File f : listFiles) {
					if(f.getName().contains(".pos")) {
					ArrayList<String> Id = new ArrayList<String>();
					ArrayList<String> Title = new ArrayList<String>();
					ArrayList<String> Abstract = new ArrayList<String>();
	
					
					Scanner scan = new Scanner(f);
					Process.NN_Function(Id, Title, Abstract, scan);
					String []DBNotation = f.getName().split("_");
					Process.DB_INSERT(Id, Title, Abstract, DB_IP, DB_ID, DB_PW, DBNotation[1].replace(".encoding.pos", ""),DBNotation[0]);
					}
				}
			
			} catch (Exception e) {
				//System.out.println(err_cnt);
				e.printStackTrace();
			}

		
	}
}
