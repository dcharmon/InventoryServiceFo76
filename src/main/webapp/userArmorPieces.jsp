<%@ include file="taglib.jsp" %>

<c:set var="title" value="User Armor Pieces" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<h2>My Armor Pieces</h2>

<table id="armorTable" class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>Armor Type</th>
        <th>Armor Slot</th>
        <th>★ 1-Star</th>
        <th>★★ 2-Star</th>
        <th>★★★ 3-Star</th>
        <th>★★★★ 4-Star</th>
        <th>Date Added</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="piece" items="${pieces}">
        <tr>
            <td>${piece.armorType.typeName}</td>
            <td>${piece.armorSlot.slotName}</td>
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
            <td>${piece.createdAt}</td>
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