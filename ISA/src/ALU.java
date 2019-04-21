
public class ALU {
	
	int acc ;
	boolean zero=false;
	 private static ALU LogSingelton = null;
	 public static ALU getInstance() 
	 { 
	     if (LogSingelton == null) 
	    	 LogSingelton = new ALU(); 

	     return LogSingelton; 
	 }
	 
	 
	 public void setAcc(int acc) {
		this.acc = acc;
	}


	public int compute(int x , int y , String op){
		 int z=-1;
		 if(op.equals("ADD")){
			 z=x+y;
		 }
		 else if(op.equals("SUB")){
			 z=x-y;
		 }
		 else if(op.equals("AND")){
			 z=x&y;
		 }else if(op.equals("OR")){
			 z=x|y;
		 }else if(op.equals("XOR")){
			 z=x^y;
		 }else if(op.equals("MOD")){
			 z=x%y;
		 }else if(op.equals("IDV")){
			 z=x/y;
		 }else if(op.equals("MUL")){
			 z=x*y;
		 }else if(op.equals("MULA")){
			 z=acc+(x*y);
		 }else if(op.equals("nCk")){
			 z=binomialCoeff(x, y); //////////////////////////////////////comment
		 }else if(op.equals("SUBRV")){
			 z=y-x;
		 }else if(op.equals("GCD")){
			 z= GCD(x,y);
		 }else if(op.equals("LCM")){
			 z=LCM(x,y);
		 }else if(op.equals("POW")){
			 z=(int)Math.pow(x, y);
		 }else if(op.equals("SLT")){
			 z= (x<y)?1:0;
		 }else if(op.equals("ADDI")){
			 z=x+y;
		 }else if(op.equals("SLTI")){
			 z=(x<y)?1:0;
		 }else if(op.equals("NZERO")){
			 z=32-numberOfSetBits(x);
		 }
		 else if(op.equals("NONE")){
			 z=numberOfSetBits(x);
		 }else if(op.equals("MOV")){
			 z=x;
		 }else if(op.equals("MOVI")){
			 z=y;
		 }
		 else if(op.equals("SLL")){
			 z=x<<y;
		 }else if(op.equals("SRL")){
			 z=x>>>y;
		 }else if(op.equals("SRA")){
			 z=x>>y;
		 }
		 else if(op.equals("NOT")){
			 z=~x;
		 }else if(op.equals("INC")){
			 z=x+1;
		 }else if(op.equals("DEC")){
			 z=x-1;
		 }else if(op.equals("BEQ")){
			 zero =(x==y);
		 }
		 
		 
		return z; 
	 }
	
	public int GCD(int a,int b){
		if (a == 0)
            return b;
         
        return GCD(b%a, a);
	}
	public int LCM(int a,int b){
        return (a*b)/GCD(a, b);

	}
	int numberOfSetBits(int i)
	{
	     // Java: use >>> instead of >>
	     // C or C++: use uint32_t
	     i = i - ((i >> 1) & 0x55555555);
	     i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
	     return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
	}
	
	public static int reverseBits(int n) 
    { 
        int rev = 0; 
        while (n > 0)  
        { 
            rev <<= 1; 
            if ((int)(n & 1) == 1) 
                rev ^= 1; 
            n >>= 1; 
        } 
        return rev; 
    }


	public boolean isZero() {
		return zero;
	} 
	public int binomialCoeff(int n, int k) 
    {
     
        // Base Cases
        if (k == 0 || k == n)
            return 1;
         
        // Recur
        return binomialCoeff(n - 1, k - 1) + 
                    binomialCoeff(n - 1, k);
    }

}
