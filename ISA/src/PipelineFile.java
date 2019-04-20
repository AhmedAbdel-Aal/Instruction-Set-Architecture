import java.io.Serializable;
import java.util.Hashtable;

public class PipelineFile implements Serializable{
 String filepath  ;
 Hashtable<String,Object> info = new Hashtable<String,Object>();
public PipelineFile(String filepath) {
	this.filepath = filepath;
}

public void adjustInstruction(String name) {
	this.filepath+="/"+name;
}
 
  
 
}
