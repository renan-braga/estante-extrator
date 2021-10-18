package main.java.utils;

public class Resizer
{
	public static String resize(String token, String height, String img) {
		return "http://" + token + ".cloudimg.io/v7/" + img + "?height=" + height;
	}
}


