package com.example.demo;

public class Main {

	
	public static void main(String[] args) {
		System.out.println(format("{} {} {} {}", "a","b","c","d"));
	}
	
	
	
	
	
	public static final String format(String format, String... objects) {
		for(String obj : objects) {
			format = format.replaceFirst("\\{}", obj);
		}
		return format;
	}
	
}
