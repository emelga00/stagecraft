package controller.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import data.StudentDAO;

public class Admin extends Action {

	@Override
	public View doGet(HttpServletRequest request, HttpServletResponse response) {
		View view = new View();
		ArrayList<Student> students;
		StudentDAO studentDAO = new StudentDAO();
		students = studentDAO.getStudents();
		request.setAttribute("students", students);
		
		view.setResponseType("FORWARD");
		view.setPage("/WEB-INF/views/admin.jsp");
		return view;
	}

	@Override
	public View doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		View view = new View();
		String submit = request.getParameter("submit");
		String studentID = request.getParameter("studentID");
		
		if(studentID==null){
			session.setAttribute("message","Click on a Radio Button to Select a Student" );
			view.setResponseType("REDIRECT");
			view.setPage("admin");
		}else{
			if(submit.equalsIgnoreCase("Remove Student")){
				StudentDAO studentDAO = new StudentDAO();
				int result = studentDAO.deleteStudent(studentID);
				if(result>0){
					session.setAttribute("message","Student Removed....");
				}else{
					session.setAttribute("message","Unable to Remove Student....");
				}
				view.setResponseType("REDIRECT");
				view.setPage("admin");
			}else{
				view.setResponseType("REDIRECT");
				view.setPage("updateStudent?studentID=" + studentID);			
			}
		}
		return view;
	}

}
