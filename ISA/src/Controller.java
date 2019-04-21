import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Consumer;


public class Controller {
    ALU alu  = ALU.getInstance();
    static Memory memory = Memory.getInstance();
    Registers registers = Registers.getInstance();
    Control control = Control.getInstance();
	static BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
 
	public static void main(String[] args) throws Exception {
		/*code
		 * addition
		 * 0011110010000000000000001  addi a0,0,1
           0011110010100000000000010  addi a1,0,2
           0000000001000100001010000  add v0,a0,a1
		 * */
		Controller c = new Controller();
		ArrayList<Instruction> insts = new ArrayList<Instruction>();
		System.out.println("write your program name");
		String line = br.readLine();
		String pname=line;
		initProgramFile(pname);
		int k=0;
		line =br.readLine();
		while(!line.equals("end")){
		Instruction i = new Instruction(line,pname,k);
		memory.IMemory.add(i);
		insts.add(i);
		k++;
		System.out.println("inst "+k+" "+i.instruction);
		System.out.println("enter next instruction");

		line = br.readLine();	
		}
		int x=0;
		int v=0;
		while(v<8) {
		 if(x==0)	
			c.Fetch();
		 if(x==1){
			c.Fetch(); 
			c.Decode();
		 }
		 if(x==3){
			 c.Fetch(); 
				c.Decode(); 
			c.Execute();
		 }
		 if(x==4){
				c.Decode(); 
			c.Execute();
			c.MemoryRW();
		 }
		 if(x>=5){
			c.Execute();
			c.MemoryRW();
			c.WriteBack();
		 }
		 if(x==6){
			 c.MemoryRW();
				c.WriteBack();
		 }
		 if(x==7){
			 c.WriteBack();
		 }
		 x++;
		 v++;
			c.RefreshPipelines();
			System.out.println("for k == "+k);
			System.out.println(c.DECODE.info.toString());
			System.out.println(c.EXECUTE.info.toString());
			System.out.println(c.MEMORY.info.toString());
			System.out.println(c.WRITEBACK.info.toString());

		}
		c.registers.toString();
	}
	
 
/// load program in instruction memory	
	public static  ArrayList<Instruction> LoadProgram() throws Exception{
		ArrayList<Instruction> insts = new ArrayList<Instruction>();
		System.out.println("write your program name");
		String line = br.readLine();
		String pname=line;
		initProgramFile(pname);
		int k=0;
		line =br.readLine();
		while(line!="end"){
		Instruction i = new Instruction(line,pname,k);
		memory.IMemory.add(i);
		insts.add(i);
		k++;
		System.out.println("enter next instruction");
		line = br.readLine();	
		}	
	 return insts;	
	}
	
	private static void initProgramFile(String line) throws IOException {
	// TODO Auto-generated method stub
	File f = new  File("InstructionFiles\\"+line);
	f.mkdir();	
}

	public void Operate(){
		int x=1;
		if(x==1){
			
		}
		if(x==2){
			
		}
		if(x==3){
			
		}
		if(x==4){
			
		}
		if(x==5){
			
		}
	}
 
	
//-------------------------------------------------------------------------------------------------  
    int state [] = {0,-1,-1,-1,-1};
	int pc=0;
	String i = null;
 String Iname="";
 int r1,r2,r3,Immediate,shift,address;
 
 PipelineFile FETCH =  new PipelineFile();
 PipelineFile DECODE =  new PipelineFile();
 PipelineFile EXECUTE =  new PipelineFile();
 PipelineFile MEMORY =  new PipelineFile();
 PipelineFile WRITEBACK =  new PipelineFile();
 PipelineFile temp =  new PipelineFile();
 
public void Fetch(){
	 
	 Instruction i = this.memory.getInstruction(pc);
	 System.out.println("from fetch we get i = "+this.memory.getInstruction(pc).instruction);
	 // set values needed for decode method
	 FETCH.add("instruction", i);
	 FETCH.add("path",this.memory.getInstruction(pc).getIpath());
	 pc=pc+1;
 }
 public void Decode(){
	   
	   Instruction i = (Instruction) DECODE.get("instruction");
       String Iname=this.control.getOperationName(i.getOpCode()); 
	   r1 = this.registers.getRegisterValue(i.getR1Number());
	   r2 = this.registers.getRegisterValue(i.getR2Number());
	   r3 = this.registers.getRegisterValue(i.getR3Number());
	   Immediate=i.getImmediate();
	   shift =i.getShift();
	   address = i.getAddress();
	   // set values needed for execute method
	   DECODE.add("iname",Iname);
	   DECODE.add("r1",r1);
	   DECODE.add("r2",r2);
	   DECODE.add("r3",r3);
	   DECODE.add("immediat",Immediate);
	   DECODE.add("shift",shift);
	   DECODE.add("RegDst",control.isRegDst());
	   DECODE.add("Branch",control.isBranch());
	   DECODE.add("MemRead",control.isMemRead());
	   DECODE.add("MemWrite",control.isMemWrite());
	   DECODE.add("MemtoReg",control.isMemtoReg());
	   DECODE.add("ALUSrc",control.isALUSrc());
	   DECODE.add("RegWrite",control.isALUSrc());
	   DECODE.add("tye",control.getType());
	   this.control.ResetControlSignals();
 }
 public void Execute(){
	 //extract operation name
	     String iname = (String) EXECUTE.get("iname"); 
	 //extract type , registers , immediate and shift
		 String type = (String) EXECUTE.get("type");
		 int r1 = (int) EXECUTE.get("r1");
		 int r2 = (int) EXECUTE.get("r2");
		 int r3 = (int) EXECUTE.get("r3");
		 int immediate = (int) EXECUTE.get("immediate");
		 int shift = (int) EXECUTE.get("shift");
	 //extract control signals
		 boolean regDst=(boolean) EXECUTE.get("RegDst");
		 boolean Branch=(boolean) EXECUTE.get("Branch");
		 boolean MemRead=(boolean) EXECUTE.get("MemRead");
		 boolean MemWrite=(boolean) EXECUTE.get("MemWrite");
		 boolean MemtoReg=(boolean) EXECUTE.get("MemtoReg");
		 boolean ALUSrc=(boolean) EXECUTE.get("ALUSrc");
		 boolean regWrite=(boolean) EXECUTE.get("regWrite");
	
	// execution of operation inside ALU 
		 int x = r1;
		 int y = Mux2x1(r2, immediate, ALUSrc);
		 int z = this.alu.compute(x, y, iname);


   // add the stage modifications
		 EXECUTE.add("ALUZero", alu.isZero());
		 EXECUTE.add("ALUout",z);
		 
 }
 public void MemoryRW(){
	 //extract operation name
	     String iname = (String) MEMORY.get("iname"); 
	 //extract type , registers , immediate and shift
		 String type = (String) MEMORY.get("type");
		 int r1 = (int) MEMORY.get("r1");
		 int r2 = (int) MEMORY.get("r2");
		 int r3 = (int) MEMORY.get("r3");
		 int immediate = (int) MEMORY.get("immediate");
		 int shift = (int) MEMORY.get("shift");
	 //extract control signals
		 boolean regDst=(boolean) MEMORY.get("RegDst");
		 boolean Branch=(boolean) MEMORY.get("Branch");
		 boolean MemRead=(boolean) MEMORY.get("MemRead");
		 boolean MemWrite=(boolean) MEMORY.get("MemWrite");
		 boolean MemtoReg=(boolean) MEMORY.get("MemtoReg");
		 boolean ALUSrc=(boolean) MEMORY.get("ALUSrc");
		 boolean regWrite=(boolean) MEMORY.get("regWrite");
		 
	 int x=0;	 
	if(iname.equals("SW")){
	}
	else if(iname.equals("LD")){
		x=1;
	}
	int t = Mux2x1((int)MEMORY.get("ALUout"),x ,MemtoReg);
	MEMORY.add("ALUout",t);

	 
	 

 }
 public void WriteBack() throws Exception{
 //extract operation name
     String iname = (String) WRITEBACK.get("iname"); 
 //extract type , registers , immediate and shift
	 String type = (String) WRITEBACK.get("type");
	 int r1 = (int) WRITEBACK.get("r1");
	 int r2 = (int) WRITEBACK.get("r2");
	 int r3 = (int) WRITEBACK.get("r3");
	 int immediate = (int) WRITEBACK.get("immediate");
	 int shift = (int) WRITEBACK.get("shift");
 //extract control signals
	 boolean regDst=(boolean) WRITEBACK.get("RegDst");
	 boolean Branch=(boolean) WRITEBACK.get("Branch");
	 boolean MemRead=(boolean) WRITEBACK.get("MemRead");
	 boolean MemWrite=(boolean) WRITEBACK.get("MemWrite");
	 boolean MemtoReg=(boolean) WRITEBACK.get("MemtoReg");
	 boolean ALUSrc=(boolean) WRITEBACK.get("ALUSrc");
	 boolean regWrite=(boolean) WRITEBACK.get("regWrite");
	 
	 int x = (int) WRITEBACK.get("ALUout");
	 int y = Mux2x1(1, 2, regDst);
	 if(y==1)
     	 WRITEBACK.add("r2", x);
	 else
		 WRITEBACK.add("r3", x);
 
	//write the instruction file
	 String filepath =((Instruction) WRITEBACK.get("instruction")).getIpath();
		// to write to the file => open write streams to the file
		FileOutputStream FileOut = new FileOutputStream(filepath);
		ObjectOutputStream Out = new ObjectOutputStream(FileOut);
		//write to the object
		Out.writeObject(WRITEBACK.info);

	 
	 
	 
 }
 private void RefreshPipelines() {
		// TODO Auto-generated method stub
      WRITEBACK.clear();
	  WRITEBACK.clone(MEMORY);
	  MEMORY.clear();
	  MEMORY.clone(EXECUTE);
	  EXECUTE.clear();
	  EXECUTE.clone(DECODE);
	  DECODE.clear();
	  DECODE.clone(FETCH);
	}

 public int Mux2x1(int x,int y,boolean s){
	 return(s)?x:y;
 }
}
