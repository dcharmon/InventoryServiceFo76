<%@ include file="taglib.jsp" %>

<c:set var="title" value="Page Not Found" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>Page Not Found</h2>
<p>The page you were looking for doesn't exist.</p>
<p><a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">Return Home</a></p>

</body>
</html>