import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;


public class Controller {
    ALU alu  = ALU.getInstance();
    static Memory memory = Memory.getInstance();
    Registers registers = Registers.getInstance();
    Control control = Control.getInstance();
	static BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
 
	public static void main(String[] args) throws Exception {
		Controller c = new Controller();
		LoadProgram();
		while(true) {
			c.Fetch();
			c.Decode();
			c.Execute();
			c.MemoryRW();
			c.WriteBack();
			c.RefreshPipelines();
		}
	}
	
 
/// load program in instruction memory	
	public static  void LoadProgram() throws Exception{
		System.out.println("write your program name");
		String line = br.readLine();
		String pname=line;
		initProgramFile(pname);
		int k=0;
		line =br.readLine();
		while(line!=null){
		Instruction i = new Instruction(line,pname,k);
		memory.IMemory.add(i);
		k++;
		line = br.readLine();	
		}	
		
	}
	
	private static void initProgramFile(String line) throws IOException {
	// TODO Auto-generated method stub
	File f = new  File("InstructionFiles/"+line);
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
	Instruction i = null;
 String Iname="";
 int r1,r2,r3,Immediate,shift,address;
 
 PipelineFile FETCH =  new PipelineFile();
 PipelineFile DECODE =  new PipelineFile();
 PipelineFile EXECUTE =  new PipelineFile();
 PipelineFile MEMORY =  new PipelineFile();
 PipelineFile WRITEBACK =  new PipelineFile();
 PipelineFile temp =  new PipelineFile();
 
public void Fetch(){
	 
	 this.i = this.memory.getInstruction(pc);
	 // set values needed for decode method
	 FETCH.add("instruction", i);
	 FETCH.add("path",i.getIpath());
	 pc=pc+1;
	 state[0]++;state[1]++;
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
// nada
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
		 
		 
	if(iname.equals("SW")){
		if(regWrite) {
//			address of memory stored in r3
			int memAddress=registers.getRegisterValue(r3);
//			offset is the immediate value
//			data to be stored in memory is saved in r2
			String data=Integer.toBinaryString(registers.getRegisterValue(r2));
			memory.store(data, immediate, Integer.toBinaryString(memAddress));	
		}
	}
	else if(iname.equals("LD")){
		String address=Integer.toBinaryString(registers.getRegisterValue(r2));
		String out=memory.load(address, immediate);
//		store value in register
		this.registers.SetRegisterValue(out, r3);
	}

	 
	 

 }
 public void WriteBack(){
	 
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
	}

 public int Mux2x1(int x,int y,boolean s){
	 return(s)?x:y;
 }

}
