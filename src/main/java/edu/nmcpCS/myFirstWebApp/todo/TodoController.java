package edu.nmcpCS.myFirstWebApp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("name")
public class TodoController {
	private TodoRepository todorepository;
	
	
	public TodoController(TodoRepository todorepository) {
		super();
		this.todorepository = todorepository;
	}


	@RequestMapping("todos")
	public String gotoTodos(ModelMap model) {
		List<Todo> todos= todorepository.findByUsername(getCurrentUserName());
		model.addAttribute("todos", todos);
		return "todo";
	}


	private String getCurrentUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String addTodosPage(ModelMap model) {
		Todo todo = new Todo(0,getCurrentUserName(),"Default Desc",LocalDate.now().plusYears(1),false);
		model.put("todo", todo);
		return "addTodo";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "addTodo";
		}
		todo.setUsername(getCurrentUserName());
		todorepository.save(todo);
		return "redirect:todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todorepository.deleteById(id);
		return "redirect:todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todorepository.findById(id).get();
		model.addAttribute("todo",todo);
		return "addTodo";
	}
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors())
			return "addTodo";
		String username = getCurrentUserName();
		todo.setUsername(username);
		todorepository.save(todo);
		return "redirect:todos";
	}	
}
