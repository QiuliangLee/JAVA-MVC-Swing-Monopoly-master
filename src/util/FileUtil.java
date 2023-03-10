package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;


public class FileUtil {

	private static final String FILE = "file/";
	private static final String IMAGE = "images/";
	private static final String AUDIO = "music/";
	private static final HashMap<String, String> MAP = new HashMap<String, String>();
	static {
		MAP.put("file", FILE);
		MAP.put("image", IMAGE);
		MAP.put("audio", AUDIO);
	}
	
	
    public static URL getURL(String type, String path) {
    	String dir = MAP.get(type);
    	return URLClassLoader.getSystemClassLoader().getResource(dir + path);
    }
    

    public static File getFile(String path) {
    	URL url = getURL("file", path);
    	if (url == null) {
    		return null;
    	}
    	return new File(url.getFile());
    }
    

    public static Image getImage(String path) {
    	URL url = getURL("image", path);
    	if(url == null) {
    		return null;
    	}
    	return Toolkit.getDefaultToolkit().getImage(url);
    }
    

    public static AudioClip getAudio(String path) {
    	URL url = getURL("audio", path);
    	if(url == null) {
    		return null;
    	}
    	return Applet.newAudioClip(url);
    }
    
}
