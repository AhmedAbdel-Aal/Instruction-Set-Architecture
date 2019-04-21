import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Memory {
	 ArrayList<Instruction> IMemory = new ArrayList<Instruction>();
	 ArrayList<String> DMemory = new ArrayList<String>();
	 private static Memory LogSingelton = null; 

	 public static Memory getInstance() 
	 { 
	     if (LogSingelton == null) 
	    	 LogSingelton = new Memory(); 

	     return LogSingelton; 
	 } 
//-----------------------------INSTRUCTION MEMORY------------------------------------------------------
	 public void addInstruction(Instruction i){
		 this.IMemory.add(i);
	 }
	 public Instruction getInstruction(int i){
		return this.IMemory.get(i);
	 }
//-----------------------------DATA MEMORY------------------------------------------------------
	 public String load(String address , int offset){
		 int x = Integer.parseInt(address,2);
		 return DMemory.get(((x*4)+offset)%128);
	 }
	 public void store(String data , int offset , String address){
		 int x = Integer.parseInt(address,2);
		 int z = ((x*4)+offset)%128;
		 DMemory.set(z,data);
	 }
	 
	 
}
