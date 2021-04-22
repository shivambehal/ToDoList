package com.toDoList;

import org.apache.commons.lang3.StringUtils;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String one = "Test";
		String two = "10-05-2020";
		String three = "10:05";
		
		String mail = "Label\t"+"Date\t"+"Time\n"+one+"\t"+two+"\t"+three;
		System.out.println(mail);
		String format = String.format("Label %s", one + two+ three  );
		System.out.println(format);
		System.out.println(String.format("%-" + 20 + "s", mail));
		
		String f = String.format("%-" + 10 + "s", one) + String.format("%" + 10 + "s", two) + String.format("%"+ 10 + "s",three);
		System.out.println(f);
	}

}
