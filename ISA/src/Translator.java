
public class Translator {
 
	public static String translate(String WordInst) {
//		 ADD $a0 $a1 $s0
			String Inst = "";
			String type="";
			String instName="";
			if (WordInst.contains(",")||WordInst.contains("j")) {
				String[] arr = WordInst.split(",");
				
				if (arr[0].equals("ADD")) {
					instName+="00000";
					type="R";

				} else if (arr[0].equals("SUB")) {
					instName+="00001";
					type="R";

				} else if (arr[0].equals("AND")) {
					instName+="00010";
					type="R";

				} else if (arr[0].equals("OR")) {
					instName+="00011";
					type="R";

				} else if (arr[0].equals("XOR")) {
					instName+="00100";
					type="R";

				} else if (arr[0].equals("MOD")) {
					instName+="00101";
					type="R";

				} else if (arr[0].equals("IDV")) {
					instName+="00110";
					type="R";

				} else if (arr[0].equals("MUL")) {
					instName+="00111";
					type="R";

				} else if (arr[0].equals("MULA")) {
					instName+="01000";
					type="R";

				} else if (arr[0].equals("nCk")) {
					instName+="01001";
					type="R";

				} else if (arr[0].equals("SUBRV")) {
					instName+="01010";
					type="R";

				} else if (arr[0].equals("GCD")) {
					instName+="01011";
					type="R";

				} else if (arr[0].equals("LCM")) {
					instName+="01100";
					type="R";

				} else if (arr[0].equals("POW")) {
					instName+="01101";
					type="R";

				} else if (arr[0].equals("SLT")) {
					instName+="01110";
					type="R";

				}else if (arr[0].equals("ADDI")) {
					instName+="01111";
					type="I";

				}else if (arr[0].equals("SLTI")) {
					instName+="10000";
					type="I";

				}else if (arr[0].equals("BEQ")) {
					instName+="10001";
					type="I";

				}else if (arr[0].equals("LDI")) {
					instName+="10010";
					type="I";

				}else if (arr[0].equals("NZERO")) {
					instName+="10011";
					type="I";

				}else if (arr[0].equals("NONE")) {
					instName+="10100";
					type="I";

				}else if (arr[0].equals("LD")) {
					instName+="10101";
					type="I";

				}else if (arr[0].equals("SW")) {
					instName+="10110";
					type="I";

				}else if (arr[0].equals("MOV")) {
					instName+="10111";
					type="I";

				}
				else if (arr[0].equals("MOVI")) {
					instName+="11000";
					type="O";

				}else if (arr[0].equals("SLL")) {
					instName+="11001";
					type="O";

				}else if (arr[0].equals("SRL")) {
					instName+="11010";
					type="O";

				}else if (arr[0].equals("SRA")) {
					instName+="11011";
					type="O";

				}else if (arr[0].equals("NOT")) {
					instName+="11100";
					type="O";

				}else if (arr[0].equals("INC")) {
					instName+="11101";
					type="O";

				}else if (arr[0].equals("DEC")) {
					instName+="11110";
					type="O";

				}else if (arr[0].equals("j")) {
					System.out.println("hello");
					instName+="11111";
					type="J";

				}
				if(type.equals("R")||type.equals("I")) {
//					System.out.println("hna1");
					if(arr[1].equals("Zero")) {
						instName+="00000";
					}
					else if(arr[1].equals("One")) {
						instName+="00001";
					}
					else if(arr[1].equals("v0")) {
						instName+="00010";
					}else if(arr[1].equals("v1")) {
						instName+="00011";
					}else if(arr[1].equals("a0")) {
						instName+="00100";
					}else if(arr[1].equals("a1")) {
						instName+="00101";
					}else if(arr[1].equals("ra")) {
						instName+="00110";
					}else if(arr[1].equals("sp")) {
						instName+="00111";
					}else if(arr[1].equals("fp")) {
						instName+="01000";
					}else if(arr[1].equals("gp")) {
						instName+="01001";
					}else if(arr[1].equals("t0")) {
						instName+="01010";
					}else if(arr[1].equals("t1")) {
						instName+="01011";
					}else if(arr[1].equals("t2")) {
						instName+="01100";
					}else if(arr[1].equals("t3")) {
						instName+="01101";
					}else if(arr[1].equals("s0")) {
						instName+="01110";
					}else if(arr[1].equals("s1")) {
						instName+="01111";
					}else if(arr[1].equals("s2")) {
						instName+="11111";
					}
				}
				else if (type.equals("O")) {
//					System.out.println("hna2");
					instName+="00000";
				}
				if(!type.equals("J")) {
					
//					System.out.println("hna3");
					if(arr[2].equals("Zero")) {
						instName+="00000";
					}
					else if(arr[2].equals("One")) {
						instName+="00001";
					}
					else if(arr[2].equals("v0")) {
						instName+="00010";
					}else if(arr[2].equals("v1")) {
						instName+="00011";
					}else if(arr[2].equals("a0")) {
						instName+="00100";
					}else if(arr[2].equals("a1")) {
						instName+="00101";
					}else if(arr[2].equals("ra")) {
						instName+="00110";
					}else if(arr[2].equals("sp")) {
						instName+="00111";
					}else if(arr[2].equals("fp")) {
						instName+="01000";
					}else if(arr[2].equals("gp")) {
						instName+="01001";
					}else if(arr[2].equals("t0")) {
						instName+="01010";
					}else if(arr[2].equals("t1")) {
						instName+="01011";
					}else if(arr[2].equals("t2")) {
						instName+="01100";
					}else if(arr[2].equals("t3")) {
						instName+="01101";
					}else if(arr[2].equals("s0")) {
						instName+="01110";
					}else if(arr[2].equals("s1")) {
						instName+="01111";
					}else if(arr[2].equals("s2")) {
						instName+="11111";
					}
				}
				if(type.equals("I")||type.equals("O")) {
//					System.out.println("hna4");
					String b=Integer.toBinaryString(Integer.parseInt(arr[3]));
					int n=9-b.length();
					String s="";
					for(int i=0;n>i;i++)
						s+="0";
					s=s+b;
//					System.out.println(s);
					instName+=s;
				}
				if(type.equals("R")) {
//					System.out.println("hna5");
					if(arr[3].equals("Zero")) {
						instName+="000000000";
					}
					else if(arr[3].equals("One")) {
						instName+="000010000";
					}
					else if(arr[3].equals("v0")) {
						instName+="000100000";
					}else if(arr[3].equals("v1")) {
						instName+="000110000";
					}else if(arr[3].equals("a0")) {
						instName+="001000000";
					}else if(arr[3].equals("a1")) {
						instName+="001010000";
					}else if(arr[3].equals("ra")) {
						instName+="001100000";
					}else if(arr[3].equals("sp")) {
						instName+="001110000";
					}else if(arr[3].equals("fp")) {
						instName+="010000000";
					}else if(arr[3].equals("gp")) {
						instName+="010010000";
					}else if(arr[3].equals("t0")) {
						instName+="010100000";
					}else if(arr[3].equals("t1")) {
						instName+="010110000";
					}else if(arr[3].equals("t2")) {
						instName+="011000000";
					}else if(arr[3].equals("t3")) {
						instName+="011010000";
					}else if(arr[3].equals("s0")) {
						instName+="011100000";
					}else if(arr[3].equals("s1")) {
						instName+="011110000";
					}else if(arr[3].equals("s2")) {
						instName+="111110000";
					}
					
				}
				if(type.equals("J")) {
//					System.out.println("hna6");
					String b=Integer.toBinaryString(Integer.parseInt(arr[1]));
					int n=19-b.length();
					String s="";
					for(int i=0;n>i;i++)
						s+="0";
					s=s+b;
					
					instName+=s;
				}
				
				
				

			}

			return instName;

		}
	
}
