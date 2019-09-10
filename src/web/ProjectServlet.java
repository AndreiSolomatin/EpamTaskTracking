package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjectDao;
import model.Project;

@WebServlet("/projects")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao projectDao;

	public void init() {
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
			case "/newproject":
				showNewForm(request, response);
				break;
			case "/insertproject":
				insertProject(request, response);
				break;
			case "/deleteproject":
				deleteProject(request, response);
				break;
			case "/editproject":
				showEditForm(request, response);
				break;
			case "/updateproject":
				updateProject(request, response);
				break;
			default:
				listProject(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Project> listProject = projectDao.getAllProject();
		request.setAttribute("listProject", listProject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("project-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("project-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Project existingProject = projectDao.getProject(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("project-form.jsp");
		request.setAttribute("project", existingProject);
		dispatcher.forward(request, response);

	}

	private void insertProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		Project newProject = new Project(name);
		projectDao.saveProject(newProject);
		response.sendRedirect("listproject");
	}

	private void updateProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		Project project = new Project(id, name);
		projectDao.updateProject(project);
		response.sendRedirect("listproject");
	}

	private void deleteProject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		projectDao.deleteProject(id);
		response.sendRedirect("listproject");
	}
}