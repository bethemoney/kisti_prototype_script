package Word2Vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class KLDA {
	public static void main(String[] args) throws IOException {
		FileWriter fWriter = new FileWriter("D:\\cbow_data\\KLDA\\result.txt");
		String JAR = "D:\\cbow_data\\KLDA\\kullback.jar";
		File file = new File("D:\\cbow_data\\KLDA\\paper.txt");
		File doc_idx = new File("D:\\cbow_data\\KLDA\\docIndex.txt");
		File doc_idx2 = new File("D:\\cbow_data\\KLDA\\docIndex2.txt");
		String doc_idx_str = doc_idx2.getAbsolutePath();
	//	System.out.println(doc_idx_str);
		String  TOPIC = "D:\\cbow_data\\KLDA\\topics.txt";
		
		HashMap<String, ArrayList<String>> ID_Terms = new HashMap<String, ArrayList<String>>();
		HashMap<String, Integer> Doc_Index = new HashMap<String, Integer>();
		String first_doc_id = "JAKO200919061730120"; // 처음 doc 아이디
		ArrayList<String> Term = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		
		ArrayList<String> Doc_ID = new ArrayList<String>();
		String user_str = "";
		
		/////////////////////// 단어 넣기 ////////////////////////////////
		System.out.println("단어 입력 ㄱㄱ");
		String str = scan.nextLine();
		user_str = str;
		String [] tmp = str.split(" ");
		for(int i=0; i<tmp.length; i++) {
			Term.add(tmp[i]); // 의사 간호사 병원 등등 들어감
		}
//		System.out.println(Term.get(0));

/////////////////////// 문서 ID - TERM 넣기 /////////////////////// 
		BufferedReader br = new BufferedReader(new FileReader(file));
		while( (str = br.readLine()) != null ) {
			tmp = str.split("\t");
			String[] term_tmp = tmp[1].split(" ");
			ArrayList<String> terms = new ArrayList<String>();
			ArrayToList(terms, term_tmp); // term들을 list에 넣기
			ID_Terms.put(tmp[0], terms); // HashMap에 넣기
		}
	//	System.out.println(ID_Terms.size());
	//	System.out.println(ID_Terms.get("JAKO200903263797418").contains(Term.get(0)));
		
/////////////////////// 문서 Index 넣기 ///////////////////////
		br = new BufferedReader(new FileReader(doc_idx));
		while( (str = br.readLine()) != null) {
			tmp = str.split(":");
			Doc_Index.put(tmp[1], Integer.parseInt(tmp[0]));
		}
	//	System.out.println(Doc_Index.size());
		
/////////////////////// 입력한 단어 있는 문서 찾기 /////////////////////// 
		Iterator<String> it_ID_Terms = ID_Terms.keySet().iterator();
		
		while(it_ID_Terms.hasNext()) {
		//	System.out.println(Term.size());
			int flag = 0;
			String ID = it_ID_Terms.next();
			for(int j=0; j<Term.size(); j++) { // 입력한 TERM
				// System.out.println(Term.get(j));
				if(ID_Terms.get(ID).contains(Term.get(j))) { // 단어가 들어있다면
				//	System.out.println(ID);
					flag++; // flag 증가
				}
			}
			if(flag == Term.size()) {
				//System.out.println(ID);
				Doc_ID.add(ID); // 모두 들어있다면
			}
		}
/////////////////////// KLDA 구하기 ///////////////////////
		HashMap<String, Double> KLDA = new HashMap<String, Double>();
		String Key_doc_id = "";
		double KLDA_Value = 0;
		System.out.println(Doc_ID.size());
		for(int i=0; i<Doc_ID.size(); i++) {
			
			String min_doc_id = "";
			String max_doc_id = "";
			String comp_doc_id = Doc_ID.get(i);
			
			if(Doc_Index.get(first_doc_id) < Doc_Index.get(comp_doc_id)) {
				System.out.println(i);
				min_doc_id = first_doc_id;
				max_doc_id = comp_doc_id;
				
				KLDA_Value = Parsingdata.parsingTextData(TOPIC, Doc_Index.get(first_doc_id),Doc_Index.get(comp_doc_id));
				
				
				
				
				
				
				//Process p = Runtime.getRuntime().exec("java -jar "+JAR+" "+min_doc_id+" "+max_doc_id+" "+doc_idx_str + " " + TOPIC);
				//System.out.println(TOPIC);
				//br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				Key_doc_id = min_doc_id + " " + max_doc_id + " ";
				//KLDA_Value = Double.parseDouble(br.readLine());
				KLDA.put(Key_doc_id, KLDA_Value);
				//System.out.println(min_doc_id + " " + max_doc_id + " " + br.readLine());
			}
			else {
				System.out.println(i);
				min_doc_id = comp_doc_id;
				max_doc_id = first_doc_id;
				
				KLDA_Value = Parsingdata.parsingTextData(TOPIC,Doc_Index.get(comp_doc_id),  Doc_Index.get(first_doc_id));
				//Process p = Runtime.getRuntime().exec("java -jar "+JAR+" "+min_doc_id+" "+max_doc_id+" "+doc_idx_str + " " + TOPIC);
				//br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				Key_doc_id = min_doc_id + " " + max_doc_id + " ";
				//KLDA_Value = Double.parseDouble(br.readLine());
				KLDA.put(Key_doc_id, KLDA_Value);
			}
			
		}
		
		Iterator<String> it = sortByValueDown(KLDA).iterator();
		fWriter.write(user_str+" "+Doc_ID.size()+"\n");
		while(it.hasNext()) {
			String it_key = it.next();
			fWriter.write(it_key+KLDA.get(it_key)+"\n");
		}
		
		fWriter.close();
		
		
		
		

		
		
		
	} // main
	
    public static List sortByValueDown(final Map map){
        List<String> list = new ArrayList();
        list.addAll(map.keySet());
         
        Collections.sort(list,new Comparator(){
             
            public int compare(Object o1,Object o2){
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v1).compareTo(v2);
            }
             
        });
       Collections.reverse(list); // 주석시 오름차순
        return list;
    }
	
	
	public static void ArrayToList(ArrayList<String>list, String[] arr) {
		for(int i=0; i<arr.length; i++) {
			list.add(arr[i]);
		}
	}
} // class
