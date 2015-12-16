package Word2Vec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Parsingdata {
	public static Double parsingTextData(String fileName,int lineNumber1, int lineNumber2) {


		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String sCurrentLine="";
			for(int i=0;i<lineNumber1;i++)
			{
				br.readLine();
			}
			String doc1Line= sCurrentLine=br.readLine();		
			for(int i=0;i<lineNumber2-lineNumber1-1;i++)
			{
				br.readLine();
			}
			String doc2Line= sCurrentLine=br.readLine();
			
			document doc1=  new document();
			document doc2 = new document();
			
			doc1Line = doc1Line.replaceAll("\\s+", "");
			doc1Line = doc1Line.replace("}", "");
			doc1Line = doc1Line.replace("{", "");
			doc1Line = doc1Line.replace(",", ":");
			String[] splitedLine1= doc1Line.split(":");
			doc1.setIndex(Integer.parseInt(splitedLine1[1]));
			
			int k=4;
			for(int i=0;i<2000;i++){
				doc1.pushValue(Double.parseDouble(splitedLine1[k]));
				k=k+2;
			}
			
			doc2Line = doc2Line.replaceAll("\\s+", "");
			doc2Line = doc2Line.replace("}", "");
			doc2Line = doc2Line.replace("{", "");
			doc2Line = doc2Line.replace(",", ":");
			String[] splitedLine2= doc2Line.split(":");
			doc2.setIndex(Integer.parseInt(splitedLine2[1]));

			k=4;
			for(int i=0;i<2000;i++){
				doc2.pushValue(Double.parseDouble(splitedLine2[k]));
				k=k+2;
			}
			
			
			return (kullback.getDistance(doc1, doc2));
		} catch (IOException e) {
			
			e.printStackTrace();
			return -1.0;
		}
	}
	public static void parseDocIndexData(String fileName, HashMap<String,Integer> docIndexList){
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.replaceAll("\\s+", "");
				String[] indexDocName= sCurrentLine.split(":");
				//System.out.println(indexDocName[1]+ ": " + indexDocName[3]);
				docIndexList.put(indexDocName[3], Integer.parseInt(indexDocName[1]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
