<!-- import jstl library -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.toDoList.item,com.toDoList.itemDbUtil,java.util.List, java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>

	<title>ToDo List</title>
	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/00030c648e.js" crossorigin="anonymous"></script>
<style>
                .button {
        appearance: button;
        -moz-appearance: button;
        -webkit-appearance: button;
        text-decoration: none; 
        font: menu; 
        color: ButtonText;
        display: inline-block; 
        padding: 2px 8px;
    }
</style>
</head>

<body class="bg-light" style="text-align:center">
	<c:choose>
	    <c:when test="${empty userEmail}">
	    	<br>
	    	<br>
	    	<h4 style="text-align:center;color:red;">
	    		User is not logged in
	    		<br>
	    		Please 
	    		<a href="logIn.jsp">log in</a>
	    		 to See the Page Requested.
	    	</h4>
	    </c:when>    
	    <c:otherwise>
	    	<%
	    	
		    	response.setHeader("Cache-Control", "no-cache");
		    	response.setHeader("Cache-Control", "no-store");
		    	response.setHeader("Pragma", "no-cache");
		    	response.setDateHeader("Expires", 0);
	    	
				String uname = (String) session.getAttribute("userEmail");
				request.setAttribute("userName", uname.charAt(0));
			%>
	    	<form style="float : right " action="logOut" method="post">
    		<button type="submit" class="btn btn-outline-danger mt-2" title="${userEmail} &#013;&#010;Click to log out"><i class="fas fa-sign-out-alt"></i></button>
			</form>
			
			
			<form  action="addItems" method="post">
			<h1 style="text-align:center" class="mt-2"> Enter task info <i class="far fa-clipboard"></i></h1>
			<table style="margin-left:auto;margin-right:auto;">
				<tr>
					<td>Item label : <i class="far fa-clipboard"></td>
					<td>
						<input type="text" name="ItemLabel" class="form-control mt-2" placeholder="Enter label here to add" size="30" required/> 
					</td>
				</tr>
				<tr>
					<td>Item date : <i class="fas fa-calendar-alt"></td>
					<td>
						<input type="date" class="form-control mt-1" name="ItemDate" required/>
					</td>
				</tr>
				<tr>
					<td>Item time : <i class="far fa-clock"></td>
					<td>
						<input type="time" class="form-control mt-1" name="ItemTime" required/>
					</td>
				</tr>
				<tr>
				<td></td>
				<td>
					<input type="submit" class="btn btn-outline-dark mt-3" name="addTheItem" value="Add item">
					
				</td>
				</tr>
			</table>
			</form>
			
				<br>
				<!-- listing items here -->
				<h2 class="text-center">Your Items <i class="fas fa-tasks"></i></h2>
				
				
				<hr>
				<%
					
					itemDbUtil dbUtil = new itemDbUtil();
					
					String email = (String)session.getAttribute("userEmail");
					
					List <item> listItems = dbUtil.getItems(email);
					
					request.setAttribute("listItems", listItems);
				
				%>
				<table style="border:2px solid black;margin-left:auto;margin-right:auto;">
					<tr>
						<th style="padding:0 20px 0 20px;" ></th>
						<th style="padding:0 20px 0 20px;" >Label  <i class="far fa-clipboard"></i></i></th>
						<th style="padding:0 20px 0 20px;" >Date  <i class="fas fa-calendar-alt"></i></th>
						<th style="padding:0 20px 0 20px;" >Time  <i class="far fa-clock"></i></th>
					</tr>
					<c:forEach var="tempItem" items="${listItems}">
						<tr>
							<td style="padding:0 20px 0 20px; width: 144px; height: 40px" ><i class="fas fa-arrow-right"></i>
							<form action="SendEmail" method="post" style="width: 196px; height: 97px">
									<input type="hidden" name="sen" value="${tempItem.label}"/>
									<input type="submit" class="btn btn-outline-dark mt-3" name="Email" value="Send Email" style="height: 31px; width: 119px"/>
									
				</form>
							</td>
							<td style="padding:0 20px 0 20px;" >${tempItem.label}</td>
							<td style="padding:0 20px 0 20px;" >${tempItem.date}</td>
							<td style="padding:0 20px 0 20px;" >${tempItem.time}</td>

							<td style="padding:0 20px 0 20px; width: 125px" >
								<form  action="deleteItem" method="post" style="width: 137px; ">
									<input type="hidden" name="del" value="${tempItem.label}"/>
									<input type="submit" class="btn btn-outline-dark mb-2 btn-sm" value="Delete Item" style="height: 35px; width: 91px"/>
									
								</form>
								
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	
</body>

</html>