package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name = "tasks")
@FilterDef(name="restrictToUser", parameters={
		@ParamDef( name="filteruserid", type="integer" )
})
@FilterDef(name="restrictToProject", parameters={
		@ParamDef( name="filterprojectid", type="integer" )
})
@Filters( {
    @Filter(name="restrictToUser", condition="userid = :filteruserid"),
    @Filter(name="restrictToProject", condition="projectid = :filterprojectid")
} )
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;

	@ManyToOne
	@JoinColumn(name = "projectid")
	protected Project project;

	@Column(name = "name")
	protected String name;

	@Column(name = "title")
	protected String title;

	@Enumerated(EnumType.STRING)
	@Column(name = "classtask", columnDefinition="ENUM('Task', 'Bug')", nullable = false)
	protected Classtask classtask;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition="ENUM('New', 'InProgress', 'OnReview', 'Closed')", nullable = false)
	protected Status status;

	@Enumerated(EnumType.STRING)
	@Column(name = "priority", columnDefinition="ENUM('High', 'Medium', 'Low')", nullable = false)
	protected Priority priority;

	@ManyToOne
	@JoinColumn(name = "userid")
	protected User user;

	@Column(name = "description")
	protected String description;

	public Task() {
		super();
	}
	
	public Task(Project project, String name, String title, Classtask classtask, Status status, Priority priority,
			User user, String description) {
		super();
		this.project = project;
		this.name = name;
		this.title = title;
		this.classtask = classtask;
		this.status = status;
		this.priority = priority;
		this.user = user;
		this.description = description;
	}

	public Task(int id, Project project, String name, String title, Classtask classtask, Status status,
			Priority priority, User user, String description) {
		super();
		this.id = id;
		this.project = project;
		this.name = name;
		this.title = title;
		this.classtask = classtask;
		this.status = status;
		this.priority = priority;
		this.user = user;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Classtask getClasstask() {
		return classtask;
	}

	public void setClasstask(Classtask classtask) {
		this.classtask = classtask;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}