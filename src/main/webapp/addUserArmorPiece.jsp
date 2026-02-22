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
    <label for="armorTypeId">Armor Type ID</label>
    <input class="form-control"
           type="number"
           id="armorTypeId"
           name="armorTypeId"
           required />
  </div>

  <div class="form-group">
    <label for="armorSlotId">Armor Slot ID</label>
    <input class="form-control"
           type="number"
           id="armorSlotId"
           name="armorSlotId"
           required />
  </div>

  <button class="btn btn-primary" type="submit">Add</button>
  <a class="btn btn-default" href="${pageContext.request.contextPath}/viewUserArmorPieces">Cancel</a>
</form>

</body>
</html>