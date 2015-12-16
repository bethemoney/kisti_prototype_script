package Word2Vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Word2VEC {
	
	@SuppressWarnings("unused")
	private static final int MAX_SIZE = 50;
	private static JSONArray jsonArr = new JSONArray();
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		//FileWriter fWriter = new FileWriter("D:\\cbow_data\\최신data\\cosine_sim 결과\\result.txt");
		//while(true) {
			Word2VEC vec = new Word2VEC();
			//vec.ReadTXT("D:\\cbow_data\\최신data\\vectors2.bin");
			vec.ReadTXT(args[1]);
			ArrayList<String> list = new ArrayList<String>();
			//Scanner scan = new Scanner(System.in);
			//System.out.println("단어를 입력하세요 // exit 입력하면 꺼짐");
			//String temp = scan.nextLine();
			String temp = args[0];
			String[] str = temp.split(" ");
			//if(str[0].equals("exit")) break;
			//fWriter.write(temp+" 분석\n");
			
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
			Iterator<WordEntry> it = vec.distance(list).iterator();
			int rank = 1;
			while(it.hasNext()) {
				System.out.println(it.next());
				//fWriter.write(it.next()+"\n");
				String []line = (it.next()+"").split(" ");

				JSONObject jsonEle = new JSONObject();
				jsonEle.put("rank", new Integer(rank++));
				jsonEle.put("keyword", line[0]);
				jsonEle.put("score", line[1]);
				jsonArr.add(jsonEle);

			}
			//fWriter.write("\n");
			//System.out.println(vec.distance(list));	
		//}
		//fWriter.close();
			System.out.println(jsonArr);

	}

	private HashMap<String, double[]> wordMap = new HashMap<String, double[]>();

	private int words;
	private int size;
	private int topNSize = 100;

	@SuppressWarnings("resource")
	public void ReadTXT(String path) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
//		 double len = 0;
		double vector = 0;
		try {

			String str = br.readLine();
			String [] num = str.split(" ");

			words = Integer.parseInt(num[0]); // 14566

			size = Integer.parseInt(num[1]); // 200

			String word;
			double[] vectors = null;
			
			//System.out.println(words+" "+size);

			for (int i = 0; i < words; i++) {
				str = br.readLine();
				String [] temp = str.split(" ");
				word = temp[0];
				//word = readString(dis);
				//System.out.println(i+" "+word);
				//System.out.println(word);
				vectors = new double[size];
//				len = 0;
				for (int j = 1; j < size+1; j++) {
					
					vector = Double.parseDouble(temp[j]);
					//System.out.print(vector+" ");
	//				len += vector * vector;
					vectors[j-1] = (double) vector;
				}
	//			len = Math.sqrt(len);

//				for (int j = 0; j < size; j++) {
//					vectors[j] /= len;
	//			}

				wordMap.put(word, vectors);
				//dis.read();

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public double VectorSize(double [] vector) {
		double vector_size = 0;
		
		for(int i=0; i<vector.length; i++) {
			vector_size += Math.pow(vector[i], 2);
		}
		vector_size = Math.sqrt(vector_size);
		
		return vector_size;
	}

	public Set<WordEntry> distance(List<String> words) {
		//System.out.println("LIST");
		double[] center = null;
		for (String word : words) {
			center = Sum(center, wordMap.get(word)); // 들어온 단어 벡터들 다 더함
		}

		if (center == null) {
			return Collections.emptySet();
		}

		int resultSize = wordMap.size() < topNSize ? wordMap.size() : topNSize;
		TreeSet<WordEntry> result = new TreeSet<WordEntry>();
		
		for(int i=0; i<words.size(); i++) {
			//System.out.println(words.get(i));
			wordMap.remove(words.get(i));
		}
	//	System.out.println(wordMap.get("결정")[199]);
		// 의사 간호사 병원이 들어옴 
		double min = Double.MIN_VALUE;
		for (Map.Entry<String, double[]> entry : wordMap.entrySet()) { // 모든 entry... 즉, vector들임

			double[] vector = entry.getValue();
			double vector_size = 0;
			vector_size = VectorSize(vector); // 단어의 사이즈를 구함
			double center_size = 0;
			center_size = VectorSize(center);
			double dist = 0;
			for (int i = 0; i < vector.length; i++) {
				dist += center[i] * vector[i];
			}
			dist  = dist / (vector_size * center_size);
			

			if (dist > min) {
				result.add(new WordEntry(entry.getKey(), dist));
				if (resultSize < result.size()) {
					result.pollLast();
				}
				min = result.last().score;
			}
		}
		result.pollFirst();

		return result;
	}

	private double[] Sum(double[] center, double[] fs) {
		// TODO Auto-generated method stub

		if (center == null && fs == null) {
			return null;
		}

		if (fs == null) {
			return center;
		}

		if (center == null) {
			return fs;
		}

		for (int i = 0; i < fs.length; i++) {
			center[i] += fs[i];
		}

		return center;
	}

}


//public Set<WordEntry> distance(String queryWord) {
//	//System.out.println("string");
//	System.out.println(queryWord);
//	double[] center = wordMap.get(queryWord);
//	if (center == null) {
//		System.out.println("없음");
//		return Collections.emptySet();
//	}
//
//	int resultSize = wordMap.size() < topNSize ? wordMap.size() : topNSize;
//	TreeSet<WordEntry> result = new TreeSet<WordEntry>();
//
//	double min = Double.MIN_VALUE;
//	for (Map.Entry<String, double[]> entry : wordMap.entrySet()) {
//		double[] vector = entry.getValue();
//		double dist = 0;
//		for (int i = 0; i < vector.length; i++) {
//			dist += center[i] * vector[i];
//			//System.out.println(dist);
//		}
//
//		if (dist > min) {
//			result.add(new WordEntry(entry.getKey(), dist));
//			if (resultSize < result.size()) {
//				result.pollLast();
//			}
//			min = result.last().score;
//		}
//	}
//	result.pollFirst();
//
//	return result;
//}