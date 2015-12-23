package com.util;

public class Test {
	public static void main(String[] args) {
		String ss="无图片，有回车\\r\\n无图片，\\r\\n有回车";
		System.out.println(ss);
		ss=ss.replace("\\r\\n", "<br/>");
		System.out.println(ss);
	}
}
