<%@ include file="taglib.jsp" %>

<c:set var="title" value="Edit PA Frame" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>Edit Power Armor Frame</h2>

<form class="form" method="post" action="${pageContext.request.contextPath}/editUserPaFrame">

    <input type="hidden" name="id" value="${frame.id}" />

    <div class="form-group">
        <label for="frameName">Frame Name (optional)</label>
        <input class="form-control" type="text" id="frameName" name="frameName"
               value="${frame.frameName}" placeholder="e.g. Ultracite Main, Farm Build" />
    </div>

    <button class="btn btn-primary" type="submit">Save</button>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/viewUserPaPieces">Cancel</a>

</form>

</body>
</html>
