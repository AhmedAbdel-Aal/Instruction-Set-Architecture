
public class Registers {
	private static Registers LogSingelton = null;

	public static Registers getInstance() {
		if (LogSingelton == null)
			LogSingelton = new Registers();

		return LogSingelton;
	}

	register zero = new register(0,Integer.toBinaryString(0));
	register One = new register(1,Integer.toBinaryString(1));
	register v0 = new register(2,"00000");
	register v1 = new register(3,"00000");
	register a0 = new register(4,"00000");
	register a1 = new register(5,"00000");
	register ra = new register(6,"00000");
	register sp = new register(7,"00000");
	register fp = new register(8,"00000");
	register gp = new register(9,"00000");
	register t0 = new register(10,"00000");
	register t1 = new register(11,"00000");
	register t2 = new register(12,"00000");
	register t3 = new register(13,"00000");
	register s0 = new register(14,"00000");
	register s1 = new register(15,"00000");
	register s2 = new register(16,"00000");

	int r1, r2, r3;

	public void fetchInstruction(Instruction i) {
		r1 = getRegisterValue(i.getR1Number());
		r2 = getRegisterValue(i.getR2Number());
		r3 = getRegisterValue(i.getR3Number());

	}

	public int getR1() {
		return r1;
	}

	public int getR2() {
		return r2;
	}

	public int getR3() {
		return r3;
	}

	public int getRegisterValue(int n) {
		switch (n) {
		case 0:
			return zero.getValue();
		case 1:
			return One.getValue();
		case 2:
			return v0.getValue();
		case 3:
			return v1.getValue();
		case 4:
			return a0.getValue();
		case 5:
			return a1.getValue();
		case 6:
			return ra.getValue();
		case 7:
			return sp.getValue();
		case 8:
			return fp.getValue();
		case 9:
			return gp.getValue();
		case 10:
			return t0.getValue();
		case 11:
			return t1.getValue();
		case 12:
			return t2.getValue();
		case 13:
			return t3.getValue();
		case 14:
			return s0.getValue();
		case 15:
			return s1.getValue();
		case 16:
			return s2.getValue();
		default:
			return 0;
		}
	}

	public void SetRegisterValue(String value, int n) {
		switch (n) {
		case 0:
			zero.setValue(value);
		case 1:
			One.setValue(value);
			;
		case 2:
			v0.setValue(value);
		case 3:
			v1.setValue(value);
		case 4:
			a0.setValue(value);
		case 5:
			a1.setValue(value);
		case 6:
			ra.setValue(value);
		case 7:
			sp.setValue(value);
		case 8:
			fp.setValue(value);
		case 9:
			gp.setValue(value);
		case 10:
			t0.setValue(value);
		case 11:
			t1.setValue(value);
		case 12:
			t2.setValue(value);
		case 13:
			t3.setValue(value);
		case 14:
			s0.setValue(value);
		case 15:
			s1.setValue(value);
		case 16:
			s2.setValue(value);

		}
	}
  public void printAllReg() {
	  System.out.println();
	  
  }
//	hello
	public String toString() {
		return "Registers [zero=" + zero + ", One=" + One + ", v0=" + v0 + ", v1="
				+ v1 + ", a0=" + a0 + ", a1=" + a1 + ", ra=" + ra + ", sp=" + sp
				+ ", fp=" + fp + ", gp=" + gp + ", t0=" + t0 + ", t1=" + t1
				+ ", t2=" + t2 + ", t3=" + t3 + ", s0=" + s0 + ", s1=" + s1
				+ ", s2=" + s2 + ", r1=" + r1 + ", r2=" + r2 + ", r3=" + r3 + "]";
	}

}
