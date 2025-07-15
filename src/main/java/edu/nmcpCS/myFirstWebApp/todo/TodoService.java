package edu.nmcpCS.myFirstWebApp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount = 0;
	static {
		todos.add(new Todo(++todosCount, "Mohamed", "Learn Springboot", LocalDate.of(2025, 07, 10), false));
		todos.add(new Todo(++todosCount, "Mohamed", "Learn DSA", LocalDate.of(2025, 07, 20), false));
		todos.add(new Todo(++todosCount, "Mohamed", "others", LocalDate.of(2025, 07, 30), false));
	}
	public List<Todo> findByUsername(String username) {
		Predicate<Todo> predicate = t -> t.getUsername().equals(username);
		return todos.stream().filter(predicate).toList();
	}
	public void addNewTodo(String username, String description, LocalDate targetDate, boolean isDone) {
		++todosCount;
		Todo todo = new Todo(todosCount,username,description,targetDate, isDone);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}
	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId()==id;
		return todos.stream().filter(predicate).findFirst().get();
	}
	public void updateTodo(Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
