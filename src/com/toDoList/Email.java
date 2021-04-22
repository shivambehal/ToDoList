package com.toDoList;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
/**
 * Servlet implementation class deleteItem
 */
@WebServlet("/SendEmail")
public class Email extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doMethod(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		itemDbUtil dbUtil = new itemDbUtil();
		String email = (String) session.getAttribute("userEmail");
		//List<String> items = new ArrayList<>();
		//items = dbUtil.getItems(email);
		//StringBuilder b = new StringBuilder();
		//for(Object iteams : items)
		 // b.append(iteams).append("\n");

		//String carriersString = b.toString();
		//System.out.println(carriersString);
		
		String del = request.getParameter("sen");
		String carriersString = dbUtil.forEmail(email,del);
		
		String [] recepients =new String[]{email};
		String [] bccRecepients =new String[]{"TodoListJavaProject@gmail.com"};
		System.out.println("in email");
		new SendEmail().sendMail(recepients, bccRecepients, email, carriersString);
		response.sendRedirect("homePage.jsp");
	
	} 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try {
			doMethod(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try {
			doMethod(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
