
public class register {
  int number;
  String value;
 public register(int number,String value) {
	this.number = number;
	this.value = "00000";
 }
public int getNumber() {
	return number;
}

public int getValue() {
	return Integer.parseInt(value,2);
}
public void setValue(String value) {
	this.value = value;
}
 

  
}
