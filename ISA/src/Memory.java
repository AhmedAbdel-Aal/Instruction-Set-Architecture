import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Memory {
	 ArrayList<Instruction> IMemory = new ArrayList<Instruction>();
	 String [] DMemory = new String[128];
	 private static Memory LogSingelton = null; 

	 public static Memory getInstance() 
	 { 
	     if (LogSingelton == null) 
	    	 LogSingelton = new Memory();

	     return LogSingelton; 
	 } 
//-----------------------------INSTRUCTION MEMORY------------------------------------------------------
	 public Memory(){
		 DMemory[4]="00010";
		 DMemory[5]="00011";
		 DMemory[6]="1000000";

		}
	 public void addInstruction(Instruction i){
		 DMemory[4]="00010";
		 DMemory[5]="00011";
		 DMemory[6]="1000000";
		 this.IMemory.add(i);
	 }
	 public Instruction getInstruction(int i){
		return this.IMemory.get(i);
	 }
//-----------------------------DATA MEMORY------------------------------------------------------
	 public String load(int address , int offset){
		 return DMemory[((address*4)+offset)%128];
	 }
	 public void store(String data , int offset , int address){
		 int z = ((address*4)+offset)%128;
		 System.out.println("from memory = "+z+" "+data);
		 DMemory[z]=data;
	 }
	 
	 
}
