<%@ include file="taglib.jsp" %>

<c:set var="title" value="Add PA Piece" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>Add Power Armor Piece</h2>

<form class="form" method="post" action="${pageContext.request.contextPath}/addUserPaPiece">

  <c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">${errorMessage}</div>
  </c:if>

  <div class="form-group">
    <label for="paTypeId">PA Type</label>
    <select class="form-control" id="paTypeId" name="paTypeId">
      <c:forEach var="type" items="${paTypes}">
        <option value="${type.id}">${type.typeName}</option>
      </c:forEach>
    </select>
  </div>

  <div class="form-group">
    <label for="paSlotId">PA Slot</label>
    <select class="form-control" id="paSlotId" name="paSlotId">
      <c:forEach var="slot" items="${paSlots}">
        <option value="${slot.id}" data-allows-legendary="${slot.allowsLegendary}">${slot.slotName}</option>
      </c:forEach>
    </select>
  </div>

  <div class="form-group">
    <label for="paFrameId">Assign to Frame (optional)</label>
    <select class="form-control" id="paFrameId" name="paFrameId">
      <option value="">-- Unassigned --</option>
      <c:forEach var="frame" items="${userFrames}">
        <option value="${frame.id}">
          <c:choose>
            <c:when test="${not empty frame.frameName}">${frame.frameName}</c:when>
            <c:otherwise>Frame #${frame.id}</c:otherwise>
          </c:choose>
        </option>
      </c:forEach>
    </select>
  </div>

  <div id="legendarySection">

    <div class="form-group">
      <label for="star1EffectId">&#9733; 1-Star Effect (optional)</label>
      <select class="form-control" id="star1EffectId" name="star1EffectId">
        <option value="">-- None --</option>
        <c:forEach var="effect" items="${star1Effects}">
          <option value="${effect.id}">${effect.name}</option>
        </c:forEach>
      </select>
    </div>

    <div class="form-group">
      <label for="star2EffectId">&#9733;&#9733; 2-Star Effect (optional)</label>
      <select class="form-control" id="star2EffectId" name="star2EffectId" disabled>
        <option value="">-- None --</option>
        <c:forEach var="effect" items="${star2Effects}">
          <option value="${effect.id}">${effect.name}</option>
        </c:forEach>
      </select>
    </div>

    <div class="form-group">
      <label for="star3EffectId">&#9733;&#9733;&#9733; 3-Star Effect (optional)</label>
      <select class="form-control" id="star3EffectId" name="star3EffectId" disabled>
        <option value="">-- None --</option>
        <c:forEach var="effect" items="${star3Effects}">
          <option value="${effect.id}">${effect.name}</option>
        </c:forEach>
      </select>
    </div>

    <div class="form-group">
      <label for="star4EffectId">&#9733;&#9733;&#9733;&#9733; 4-Star Effect (optional)</label>
      <select class="form-control" id="star4EffectId" name="star4EffectId" disabled>
        <option value="">-- None --</option>
        <c:forEach var="effect" items="${star4Effects}">
          <option value="${effect.id}">${effect.name}</option>
        </c:forEach>
      </select>
    </div>

  </div><%-- end legendarySection --%>

  <button class="btn btn-primary" type="submit">Add</button>
  <a class="btn btn-default" href="${pageContext.request.contextPath}/viewUserPaPieces">Cancel</a>

</form>

<script src="${pageContext.request.contextPath}/js/armorPiece.js"></script>

</body>
</html>
