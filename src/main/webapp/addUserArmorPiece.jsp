<%@ include file="taglib.jsp" %>

<c:set var="title" value="Add Armor Piece" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<h2>Add Armor Piece</h2>

<form class="form" method="post" action="${pageContext.request.contextPath}/addUserArmorPiece">

  <div class="form-group">
    <label for="userId">User ID</label>
    <input class="form-control"
           type="number"
           id="userId"
           name="userId"
           value="1"
           required />
  </div>

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

  <button class="btn btn-primary" type="submit">Add</button>
  <a class="btn btn-default" href="${pageContext.request.contextPath}/viewUserArmorPieces">Cancel</a>

</form>

</body>
</html>