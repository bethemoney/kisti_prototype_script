package Word2Vec;

import java.util.ArrayList;
import java.util.List;

public class document {
	private int index;
	private List<Double> probArray = new ArrayList<Double>();
	
	public int size(){
		return this.probArray.size();
		
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void pushValue(Double value){
		this.probArray.add(value);
	}
	public Double getValue(int index){
		return this.probArray.get(index);
	}
	public void print() {
		// TODO Auto-generated method stub
		for (Double double1 : probArray) {
			System.out.print(double1+" ");
		}
	}
	
}
