package StepAnalysis;
import java.util.ArrayList;


public class StepAnalysis {
	public static void main(String[] args) {

		String inputWord = args[0];
		String groupName  =args[1];
		String dataPath = args[2];
		String jarPath = args[3];
		String Paper = "paper";
		String Patent = "patent";
		String Report = "report";
		
		ArrayList<String> Represent_Paper = Process_Function.Represent_LIST(inputWord, groupName, Paper, dataPath, jarPath);
		ArrayList<String> Represent_Patent = Process_Function.Represent_LIST(inputWord, groupName, Patent, dataPath, jarPath);
		ArrayList<String> Represent_Report = Process_Function.Represent_LIST(inputWord, groupName, Report, dataPath, jarPath);
		
		Process_Function.Result(Represent_Paper, Represent_Patent, Represent_Report);
		
		
	}
	

}
