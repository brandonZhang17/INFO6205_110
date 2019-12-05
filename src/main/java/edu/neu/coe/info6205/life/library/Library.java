package edu.neu.coe.info6205.life.library;

import java.util.HashMap;
import java.util.Map;

public class Library {

		final public static String Blip = "0 0";

		final public static String Blip2 = "0 0, 1 0";

		final public static String Block = "1 1, 1 2, 2 2, 2 1";

		final public static String Beehive = "1 2, 2 1, 3 1, 4 2, 3 3, 2 3";

		final public static String Loaf = "1 3, 2 4, 3 4, 4 3, 4 2, 3 1, 2 2";

		final public static String Blinker = "0 -1, 0 0, 0 1";

		final public static String Glider1 = "0 0, 1 0, 2 0, 2 -1, 1 -2";

		final public static String Glider2 = "2 0, 1 0, 0 0, 0 -1, 1 -2";

		final public static String Glider3 = "0 0, 1 0, 2 0, 2 1, 1 2";
		final public static String Test = "10 8 ,3 -5 ,10 5 ,-1 1 ,-3 2 ,-5 -6 ,3 -4 ,-10 2 ,-4 -6 ,10 8 ,9 -5 ,6 4 ,9 -3 ,-10 5 ,7 6 ,3 3 ,-4 2 ,-2 -6 ,9 9 ,-6 -1 ,4 10 ,5 10 ,5 5 ,1 10 ,5 -4 ,10 9 ,-10 -2 ,1 -10 ,9 -2 ,-2 -9 ,4 -6 ,7 8 ,-2 4 ,7 -2 ,10 -8 ,5 -4 ,-8 1 ,-9 -6 ,9 -7 ,4 0 ,-5 -6 ,7 5 ,6 3 ,5 -2 ,-1 -9 ,-9 -4";

		final public static Map<String, String> map = new HashMap<>();

		public static String get(String key) {
				return map.get(key.toLowerCase());
		}

		public static String put(String key, String value) {
				return map.put(key.toLowerCase(), value);
		}

		static {
				put("Blip", Blip);
				put("Blip2", Blip2);
				put("Block", Block);
				put("Beehive", Beehive);
				put("Loaf", Loaf);
				put("Blinker", Blinker);
				put("Glider1", Glider1);
				put("Glider2", Glider2);
				put("Glider3", Glider3);
				put("Test", Test);
				
		}
}
