<%@ include file="taglib.jsp" %>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">FO76 Inventory</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/viewLegendaryEffects">Legendary Effects</a></li>
            <c:if test="${not empty sessionScope.user}">
                <li><a href="${pageContext.request.contextPath}/viewUserArmorPieces">My Armor</a></li>
                <li><a href="${pageContext.request.contextPath}/addUserArmorPiece">Add Armor</a></li>
                <li><a href="${pageContext.request.contextPath}/viewLoadouts">My Loadouts</a></li>
                <li><a href="${pageContext.request.contextPath}/addLoadout">Add Loadout</a></li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/logOut">Sign Out</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/logIn">Sign In</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>