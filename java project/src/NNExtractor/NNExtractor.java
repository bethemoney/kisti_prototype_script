package NNExtractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NNExtractor {
	// Input, Output ���
	public static String inputPath="";
	public static String outputPath="";
	
	/*
	 * ���� Ŭ����
	 */
	public static void main(String[] args) {
		String usage = "NNExtarctor -i [inputPath] -o [outputPath]";
	
		
		
		if(args.length == 4){
			for(int i=0; i<args.length; i++) {
				if("-i".equals(args[i])){
					inputPath=args[i+1];
					i++;
				} else if ("-o".equals(args[i])) {
					outputPath=args[i+1];
					i++;
				}
			}
			
			File folder = new File(inputPath);
			listFilesForFolder(folder);
			
		} else {
			System.out.println(usage);
		}
		
		
	}
	
	/*
	 * ��η� �־��� ���丮 ���� ���� Ž��
	 */
	private static void listFilesForFolder(final File folder) {
		for(final File fileEntry : folder.listFiles()) {
			if(fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				if(fileEntry.isFile()){
					String temp = fileEntry.getName();
					String[] s = temp.split("\\.");
					
					String filename = s[0];
					
					NounExtract(filename, fileEntry);
				}
			}
		}
		
	}
	
	/*
	 * ���Ͽ��� /NN* �� ���� �ܾ���� �����Ѵ�.
	 * �� �߿��� ���ʿ��� 1����¥�� �ܾ���� �����Ѵ�.
	 */
	private static void NounExtract(String filename, File fileEntry) {
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileEntry));
			
			String mkFolder = outputPath+"//txtdata";
			File dir = new File(mkFolder);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			
			String str="";
			BufferedWriter out = new BufferedWriter(new FileWriter(mkFolder+"//"+filename+".txt"));
			while((str=in.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(str);
				String token="";
				
				while(st.hasMoreElements()) {
					token = st.nextToken();
					if(token.contains("NN")) {
						int end = token.lastIndexOf("/");
						String none = token.substring(0, end);
						if(none.length() != 1) {
							out.write(none+"\n");
						}
					}
				}
				
			}
			out.close();
			System.out.println(filename+".txt : done");
			in.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
