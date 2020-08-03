package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ToolMain {
	
	public static void main(String[] args) throws Exception {
		test();
	}
	public static void test() throws URISyntaxException {
		TreeMap<Position,Position> map=new TreeMap<Position,Position>();
		ArrayList<Position> container=new ArrayList<Position>();
		container.add(new Position(10,10));
		container.add(new Position(20,10));
		container.add(new Position(30,10));
		container.add(new Position(40,10));
		for(Position p:container)
			map.put(p, p);
		Position result=map.get(new Position(30,10));
		System.out.println((result==null)? "no value":result.x+" "+result.y);
	}
	public static void moduleChecker() {
		String moduleName=ModuleFinder.of(Paths.get("json-simple-1.1.1.jar"))
				.findAll().stream().findFirst()
				.map(ModuleReference::descriptor)
				.map(ModuleDescriptor::name)
				.orElse(null);
		System.out.println(moduleName);
	}
	
	public static void modify() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser=new JSONParser();
		JSONObject map=(JSONObject)parser.parse(new FileReader("out.json"));
		
		int width=((Long) map.get("width")).intValue();
		int height=((Long) map.get("height")).intValue();
		JSONArray objLayer=new JSONArray();
		for(int j=0;j<height;j++)
		{
			for(int i=0;i<width;i++) {
				if(i==2&&j==1)
					objLayer.add(25);
				else
					objLayer.add(-1);
			}
		}
		map.put("objLayer",objLayer);
		PrintWriter out=new PrintWriter("out.json");
		out.println(map);
		out.flush();
		out.close();
		
	}
	

}
