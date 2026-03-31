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
        <th>Date Added</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="piece" items="${pieces}">
        <tr>
            <td>${piece.armorType.typeName}</td>
            <td>${piece.armorSlot.slotName}</td>
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