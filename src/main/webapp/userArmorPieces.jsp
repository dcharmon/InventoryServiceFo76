<%@ include file="taglib.jsp" %>

<c:set var="title" value="User Armor Pieces" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<h2>User Armor Pieces</h2>

<table id="armorTable" class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Armor Type ID</th>
        <th>Armor Slot ID</th>
        <th>Created At</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="piece" items="${pieces}">
        <tr>
            <td>${piece.id}</td>
            <td>${piece.userId}</td>
            <td>${piece.armorTypeId}</td>
            <td>${piece.armorSlotId}</td>
            <td>${piece.createdAt}</td>
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


