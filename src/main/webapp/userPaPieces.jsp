<%@ include file="taglib.jsp" %>

<c:set var="title" value="My PA Pieces" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>My Power Armor Pieces</h2>

<c:if test="${not empty sessionScope.flashMessage}">
  <div class="alert alert-success alert-dismissible">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
      ${sessionScope.flashMessage}
  </div>
  <c:remove var="flashMessage" scope="session" />
</c:if>

<a class="btn btn-primary" href="${pageContext.request.contextPath}/addUserPaPiece">Add PA Piece</a>
<a class="btn btn-default" href="${pageContext.request.contextPath}/addUserPaFrame">Add Frame</a>

<%-- Frames section --%>
<c:choose>
  <c:when test="${empty frames}">
    <p class="mt-3">You have no frames yet.</p>
  </c:when>
  <c:otherwise>
    <h3 class="mt-3">Frames</h3>
    <c:forEach var="frame" items="${frames}">
      <div class="panel panel-default mt-3">
        <div class="panel-heading">
          <h4 class="panel-title">
            <c:choose>
              <c:when test="${not empty frame.frameName}">${frame.frameName}</c:when>
              <c:otherwise>Frame #${frame.id}</c:otherwise>
            </c:choose>
            <a class="btn btn-warning btn-xs pull-right"
               href="${pageContext.request.contextPath}/editUserPaFrame?id=${frame.id}">
              Edit
            </a>
            <a class="btn btn-danger btn-xs pull-right"
               href="${pageContext.request.contextPath}/deleteUserPaFrame?id=${frame.id}"
               onclick="return confirm('Delete this frame? Pieces will remain as unassigned.');">
              Delete
            </a>
          </h4>
        </div>
        <div class="panel-body">
          <c:choose>
            <c:when test="${empty frame.pieces}">
              <p>No pieces on this frame.</p>
            </c:when>
            <c:otherwise>
              <table class="table table-striped table-bordered">
                <thead>
                <tr>
                  <th>PA Type</th>
                  <th>Slot</th>
                  <th>DR</th><th>ER</th><th>RR</th><th>PR</th><th>FR</th><th>CR</th>
                  <th>&#9733; 1-Star</th>
                  <th>&#9733;&#9733; 2-Star</th>
                  <th>&#9733;&#9733;&#9733; 3-Star</th>
                  <th>&#9733;&#9733;&#9733;&#9733; 4-Star</th>
                  <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="piece" items="${frame.pieces}">
                  <c:set var="resistance" value="${null}" />
                  <c:forEach var="res" items="${piece.paType.baseResistances}">
                    <c:if test="${res.id.paSlotId == piece.paSlot.id}">
                      <c:set var="resistance" value="${res}" />
                    </c:if>
                  </c:forEach>
                  <tr>
                    <td>${piece.paType.typeName}</td>
                    <td>${piece.paSlot.slotName}</td>
                    <td><c:choose><c:when test="${not empty resistance}">${resistance.damageResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty resistance}">${resistance.energyResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty resistance}">${resistance.radiationResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty resistance}">${resistance.poisonResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty resistance}">${resistance.fireResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty resistance}">${resistance.cryoResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty piece.star1Effect}"><span title="${piece.star1Effect.description}">${piece.star1Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty piece.star2Effect}"><span title="${piece.star2Effect.description}">${piece.star2Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty piece.star3Effect}"><span title="${piece.star3Effect.description}">${piece.star3Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td><c:choose><c:when test="${not empty piece.star4Effect}"><span title="${piece.star4Effect.description}">${piece.star4Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
                    <td>
                      <a class="btn btn-warning btn-xs"
                         href="${pageContext.request.contextPath}/editUserPaPiece?id=${piece.id}">Edit</a>
                      <a class="btn btn-danger btn-xs"
                         href="${pageContext.request.contextPath}/deleteUserPaPiece?id=${piece.id}"
                         onclick="return confirm('Delete this PA piece?');">Delete</a>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </c:forEach>
  </c:otherwise>
</c:choose>

<%-- Unassigned pieces section --%>
<h3 class="mt-3">Unassigned Pieces</h3>
<c:set var="hasUnassigned" value="false" />
<c:forEach var="piece" items="${pieces}">
  <c:if test="${empty piece.paFrame}">
    <c:set var="hasUnassigned" value="true" />
  </c:if>
</c:forEach>

<c:choose>
  <c:when test="${not hasUnassigned}">
    <p>No unassigned pieces.</p>
  </c:when>
  <c:otherwise>
    <table id="paTable" class="table table-striped table-bordered">
      <thead>
      <tr>
        <th>PA Type</th>
        <th>Slot</th>
        <th>DR</th><th>ER</th><th>RR</th><th>PR</th><th>FR</th><th>CR</th>
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
        <c:if test="${empty piece.paFrame}">
          <c:set var="resistance" value="${null}" />
          <c:forEach var="res" items="${piece.paType.baseResistances}">
            <c:if test="${res.id.paSlotId == piece.paSlot.id}">
              <c:set var="resistance" value="${res}" />
            </c:if>
          </c:forEach>
          <tr>
            <td>${piece.paType.typeName}</td>
            <td>${piece.paSlot.slotName}</td>
            <td><c:choose><c:when test="${not empty resistance}">${resistance.damageResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty resistance}">${resistance.energyResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty resistance}">${resistance.radiationResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty resistance}">${resistance.poisonResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty resistance}">${resistance.fireResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty resistance}">${resistance.cryoResistance}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty piece.star1Effect}"><span title="${piece.star1Effect.description}">${piece.star1Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty piece.star2Effect}"><span title="${piece.star2Effect.description}">${piece.star2Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty piece.star3Effect}"><span title="${piece.star3Effect.description}">${piece.star3Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty piece.star4Effect}"><span title="${piece.star4Effect.description}">${piece.star4Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td>${piece.formattedCreatedAt}</td>
            <td>
              <a class="btn btn-warning btn-xs"
                 href="${pageContext.request.contextPath}/editUserPaPiece?id=${piece.id}">Edit</a>
              <a class="btn btn-danger btn-xs"
                 href="${pageContext.request.contextPath}/deleteUserPaPiece?id=${piece.id}"
                 onclick="return confirm('Delete this PA piece?');">Delete</a>
            </td>
          </tr>
        </c:if>
      </c:forEach>
      </tbody>
    </table>
    <script>
      $(document).ready(function() {
        $('#paTable').DataTable();
      });
    </script>
  </c:otherwise>
</c:choose>

</body>
</html>
