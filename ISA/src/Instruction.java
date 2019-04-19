
public class Instruction {
 String instruction;
 String opCode;
 int r1;
 int r2;
 int r3;
 String Immediate;
 String shift;
 String address;
	public Instruction(String instruction) {
		this.instruction = instruction;
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
	public int getAddress() {
		return Integer.parseInt(instruction.substring(5),2);
	}
	
	
 
 
 
 

}
