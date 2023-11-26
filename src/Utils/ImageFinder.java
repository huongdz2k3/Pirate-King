package Utils;

import java.io.*;

public class ImageFinder {
	static String[] paths = {"assets/","./","../assets/","../","../../"};
	static public InputStream findAndOpenStreamCanNull(String name) {
		try{
			for(String i:paths) {
				File f=new File(i, name);
				if(f.exists())
					return new FileInputStream(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	                                              
		return null;
	}
	
	static public InputStream findAndOpenStream(String name) {
		try{
			for(String i:paths) {
				File f=new File(i, name);
				if(f.exists())
					return new FileInputStream(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("Cant find "+name+", tried:");
		for(String i:paths) {
			File f=new File(i, name);
			System.err.println("> "+f.getAbsolutePath());
		}
		                                              
		return null;
	}
	static public File findFileNotNull(String name) {
		for(String i:paths) {
			File f=new File(i, name);
			if(f.exists())
				return f;
		}
		System.err.println("Cant find "+name+", tried:");
		for(String i:paths) {
			File f=new File(i, name);
			System.err.println("> "+f.getAbsolutePath());
		}
		return null;
	}
}
