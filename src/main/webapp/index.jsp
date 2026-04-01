<%@ include file="taglib.jsp" %>

<c:set var="title" value="Inventory Service FO76" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<h2>Inventory Service FO76</h2>

<hr/>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <p>Please <a href="${pageContext.request.contextPath}/logIn">log in</a> to access your inventory.</p>
    </c:when>
    <c:otherwise>
        <p>Welcome, <strong>${sessionScope.user.email}</strong>!</p>

        <hr/>

        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/viewUserArmorPieces">
                    My Armor Pieces
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/addUserArmorPiece">
                    Add Armor Piece
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/viewLoadouts">
                    My Loadouts
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/addLoadout">
                    Add Loadout
                </a>
            </li>
        </ul>
    </c:otherwise>
</c:choose>

</body>
</html>