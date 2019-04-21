
public class register {
  String name;
  int  value;
 public register(int name) {
	this.name = Integer.toBinaryString(name);
	this.value=0;
 }
public String getName() {
	return name;
}

public int getValue() {
	return value;
}
public void setValue(int  value) {
	this.value = value;
}
@Override
public String toString() {
	return "register [name=" + name + ", value=" + value + "]";
}
 

  
}
