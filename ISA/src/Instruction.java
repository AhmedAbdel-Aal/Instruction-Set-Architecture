import java.io.FileWriter;
import java.io.IOException;

public class Instruction {
 String programeName;
 int instNumber;
 String ipath;
 String instruction;
 String opCode;
 int r1;
 int r2;
 int r3;
 int Immediate;
 int shift;
 String address;
	public Instruction(String instruction,String name,int x) throws IOException {
		this.instruction = instruction;
		this.instNumber=x;
		this.programeName=name;
		this.setAddress(this.getAddress());
		this.setImmediate(this.getImmediate());
		this.setOpCode(this.getOpCode());
		this.setR1(this.getR1Number());
		this.setR2(this.getR2Number());
		this.setR3(this.getR3Number());
		this.setShift(this.getShift());
		this.ipath="InstructionFiles/"+programeName+"/"+instNumber;
		FileWriter writer = new FileWriter(ipath,true);
	}
	@Override
	public String toString() {
		return "Instruction [programeName=" + programeName + ", instNumber=" + instNumber + ", ipath=" + ipath
				+ ", instruction=" + instruction + ", opCode=" + opCode + ", r1=" + r1 + ", r2=" + r2 + ", r3=" + r3
				+ ", Immediate=" + Immediate + ", shift=" + shift + ", address=" + address + "]";
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
	public String getAddress() {
		return instruction.substring(5);
	}
	public int getR1() {
		return r1;
	}
	public void setR1(int r1) {
		this.r1 = r1;
	}
	public int getR2() {
		return r2;
	}
	public void setR2(int r2) {
		this.r2 = r2;
	}
	public int getR3() {
		return r3;
	}
	public void setR3(int r3) {
		this.r3 = r3;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	public void setImmediate(int i) {
		Immediate = i;
	}
	public void setShift(int shift) {
		this.shift = shift;
	}
	public void setAddress(String i) {
		this.address = i;
	}
	
	
 
 
 
 

}
