package controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import data.StudentDAO;

public class UpdateStudent extends Action{

	@Override
	public View doGet(HttpServletRequest request, HttpServletResponse response) {
		StudentDAO studentDAO = new StudentDAO();
		String studentID = request.getParameter("studentID");
		Student student = studentDAO.getStudent(studentID);
		request.setAttribute("student", student);
		View view = new View();
		view.setResponseType("FORWARD");
		view.setPage("/WEB-INF/views/studentForm.jsp");
		return view;
	}

	@Override
	public View doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		View view = new View();
		
		//(1)**** Retrieve Name-Value Pairs from Form Submission in HTTP Post Request ***
		String studentID = request.getParameter("studentID");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String age = request.getParameter("age");
		 
		//(2)**** Create a Data Transfer Object(bean) to encapsulate form data for    ****
		//******* sending to the Data Access Object (DAO) and load DTO with Form Data ****
		Student student = new Student();
		student.setStudentID(studentID);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setCity(city);
		student.setState(state);
		student.setAge(Integer.parseInt(age));
		
		//(3)**** Call Add Method in DAO Object and pass the Student Object  *******
		StudentDAO studentDAO = new StudentDAO();
		int result=studentDAO.updateStudent(student);
		if(result>0){
			session.setAttribute("message","Student Updated....");
		}else{
			session.setAttribute("message","Unable to Update Student....");
		}
		view.setResponseType("REDIRECT");
		view.setPage("admin");
		return view;
	}

}
