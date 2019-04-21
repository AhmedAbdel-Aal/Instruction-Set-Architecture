import java.io.Serializable;
import java.util.Hashtable;

public class PipelineFile implements Serializable{
 Hashtable<String,Object> info ;
 
 
public PipelineFile() {
	 info = new Hashtable<String,Object>();

}

public void add(String s, Object o){
	info.put(s, o);
//	System.out.println(info.toString());
}
public Object get(String s){
	return info.get(s);
}

public void clear(){
	this.info.clear();
}

public  void clone(PipelineFile p ){
	this.info = (Hashtable<String, Object>) p.info.clone();
}



  
 
}
