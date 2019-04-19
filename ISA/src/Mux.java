
public class Mux {
 int x;
 int y;
public Mux(int x, int y) {
	super();
	this.x = x;
	this.y = y;
}
public int getValue(int s){
	return (s==0)?x:y;
}
 
 
 
}
