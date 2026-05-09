<%@ include file="taglib.jsp" %>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">FO76 Inventory</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/viewLegendaryEffects">Legendary Effects</a></li>
            <c:if test="${not empty sessionScope.user}">

                <%-- Armor dropdown --%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">
                        Armor <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/viewUserArmorPieces">View My Armor</a></li>
                        <li><a href="${pageContext.request.contextPath}/addUserArmorPiece">Add Armor Piece</a></li>
                    </ul>
                </li>

                <%-- Power Armor dropdown --%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">
                        Power Armor <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/viewUserPaPieces">View My PA</a></li>
                        <li><a href="${pageContext.request.contextPath}/addUserPaPiece">Add PA Piece</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/addUserPaFrame">Add Frame</a></li>
                    </ul>
                </li>

                <%-- Loadouts dropdown --%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">
                        Loadouts <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/viewLoadouts">View Loadouts</a></li>
                        <li><a href="${pageContext.request.contextPath}/addLoadout">Add Loadout</a></li>
                    </ul>
                </li>

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
