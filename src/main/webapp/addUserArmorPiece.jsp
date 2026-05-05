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
    <label for="star1EffectId">★ 1-Star Effect (optional)</label>
    <select class="form-control" id="star1EffectId" name="star1EffectId">
      <option value="">-- None --</option>
      <c:forEach var="effect" items="${star1Effects}">
        <option value="${effect.id}">${effect.name}</option>
      </c:forEach>
    </select>
  </div>

  <div class="form-group">
    <label for="star2EffectId">★★ 2-Star Effect (optional)</label>
    <select class="form-control" id="star2EffectId" name="star2EffectId" disabled>
      <option value="">-- None --</option>
      <c:forEach var="effect" items="${star2Effects}">
        <option value="${effect.id}">${effect.name}</option>
      </c:forEach>
    </select>
  </div>

  <div class="form-group">
    <label for="star3EffectId">★★★ 3-Star Effect (optional)</label>
    <select class="form-control" id="star3EffectId" name="star3EffectId" disabled>
      <option value="">-- None --</option>
      <c:forEach var="effect" items="${star3Effects}">
        <option value="${effect.id}">${effect.name}</option>
      </c:forEach>
    </select>
  </div>

  <div class="form-group">
    <label for="star4EffectId">★★★★ 4-Star Effect (optional)</label>
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

<script>
  function updateStarDropdowns() {
    var star1 = document.getElementById('star1EffectId');
    var star2 = document.getElementById('star2EffectId');
    var star3 = document.getElementById('star3EffectId');
    var star4 = document.getElementById('star4EffectId');

    var has1 = star1.value !== '';
    var has2 = has1 && star2.value !== '';
    var has3 = has2 && star3.value !== '';

    // Enable/disable based on previous tier
    star2.disabled = !has1;
    star3.disabled = !has2;
    star4.disabled = !has3;

    // Reset lower tiers if a higher tier is cleared
    if (!has1) {
      star2.value = '';
      star3.value = '';
      star4.value = '';
    }
    if (!has2) {
      star3.value = '';
      star4.value = '';
    }
    if (!has3) {
      star4.value = '';
    }
  }

  document.getElementById('star1EffectId').addEventListener('change', updateStarDropdowns);
  document.getElementById('star2EffectId').addEventListener('change', updateStarDropdowns);
  document.getElementById('star3EffectId').addEventListener('change', updateStarDropdowns);
</script>

</body>
</html>