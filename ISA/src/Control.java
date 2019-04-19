
public class Control {
 String opCode ;
 private static Control LogSingelton = null;
 boolean regDst=false;
 boolean Branch=false;
 boolean MemRead=false;
 boolean MemWrite=false;
 boolean MemtoReg=false;
 boolean ALUSrc=false;
 boolean regWrite=false;


 public static Control getInstance() 
 { 
     if (LogSingelton == null) 
    	 LogSingelton = new Control(); 

     return LogSingelton; 
 } 
//-----------------------------------------------------------
 
 public void setOpCode(String s){
	 this.opCode=s;
 }
 public String getInstructionName(String opCode){
	 int x = Integer.parseInt(opCode,2);
	 switch(x){
	 case 0: return "ADD";
	 case 1: return "SUB";
	 case 2: return "AND";
	 case 3: return "OR";
	 case 4: return "XOR";
	 case 5: return "MOD";
	 case 6: return "IDV";
	 case 7: return "MUL";
	 case 8: return "MULA";
	 case 9: return "ADDC";
	 case 10: return "SUBRV";
	 case 11: return "GCD";
	 case 12: return "LCM";
	 case 13: return "POW";
	 case 14: return "SLT";
	 case 15: return "ADDI";
	 case 16: return "SLTI";
	 case 17: return "BEQ";
	 case 18: return "SWAP";
	 case 19: return "NZERO";
	 case 20: return "NONE";
	 case 21: return "LD";
	 case 22: return "SW";
	 case 23: return "MOV";
	 case 24: return "MOVI";
	 case 25: return "SLL";
	 case 26: return "SRL";
	 case 27: return "SRA";
	 case 28: return "NOT";
	 case 29: return "INC";
	 case 30: return "DEC";
	 case 31: return "j";
	 default : return "NULL";
	 }
 }
}
