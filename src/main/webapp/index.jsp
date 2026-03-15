<%@ include file="taglib.jsp" %>

<c:set var="title" value="Inventory Service FO76" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<h2>Inventory Service FO76</h2>

<hr/>

<c:choose>
    <c:when test="${empty sessionScope.email}">
        <p>
            <a href="${pageContext.request.contextPath}/logIn">Log in</a>
        </p>
    </c:when>
    <c:otherwise>
        <p>Welcome ${sessionScope.email}</p>
    </c:otherwise>
</c:choose>

<hr/>

<ul>
    <li>
        <a href="${pageContext.request.contextPath}/viewUserArmorPieces">
            View User Armor Pieces
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/addUserArmorPiece">
            Add Armor Piece
        </a>
    </li>
</ul>

</body>
</html>