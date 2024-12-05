package MichaelSeleniumAutomation.data;



import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;


public class DataReader {
	//lesson 174
	public List<HashMap<String, String>> getJsonDatToMap() throws IOException {
		
		//Read Json to string
	String jsonContentInString =	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//MichaelSeleniumAutomation//data//PurchaseOrder.json") 
		,StandardCharsets.UTF_8	);
	
	// String to HashMap you need a dependency called Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data =mapper.readValue(jsonContentInString, new TypeReference<List<HashMap<String,String>>>(){});
	
	
	return data;
	}

}
