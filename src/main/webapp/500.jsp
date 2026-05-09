<%@ include file="taglib.jsp" %>

<c:set var="title" value="Server Error" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>Server Error</h2>
<p>Something went wrong on our end. Please try again later.</p>
<p><a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">Return Home</a></p>

</body>
</html>