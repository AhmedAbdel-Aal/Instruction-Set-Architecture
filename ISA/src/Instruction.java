import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Instruction implements Serializable{
 String programeName;
 int instNumber;
 String ipath;
 String instruction;
 String opCode;
 int r1;
 int r2;
 int r3;
 String Immediate;
 String shift;
 String address;
	public Instruction(String instruction,String name,int x) throws IOException {
		this.instruction = instruction;
		this.instNumber=x;
		this.programeName=name;
		this.ipath="InstructionFiles\\"+programeName+"\\"+instNumber;
		FileWriter writer = new FileWriter(ipath,true);
	}
	public String getIpath() {
		return ipath;
	}
	public int getR3Number() {
		return Integer.parseInt(instruction.substring(5, 10),2); 
	}
	public String getInstruction() {
		return instruction;
	}
	public String getOpCode() {
		System.out.println(instruction);
		return instruction.substring(0, 5);
	}
	public int getR1Number() {
		return Integer.parseInt(instruction.substring(15, 20),2); 
	}
	public int getR2Number() {
		return Integer.parseInt(instruction.substring(10, 15),2); 
	}
	public int getImmediate() {
		return Integer.parseInt(instruction.substring(15),2);
	}
	public int getShift() {
		return Integer.parseInt(instruction.substring(20),2);
	}
	public int getAddress() {
		return Integer.parseInt(instruction.substring(5),2);
	}
	
	
 
 
 
 

}
