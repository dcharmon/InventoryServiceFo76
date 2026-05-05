<%@ include file="taglib.jsp" %>

<c:set var="title" value="Inventory Service FO76" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<div class="jumbotron">
    <h1>FO76 Inventory</h1>
    <p>Track your Fallout 76 armor pieces and loadouts.</p>
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/logIn" class="btn btn-primary btn-lg">Sign In</a>
    </c:if>
</div>

<c:if test="${not empty sessionScope.user}">
    <p>Welcome back, <strong>${sessionScope.user.email}</strong>!</p>
</c:if>

<h3>Reference Data</h3>
<ul>
    <li><a href="${pageContext.request.contextPath}/viewLegendaryEffects">Legendary Effects</a></li>
</ul>

</body>
</html>