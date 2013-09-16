package controller.actions;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Student;
import data.StudentDAO;

public class Index extends Action {

	@Override
	public View doGet(HttpServletRequest request, HttpServletResponse response) {
		View view = new View();
		
		ArrayList<Student> students;
		StudentDAO studentDAO = new StudentDAO();
		students = studentDAO.getStudents();
		request.setAttribute("students", students);
		
		view.setResponseType("FORWARD");
		view.setPage("/WEB-INF/views/index.jsp");
		return view;
		
	}

	@Override
	public View doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isAuthorized(HttpServletRequest request){
		return true;
	}

}
