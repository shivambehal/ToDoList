/**
 * 
 */
package com.toDoList;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author gursh
 *
 */
@WebServlet("/contact")
public class Contactus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doMethod(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String name = request.getParameter("firstname");
		String email = request.getParameter("Email");
		String mail = request.getParameter("Details");
		
		
		
		String [] recepients =new String[]{email};
		String [] bccRecepients =new String[]{"TodoListJavaProject@gmail.com"};
		System.out.println("in email");
		new SendEmail().sendMail(recepients, bccRecepients, email, mail);
		response.sendRedirect("welcome.jsp");
	
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
