<html>
<body>
<h2>Hello World!</h2>

<form action="." method="get">
	<input name="helloText" value="<%= request.getParameter("helloText") %>" />
	<input type="submit" value="Send!" />
</form>

</body>
</html>