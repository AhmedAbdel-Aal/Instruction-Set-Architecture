
public class Registers {
  register zero  = new register(0);	
  register One  = new register(1);
  register v0  = new register(2);
  register v1  = new register(3);
  register a0  = new register(4);
  register a1  = new register(5);
  register ra  = new register(6);
  register sp  = new register(7);
  register fp  = new register(8);
  register gp  = new register(9);
  register t0  = new register(10);
  register t1  = new register(11);
  register t2  = new register(12);
  register t3  = new register(13);
  register s0  = new register(14);
  register s1  = new register(15);
  register s2  = new register(16);
  
  
  public void fetchInstruction(Instruction i ){
	  int r1 = getRegisterValue(i.getR1Number());
	  int r2 = getRegisterValue(i.getR2Number());
	  int r3 = getRegisterValue(i.getR3Number());
	  

  }

  
  
  
  
  
  
  public int getRegisterValue(int n){
	  switch(n){
	  case 0: return zero.getValue();
	  case 1: return One.getValue(); 
	  case 2: return v0.getValue(); 
	  case 3: return v1.getValue(); 
	  case 4: return a0.getValue(); 
	  case 5: return a1.getValue(); 
	  case 6: return ra.getValue(); 
	  case 7: return sp.getValue(); 
	  case 8: return fp.getValue(); 
	  case 9: return gp.getValue(); 
	  case 10: return t0.getValue(); 
	  case 11: return t1.getValue(); 
	  case 12: return t2.getValue(); 
	  case 13: return t3.getValue(); 
	  case 14: return s0.getValue(); 
	  case 15: return s1.getValue(); 
	  case 16: return s2.getValue(); 
	  default : return 0;
	  }
  }
  


}
