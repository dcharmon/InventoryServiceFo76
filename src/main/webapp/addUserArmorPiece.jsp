<%@ include file="taglib.jsp" %>

<c:set var="title" value="Add Armor Piece" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>Add Armor Piece</h2>

<form class="form" method="post" action="${pageContext.request.contextPath}/addUserArmorPiece">

  <div class="form-group">
    <label for="armorTypeId">Armor Type</label>
    <select class="form-control" id="armorTypeId" name="armorTypeId">
      <c:forEach var="type" items="${armorTypes}">
        <option value="${type.id}">${type.typeName}</option>
      </c:forEach>
    </select>
  </div>

  <div class="form-group">
    <label for="armorSlotId">Armor Slot</label>
    <select class="form-control" id="armorSlotId" name="armorSlotId">
      <c:forEach var="slot" items="${armorSlots}">
        <option value="${slot.id}">${slot.slotName}</option>
      </c:forEach>
    </select>
  </div>

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

  <button class="btn btn-primary" type="submit">Add</button>
  <a class="btn btn-default" href="${pageContext.request.contextPath}/viewUserArmorPieces">Cancel</a>

</form>

<script src="${pageContext.request.contextPath}/js/armorPiece.js"></script>

</body>
</html>