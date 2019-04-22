import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Controller extends JFrame{
    ALU alu  = ALU.getInstance();
    static Memory memory = Memory.getInstance();
    Registers registers = Registers.getInstance();
    Control control = Control.getInstance();
    Translator translator = new Translator();
	static BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
    boolean flag=true;
    int state [] = {0,-1,-1,-1,-1};
	int pc=0;
	String i = null;
    String Iname="";
	String ProgramLines;
    int r1,r2,r3,Immediate,shift,address;
    int count=0;
    JTextArea program; 
    JPanel gridPanel;
    
//=========================================================
			 PipelineFile FETCH =  new PipelineFile(); 
			 PipelineFile DECODE =  new PipelineFile();
			 PipelineFile EXECUTE =  new PipelineFile();
			 PipelineFile MEMORY =  new PipelineFile();
			 PipelineFile WRITEBACK =  new PipelineFile();
			 PipelineFile temp =  new PipelineFile();
//=========================================================
	  public Controller(){
		  draw();
	  }
		public void draw(){
		this.setTitle("Java Assembler");
		this.getContentPane().setForeground(SystemColor.windowBorder);
		this.getContentPane().setBackground(SystemColor.activeCaption);
		this.setBounds(100, 100, 1000, 490);
		this.getContentPane().setLayout(null);
		
		program = new JTextArea();
		program.setFont(new Font("Arial", Font.PLAIN, 24));
		JScrollPane scroll = new JScrollPane(program);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(12, 13, 450, 362);
		this.getContentPane().add(scroll);
		
		gridPanel =  new  JPanel();
		gridPanel.setBounds(12+450+50, 13, 450, 362);
		gridPanel.setLayout(new GridLayout(8, 2));
		for (int i = 0; i < 8 * 2; i++) {
			
		    gridPanel.add(new JLabel(registers.registersArray[i]+" = "+registers.getRegisterValue(i) ));
		}
		this.getContentPane().add(gridPanel);

		JButton load = new JButton("Load Program");
		load.setBounds(22, 388, 200, 50);
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					LoadProgram();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		this.getContentPane().add(load);
		
		JButton execute = new JButton("Execute");
		execute.setBounds(22+200+25, 388, 200, 50);
		execute.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					ExecuteProgram();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		this.getContentPane().add(execute);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setVisible(true);
		this.setVisible(true);
		

		
	}
			 
			 
			 
			 
			 
//-------------------------------------------------------------------------------------------------------  
	 
			 
	public static void main(String[] args) {
		Controller c = new Controller();
	}		 
			 
			 
			 
			 
//-------------------------------------------------------------------------------------------------------  
	 public void LoadProgram () throws IOException{
		 String s[]= program.getText().split("\n");
		 initProgramFile(s[0]);
		 System.out.println(Arrays.toString(s));
		 System.out.println(s[0]+" "+s[1]);
		 for(int i=1;i<s.length;i++){
		   memory.IMemory.add(new Instruction(translator.translate(s[i]),s[0],i));	 
			 System.out.println("==="+translator.translate(s[i])+"===");
		 }
	 }
	
//-------------------------------------------------------------------------------------------------------  
	
	 public void  ExecuteProgram () throws Exception {
		int x=1;
		int v=1;
		int s=0;
		
		while(flag) {
			Fetch();
			Decode();
			Execute();
			MemoryRW();
			WriteBack();
	
			System.out.println("==============================");
			System.out.println("at loop "+v+" = "+registers.toString());
			System.out.println(FETCH.info.toString());
			System.out.println(DECODE.info.toString());
			System.out.println(EXECUTE.info.toString());
			System.out.println(MEMORY.info.toString());
			System.out.println(WRITEBACK.info.toString());
			System.out.println("==============================");	
			
			RefreshPipelines();
		System.out.println(registers.toString());
		System.out.println(Arrays.toString(memory.DMemory));
		updateRpanel();
	}
	
 
//-------------------------------------------------------------------------------------------------------  

	private void updateRpanel() {
		// TODO Auto-generated method stub
		ProgramLines = this.program.getText();
		draw();
		this.program.setText(ProgramLines);

		
	}



	//-------------------------------------------------------------------------------------------------------  

	private static void initProgramFile(String line) throws IOException {
	// TODO Auto-generated method stub
	File f = new  File("InstructionFiles/"+line);
	f.mkdir();	
}

//-------------------------------------------------------------------------------------------------------  

 
public void Fetch(){
	 if((pc<this.memory.IMemory.size())){
	 Instruction i = this.memory.getInstruction(pc);
	 System.out.println("from fetch we get i = "+this.memory.getInstruction(pc).instruction);

	 // set values needed for decode method
	 FETCH.add("instruction", i);
	 FETCH.add("path",this.memory.getInstruction(pc).getIpath());
	 pc=pc+1;
	 count=pc;
	 }
	 else{
		 count++;
	 }
	if(count>pc+4){
		this.flag=false;
	}
 }
 public void Decode(){
	   if(!DECODE.info.isEmpty()){
	   Instruction i = (Instruction) DECODE.get("instruction");
//	   System.out.println(i.toString()+"***");
       String Iname=this.control.getOperationName(i.getOpCode()); 
	   r1 = this.registers.getRegisterValue(i.getR1Number());
	   r2 = this.registers.getRegisterValue(i.getR2Number());
	   r3 = this.registers.getRegisterValue(i.getR3Number());
//	   System.out.println(r1+" "+r2+" "+r3+" ");
	   Immediate=i.getImmediate();
	   shift =i.getShift();
	   address = i.getAddress();
	   // set values needed for execute method
	   DECODE.add("iname",Iname);
	   DECODE.add("opCode",i.getOpCode());
	   DECODE.add("r1V",r1);
	   DECODE.add("r1N",i.getR1Number());	   
	   DECODE.add("r2V",r2);
	   DECODE.add("r2N",i.getR2Number());
	   DECODE.add("r3V",r3);
	   DECODE.add("r3N",i.getR3Number());
	   DECODE.add("immediate",Immediate);
	   DECODE.add("shift",shift);
	   DECODE.add("RegDst",control.isRegDst());
	   DECODE.add("Branch",control.isBranch());
	   DECODE.add("MemRead",control.isMemRead());
	   DECODE.add("MemWrite",control.isMemWrite());
	   DECODE.add("MemtoReg",control.isMemtoReg());
	   DECODE.add("ALUSrc",control.isALUSrc());
	   DECODE.add("regWrite",control.isRegWrite());
	   DECODE.add("type",control.getType());
	   DECODE.add("address",address);
	   this.control.ResetControlSignals();
	   if(DECODE.get("iname").equals("j")){
		   pc = address;
	      System.out.println("the new pc = "+pc);
	      return;
	   
	   }
	   
   }

 }
//-------------------------------------------------------------------------------------------------------  

 public void Execute(){
	   if(!EXECUTE.info.isEmpty()&&!EXECUTE.get("iname").equals("j")){

	 //extract operation name
	     String iname = (String) EXECUTE.get("iname"); 
	 //extract type , registers , immediate and shift
		 String type = (String) EXECUTE.get("type");
		 System.out.println("from execute type = "+type);
		 int r1 = (int) EXECUTE.get("r1V");
		 int r2 = (int) EXECUTE.get("r2V");
		 int r3 = (int) EXECUTE.get("r3V");
		 int immediate = (int) EXECUTE.get("immediate");
		 int shift = (int) EXECUTE.get("shift");
	 //extract control signals
		 boolean regDst=(boolean) EXECUTE.get("RegDst");
		 boolean Branch=(boolean) EXECUTE.get("Branch");
		 boolean MemRead=(boolean) EXECUTE.get("MemRead");
		 boolean MemWrite=(boolean) EXECUTE.get("MemWrite");
		 boolean MemtoReg=(boolean) EXECUTE.get("MemtoReg");
		 boolean ALUSrc=(boolean) EXECUTE.get("ALUSrc");
		 boolean regWrite=(boolean) EXECUTE.get("RegWrite");
	
	// execution of operation inside ALU 
	int z=0;	 
	 if(type.equals("R")||type.equals("O")||(type.equals("I")&&(!(iname.equals("LD")||iname.equals("LDI")||iname.equals("SW"))))){	 
		 if(iname.equals("MULA")){
			 alu.setAcc(r3);
		 }
		 if(iname.equals("BEQ")){
			alu.compute(r3, r2, iname);
			if(alu.isZero()&&Branch){
				this.pc = immediate; 
				 EXECUTE.add("ALUZero", alu.isZero());
				 System.out.println("the new pc = "+pc);
				return;
			}
		 }
		 int x = r2;
		 int y = Mux2x1(r1, immediate, ALUSrc);
		  z = this.alu.compute(x, y, iname);
		 System.out.println("x = "+x+" y= "+y+" z= "+z);
	 }
	 

   // add the stage modifications
		 EXECUTE.add("ALUZero", alu.isZero());
		 EXECUTE.add("ALUout",z);
	 }
	 else {
		 System.out.println("not there yet in the execute phase");
	 }
		 
  }
 }
 


//-------------------------------------------------------------------------------------------------------  
 
 public void MemoryRW(){
	   if(!MEMORY.info.isEmpty()&&!MEMORY.get("iname").equals("BEQ")&&!MEMORY.get("iname").equals("j")){

	 //extract operation name
	     String iname = (String) MEMORY.get("iname"); 
	 //extract type , registers , immediate and shift
		 String type = (String) MEMORY.get("type");
		 int r1 = (int) MEMORY.get("r1V");
		 int r2 = (int) MEMORY.get("r2V");
		 int r3 = (int) MEMORY.get("r3V");
		 int immediate = (int) MEMORY.get("immediate");
		 int shift = (int) MEMORY.get("shift");
	 //extract control signals
		 boolean regDst=(boolean) MEMORY.get("RegDst");
		 boolean Branch=(boolean) MEMORY.get("Branch");
		 boolean MemRead=(boolean) MEMORY.get("MemRead");
		 boolean MemWrite=(boolean) MEMORY.get("MemWrite");
		 boolean MemtoReg=(boolean) MEMORY.get("MemtoReg");
		 boolean ALUSrc=(boolean) MEMORY.get("ALUSrc");
		 boolean regWrite=(boolean) MEMORY.get("RegWrite");
		 
	 int x=0;	 
	if(iname.equals("SW")&&MemWrite){
		int address=r2;
		int offset=immediate;
		String data = Integer.toBinaryString(r3);
		memory.store(data, offset, address);
	}
	else if(iname.equals("LD")&&MemRead){
		int address = r2;
		int offset=immediate;
		r3 = Integer.parseInt(memory.load(address, offset),2);
		MEMORY.add("r3V", r3);
      	int Rn =(int) MEMORY.get("r3N");
    	MEMORY.add("ALUout", r3);
     //   this.registers.setRegisterValue(Rn, r3);
	}
	else if(iname.equals("LDI")&&MemRead){
		int address = immediate;
		int offset = (int)MEMORY.get("r2N");
		r3 = Integer.parseInt(memory.load(address, offset),2);
		MEMORY.add("r3V", r3);
      	int Rn =(int) MEMORY.get("r3N");
      	MEMORY.add("ALUout", r3);
     //   this.registers.setRegisterValue(Rn, r3);

	}
	 }
	 else {
		 System.out.println("not there yet in memory write phase");
	 }
	 
	 

  }
 }
//-------------------------------------------------------------------------------------------------------  

 public void WriteBack() throws Exception{
	   if(!WRITEBACK.info.isEmpty()&&!WRITEBACK.get("iname").equals("BEQ")&&!WRITEBACK.get("iname").equals("j")){

 //extract operation name
     String iname = (String) WRITEBACK.get("iname"); 
 //extract type , registers , immediate and shift
	 String type = (String) WRITEBACK.get("type");
	 int r1 = (int) WRITEBACK.get("r1V");
	 int r2 = (int) WRITEBACK.get("r2V");
	 int r3 = (int) WRITEBACK.get("r3V");

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

	 
	 if(regWrite){
	 int x = (int) WRITEBACK.get("ALUout");
	 int y = Mux2x1(1, 2, regDst);
	 System.out.println("reg Dst mux = "+y);
	 if(y==1){
     	 WRITEBACK.add("r2V", x);
     	      	 int Rn =(int) WRITEBACK.get("r2N");
     	 this.registers.setRegisterValue(Rn, x);

	 }
	 else{
		 WRITEBACK.add("r3V", x);
     	 int Rn =(int) WRITEBACK.get("r3N");
     	 this.registers.setRegisterValue(Rn, x);
     	 System.out.println(Rn+" ---- "+x);

	 }
	 }
	 
 
	//write the instruction file
	 String filepath =((Instruction) WRITEBACK.get("instruction")).getIpath();
		// to write to the file => open write streams to the file
		FileOutputStream FileOut = new FileOutputStream(filepath);
		ObjectOutputStream Out = new ObjectOutputStream(FileOut);
		//write to the object
		Out.writeObject(WRITEBACK.info);

	 
	 
	 
  }
 }
//-------------------------------------------------------------------------------------------------------  

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
	  FETCH.clear();
	  System.out.println("after refresh");
	  System.out.println("fetch = "+FETCH.toString());
	  System.out.println("decode = "+DECODE.toString());
	  System.out.println("execute = "+EXECUTE.toString());
	  System.out.println("memory = "+MEMORY.toString());
	  System.out.println("write back = "+WRITEBACK.toString());


	}
//-------------------------------------------------------------------------------------------------------  

 public int Mux2x1(int x,int y,boolean s){
	 return(s)?y:x;
 }

}
