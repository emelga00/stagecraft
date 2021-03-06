package framework.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import framework.controller.Command;
import data.UserDB;


/***************************************************************************************
 * Class...................................................................User_Delete *
 * Author..........................................................................JLH *
 * ----------------------------------------------------------------------------------- *
 * This class deletes all of the user's data from the user table and the credential    *
 * table and then provides directs the nextview to the View_Users class.               *
 *                                                                                     *
 *    perform - required method from Command interface                                 *
 *                                                                                     *
 ***************************************************************************************/
public class User_Delete implements Command {

	@Override
	public String perform(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String user_ID = (String)request.getParameter("userID");
		String status = "User Not Deleted!";
		int userID = Integer.parseInt(user_ID);
		int result = UserDB.delUser(userID);
		if(result >0){
			status = "User Deleted!";
		}
		
		session.setAttribute("status", status);
		return "ViewUsers";
	}
}
