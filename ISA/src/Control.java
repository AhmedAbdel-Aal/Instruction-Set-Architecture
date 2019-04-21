
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
 String type ;


 public static Control getInstance() 
 { 
     if (LogSingelton == null) 
    	 LogSingelton = new Control(); 

     return LogSingelton; 
 } 
//-----------------------------------------------------------
 public void ResetControlSignals(){
	  regDst=false;
	  Branch=false;
	  MemRead=false;
	  MemWrite=false;
     MemtoReg=false;
	  ALUSrc=false;
	  regWrite=false;
      type="";
 }
 
 public void setOpCode(String s){
	 this.opCode=s;
	 int x = Integer.parseInt(opCode,2);
	 
	 // set control signals needed for R-type Instructions
	 if(x>=0&&x<=14){ 
		  regDst=true;
		  Branch=false;
		  MemRead=false;
		  MemWrite=false;
	      MemtoReg=false;
		  ALUSrc=false;
		  regWrite=true;
		  type="R";
	 }
	 if(x==15||x==16){
		 regDst=true;
		 ALUSrc=true;
		 regWrite=true;
		  type="I";
	 }
	 if(x==17){
		 Branch=true;
		  type="I";
	 }
	 if(x==18){
		 MemRead=true;
		 regWrite=true;
		 ALUSrc=true;
		 MemtoReg=true;
		 regDst=true;
		 type="I";
	 }
	 if(x==19||x==20){
		 regDst=true;
		 regWrite=true;
		  type="I";
	 }
	 if(x==21){
		  regDst=true;
		  MemRead=true;
	      MemtoReg=true;
		  regWrite=true;
		  type="I";
	 }
	 if(x==22){
		  MemWrite=true; type="I";
	 }
	 if(x==23){
		  regDst=true;
		  regWrite=true;
		  type="I";
	 }
	 if(x>=24&&x<=27){
		  ALUSrc=true;
	      regWrite=true;
		  type="O";
	 }
	 if(x==28||x==29||x==30){
		  regWrite=true; type="O";
	 }
	 if(x==31){
		  type="J";
	 }
	 
 }
 public String getOperationName(String opCode){
	 int x = Integer.parseInt(opCode,2);
	 this.setOpCode(opCode);
	 switch(x){
	 //R-type Instructions
	 case 0:{ return "ADD";}
	 case 1: return "SUB";
	 case 2: return "AND";
	 case 3: return "OR";
	 case 4: return "XOR";
	 case 5: return "MOD";
	 case 6: return "IDV";
	 case 7: return "MUL";
	 case 8: return "MULA";
	 case 9: return "nCk";
	 case 10: return "SUBRV";
	 case 11: return "GCD";
	 case 12: return "LCM";
	 case 13: return "POW";
	 case 14: return "SLT";
	 //I-type instructions
	 case 15: return "ADDI";
	 case 16: return "SLTI";
	 case 17: return "BEQ";
	 case 18: return "LDI";
	 case 19: return "NZERO";
	 case 20: return "NONE";
	 case 21: return "LD";
	 case 22: return "SW";
	 case 23: return "MOV";
	 //O-type instructions
	 case 24: return "MOVI";
	 case 25: return "SLL";
	 case 26: return "SRL";
	 case 27: return "SRA";
	 case 28: return "NOT";
	 case 29: return "INC";
	 case 30: return "DEC";
	 //j-type format
	 case 31: return "j";
	 default : return "NULL";
	 }
 }
public boolean isRegDst() {
	return regDst;
}
public boolean isBranch() {
	return Branch;
}
public boolean isMemRead() {
	return MemRead;
}
public boolean isMemWrite() {
	return MemWrite;
}
public boolean isMemtoReg() {
	return MemtoReg;
}
public boolean isRegWrite() {
	return regWrite;
}
public boolean isALUSrc() {
	return ALUSrc;
}
public String getType() {
	return type;
}
 
 
}
