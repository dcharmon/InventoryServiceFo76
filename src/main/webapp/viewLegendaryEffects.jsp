<%@ include file="taglib.jsp" %>

<c:set var="title" value="Legendary Effects" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<h2>Legendary Effects</h2>

<hr/>

<div>
    <a href="${pageContext.request.contextPath}/viewLegendaryEffects"
       class="btn ${empty selectedStar ? 'btn-primary' : 'btn-default'}">All</a>
    <a href="${pageContext.request.contextPath}/viewLegendaryEffects?star=1"
       class="btn ${selectedStar == '1' ? 'btn-primary' : 'btn-default'}">&#9733; 1-Star</a>
    <a href="${pageContext.request.contextPath}/viewLegendaryEffects?star=2"
       class="btn ${selectedStar == '2' ? 'btn-primary' : 'btn-default'}">&#9733;&#9733; 2-Star</a>
    <a href="${pageContext.request.contextPath}/viewLegendaryEffects?star=3"
       class="btn ${selectedStar == '3' ? 'btn-primary' : 'btn-default'}">&#9733;&#9733;&#9733; 3-Star</a>
    <a href="${pageContext.request.contextPath}/viewLegendaryEffects?star=4"
       class="btn ${selectedStar == '4' ? 'btn-primary' : 'btn-default'}">&#9733;&#9733;&#9733;&#9733; 4-Star</a>
</div>

<br/>

<c:choose>
    <c:when test="${empty effects}">
        <p>No legendary effects found.</p>
    </c:when>
    <c:otherwise>
        <table id="effectsTable" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>&#9733; Star</th>
                <th>Category</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="effect" items="${effects}">
                <tr>
                    <td>${effect.name}</td>
                    <td>${effect.star}</td>
                    <td>${effect.armorCategory}</td>
                    <td>${effect.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<script>
    $(document).ready(function() {
        $('#effectsTable').DataTable();
    });
</script>

<p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>

</body>
</html>