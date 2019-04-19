
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
		return Integer.parseInt(instruction.substring(5, 10)); 
	}
	public String getInstruction() {
		return instruction;
	}
	public String getOpCode() {
		return instruction.substring(0, 5);
	}
	public int getR1Number() {
		return Integer.parseInt(instruction.substring(15, 20)); 
	}
	public int getR2Number() {
		return Integer.parseInt(instruction.substring(10, 15)); 
	}
	public String getImmediate() {
		return instruction.substring(15);
	}
	public String getShift() {
		return instruction.substring(20);
	}
	public String getAddress() {
		return instruction.substring(5);
	}
	
	
 
 
 
 

}
