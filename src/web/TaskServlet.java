package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjectDao;
import dao.TaskDao;
import dao.UserDao;
import model.Classtask;
import model.Priority;
import model.Project;
import model.Status;
import model.Task;
import model.User;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDao taskDao;
	private UserDao userDao;
	private ProjectDao projectDao;
	private int userfilterid = 0;
	private int projectfilterid = 0;

	public void init() {
		taskDao = new TaskDao();
		userDao = new UserDao();
		projectDao = new ProjectDao();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/newtask":
				showNewForm(request, response);
				break;
			case "/inserttask":
				insertTask(request, response);
				break;
			case "/deletetask":
				deleteTask(request, response);
				break;
			case "/edittask":
				showEditForm(request, response);
				break;
			case "/updatetask":
				updateTask(request, response);
				break;
			case "/filtertask":
				filterTask(request, response);
				listTask(request, response);
				break;
			case "/cleanfiltertask":
				cleanFilterTask(request, response);
				listTask(request, response);
				break;
			default:
				listTask(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void cleanFilterTask(HttpServletRequest request, HttpServletResponse response) {
		userfilterid = 0;
		projectfilterid = 0;
		request.setAttribute("selectedUserFilter", userfilterid);
		request.setAttribute("selectedProjectFilter", projectfilterid);
	}

	private void filterTask(HttpServletRequest request, HttpServletResponse response) {
		userfilterid = Integer.parseInt(request.getParameter("userfilterdid"));
		projectfilterid = Integer.parseInt(request.getParameter("projectfilterid"));
	}

	private void listTask(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> users = userDao.getAllUser();
		users.add(0, new User(0, ""));
		List<Project> projects = projectDao.getAllProject();
		projects.add(0, new Project(0, ""));
		
		request.setAttribute("userfilterdList", users);
		request.setAttribute("projectfilterList", projects);
		if(userfilterid != 0) {
			request.setAttribute("selectedUserFilter", userfilterid);
		}
		if(projectfilterid != 0) {
			request.setAttribute("selectedProjectFilter", projectfilterid);
		}
		
		List<Task> listTask = taskDao.getAllTask(userfilterid, projectfilterid);
		request.setAttribute("listTask", listTask);
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		feelSelects(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Task existingTask = taskDao.getTask(id);
		int existingProjectId = existingTask.getProject().getId();
		int existingUserId = existingTask.getUser().getId();
		String existingClasstask = existingTask.getClasstask().toString();
		String existingStatus = existingTask.getStatus().toString();
		String existingPriority = existingTask.getPriority().toString();
		
		request.setAttribute("selectedProject", existingProjectId);
		request.setAttribute("selectedUser", existingUserId);
		request.setAttribute("selectedClasstask", existingClasstask);
		request.setAttribute("selectedStatus", existingStatus);
		request.setAttribute("selectedPriority", existingPriority);
		
		feelSelects(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
		request.setAttribute("task", existingTask);
		dispatcher.forward(request, response);

	}
	
	private void feelSelects(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = userDao.getAllUser();
		List<Project> projects = projectDao.getAllProject();
		List<Classtask> classtasks = Arrays.asList(Classtask.values());
		List<Status> statuses = Arrays.asList(Status.values());
		List<Priority> priorites = Arrays.asList(Priority.values());
		request.setAttribute("usersList", users);
		request.setAttribute("projectsList", projects);
		request.setAttribute("classtasksList", classtasks);
		request.setAttribute("statusesList", statuses);
		request.setAttribute("prioritesList", priorites);
	}

	private void insertTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int projectid = Integer.parseInt(request.getParameter("projectid"));
		Project project = projectDao.getProject(projectid);
		
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		Classtask classtask = Classtask.valueOf(request.getParameter("classtask"));
		Status status = Status.valueOf(request.getParameter("status"));
		Priority priority = Priority.valueOf(request.getParameter("priority"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		User user = userDao.getUser(userid);
		
		String description = request.getParameter("description");
		
		Task newTask = new Task(project, name, title, classtask, status, priority, user, description);
		taskDao.saveTask(newTask);
		response.sendRedirect("listtask");
	}

	private void updateTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int projectid = Integer.parseInt(request.getParameter("projectid"));
		Project project = projectDao.getProject(projectid);
		
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		Classtask classtask = Classtask.valueOf(request.getParameter("classtask"));
		Status status = Status.valueOf(request.getParameter("status"));
		Priority priority = Priority.valueOf(request.getParameter("priority"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		User user = userDao.getUser(userid);
		
		String description = request.getParameter("description");

		Task task = new Task(id, project, name, title, classtask, status, priority, user, description);;
		taskDao.updateTask(task);
		response.sendRedirect("listtask");
	}

	private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		taskDao.deleteTask(id);
		response.sendRedirect("listtask");
	}
}