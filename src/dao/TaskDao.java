package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import model.Task;
import utl.HibernateUtil;

public class TaskDao {
	public void saveTask(Task task) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(task);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateTask(Task task) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(task);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteTask(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Task task = session.get(Task.class, id);
			if (task != null) {
				session.delete(task);
				System.out.println("task is deleted");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Task getTask(int id) {

		Transaction transaction = null;
		Task task = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			task = session.get(Task.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return task;
	}

	@SuppressWarnings("unchecked")
	public List<Task> getAllTask(int userfilter, int projectfilter) {

		Transaction transaction = null;
		List<Task> listOfTask = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			if (userfilter != 0) {
				Filter filter = session.enableFilter("restrictToUser");
				filter.setParameter("filteruserid", userfilter);
			}
			if (projectfilter != 0) {
				Filter filter = session.enableFilter("restrictToProject");
				filter.setParameter("filterprojectid", projectfilter);
			}
			transaction = session.beginTransaction();
			listOfTask = session.createQuery("from Task").getResultList();

			transaction.commit();
			session.disableFilter("restrictToUser");
			session.disableFilter("restrictToProject");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfTask;
	}
}