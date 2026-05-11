<%@ include file="taglib.jsp" %>

<c:set var="title" value="User Armor Pieces" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>My Armor Pieces</h2>

<c:if test="${not empty sessionScope.flashMessage}">
    <div class="alert alert-success alert-dismissible">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
            ${sessionScope.flashMessage}
    </div>
    <c:remove var="flashMessage" scope="session" />
</c:if>

<a class="btn btn-primary" style="margin-bottom: 10px;" href="${pageContext.request.contextPath}/addUserArmorPiece">Add Armor Piece</a>

<table id="armorTable" class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>Armor Type</th>
        <th>Weight Class</th>
        <th>Armor Slot</th>
        <th>DR</th>
        <th>ER</th>
        <th>RR</th>
        <th>PR</th>
        <th>FR</th>
        <th>CR</th>
        <th>&#9733; 1-Star</th>
        <th>&#9733;&#9733; 2-Star</th>
        <th>&#9733;&#9733;&#9733; 3-Star</th>
        <th>&#9733;&#9733;&#9733;&#9733; 4-Star</th>
        <th>Date Added</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="piece" items="${pieces}">
        <%-- Find the resistance row matching this piece's slot group --%>
        <c:set var="resistance" value="${null}" />
        <c:forEach var="res" items="${piece.armorType.baseResistances}">
            <c:if test="${res.id.slotGroup == piece.armorSlot.slotGroup}">
                <c:set var="resistance" value="${res}" />
            </c:if>
        </c:forEach>
        <tr>
            <td>${piece.armorType.typeName}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty piece.armorType.weightClass}">
                        ${piece.armorType.weightClass}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>${piece.armorSlot.slotName}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty resistance}">${resistance.damageResistance}</c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty resistance}">${resistance.energyResistance}</c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty resistance}">${resistance.radiationResistance}</c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty resistance}">${resistance.poisonResistance}</c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty resistance}">${resistance.fireResistance}</c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty resistance}">${resistance.cryoResistance}</c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty piece.star1Effect}">
                        <span title="${piece.star1Effect.description}">${piece.star1Effect.name}</span>
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty piece.star2Effect}">
                        <span title="${piece.star2Effect.description}">${piece.star2Effect.name}</span>
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty piece.star3Effect}">
                        <span title="${piece.star3Effect.description}">${piece.star3Effect.name}</span>
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty piece.star4Effect}">
                        <span title="${piece.star4Effect.description}">${piece.star4Effect.name}</span>
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td>${piece.formattedCreatedAt}</td>
            <td>
                <a class="btn btn-warning btn-xs"
                   href="${pageContext.request.contextPath}/editUserArmorPiece?id=${piece.id}">
                    Edit
                </a>
                <a class="btn btn-danger btn-xs"
                   href="${pageContext.request.contextPath}/deleteUserArmorPiece?id=${piece.id}"
                   onclick="return confirm('Delete this armor piece?');">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    $(document).ready(function() {
        $('#armorTable').DataTable();
    });
</script>

</body>
</html>