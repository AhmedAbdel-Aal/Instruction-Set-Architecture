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
			
		}
	}
	
 /// load program in instruction memory	
	public static  void LoadProgram() throws Exception{
		System.out.println("write your program name");
		String line = br.readLine();
		String pname=line;
		initProgramFile(pname);
		int k=0;
		while(line!=null){
		line = br.readLine();	
		Instruction i = new Instruction(line,pname,k);
		memory.IMemory.add(i);
		k++;
		}	
		
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
	Instruction i = null;
 String Iname="";
 int r1,r2,r3,Immediate,shift,address;
 
public void Fetch(){
	 
	 this.i = this.memory.getInstruction(pc);
	 pc=pc+1;
	 state[0]++;state[1]++;
 }
 public void Decode(){
	 
       this.Iname=this.control.getInstructionName(i.getOpCode()); 
	   r1 = this.registers.getRegisterValue(i.getR1Number());
	   r2 = this.registers.getRegisterValue(i.getR2Number());
	   r3 = this.registers.getRegisterValue(i.getR3Number());
	   Immediate=i.getImmediate();
	   shift =i.getShift();
	   address = i.getAddress();
	   state[1]++;state[2]++;
 }
 public void Execute(){
	 
	 
	  state[2]++;state[3]++;
 }
 public void MemoryRW(){
	 
	 
	  state[1]++;state[2]++;

 }
 public void WriteBack(){
	 
 }
	
}
