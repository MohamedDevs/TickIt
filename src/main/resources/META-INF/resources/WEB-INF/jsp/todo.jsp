	<%@ include file="common/header.jspf" %>
	<%@ include file="common/navigation.jspf" %>
	<div class="container">
    Hello ${name}!
    <h1>List of Todos</h1>
    <table class="table">
      <thead>
        <tr>
          <th>Description</th>
          <th>Target Date</th>
          <th>Done</th>
		  <th></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="todo" items="${todos}">
          <tr>
            <td>${todo.description}</td>
            <td>${todo.targetDate}</td>
            <td>${todo.isDone}</td>
			<td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">DELETE</td>
			<td><a href="update-todo?id=${todo.id}" class="btn">UPDATE</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
	<a href="add-todo" class="btn btn-success">add Todo</a>
	</div>
	<%@ include file="common/footer.jspf" %>