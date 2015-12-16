package NewsFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

public class NewsDataFomatter {
	public static void main(String []args)
	{
		String cvbResultPath = args[0];
		String RefinedPath = args[1];
		File dirFile=new File(cvbResultPath);
		File []fileList=dirFile.listFiles();
		
		for(File tmpFile: fileList)
		{
			String tempFileName=tmpFile.getAbsolutePath();
			try {
				
				BufferedWriter bWriter= new BufferedWriter(new FileWriter(RefinedPath+"/"+tmpFile.getName()+".refined"));
				
				BufferedReader bReader= new BufferedReader(new FileReader(tmpFile));
				String line="";
				ArrayList<ArrayList<String>> retArr= new ArrayList<ArrayList<String>>();
				while((line = bReader.readLine())!=null)
				{
					line = line.replace("{", "");
					line = line.replace("}", "");
					String[] splitedLine = line.split(",");
					ArrayList<String> newList = new ArrayList<String>();
					for(int i=0;i<splitedLine.length;i++)
					{
						String keyword = splitedLine[i].split(":")[0];
						newList.add(keyword);
					}
					retArr.add(newList);
					
				}
				bWriter.write("[");
				for(int i=0;i<retArr.size();i++)
				{
					if(i!=0)
					{
						bWriter.write(",\n");
					}
					JSONArray jsonRowArr= new JSONArray();
					for(int j=0;j<5;j++)
					{
						jsonRowArr.add(retArr.get(i).get(j));
						
					}
					bWriter.write(jsonRowArr.toJSONString());
				}
				bWriter.write("]");
				
				
				bWriter.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
