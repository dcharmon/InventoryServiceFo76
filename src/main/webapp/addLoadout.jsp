<%@ include file="taglib.jsp" %>

<c:set var="title" value="Add Loadout" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>Add Loadout</h2>

<form class="form" method="post" action="${pageContext.request.contextPath}/addLoadout">

  <div class="form-group">
    <label for="name">Loadout Name</label>
    <input class="form-control"
           type="text"
           id="name"
           name="name"
           placeholder="e.g. Rad Farming Build"
           required />
  </div>

  <div class="form-group">
    <label for="notes">Notes (optional)</label>
    <textarea class="form-control"
              id="notes"
              name="notes"
              rows="3"
              placeholder="Any notes about this loadout..."></textarea>
  </div>

  <div class="form-group">
    <label>Select Armor Pieces (one per slot)</label>
    <c:choose>
      <c:when test="${empty userPieces}">
        <p>You have no armor pieces yet. <a href="${pageContext.request.contextPath}/addUserArmorPiece">Add one first.</a></p>
      </c:when>
      <c:otherwise>
        <table class="table table-striped table-bordered">
          <thead>
          <tr>
            <th>Select</th>
            <th>Armor Type</th>
            <th>Slot</th>
            <th>★ 1-Star</th>
            <th>★★ 2-Star</th>
            <th>★★★ 3-Star</th>
            <th>★★★★ 4-Star</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="piece" items="${userPieces}">
            <tr>
              <td>
                <input type="checkbox"
                       name="armorPieceIds"
                       value="${piece.id}"
                       data-piece-id="${piece.id}"
                       data-slot="${piece.armorSlot.slotName}" />
              </td>
              <td>${piece.armorType.typeName}</td>
              <td>${piece.armorSlot.slotName}</td>
              <td>
                <c:choose>
                  <c:when test="${not empty piece.star1Effect}">
                    ${piece.star1Effect.name}
                  </c:when>
                  <c:otherwise>--</c:otherwise>
                </c:choose>
              </td>
              <td>
                <c:choose>
                  <c:when test="${not empty piece.star2Effect}">
                    ${piece.star2Effect.name}
                  </c:when>
                  <c:otherwise>--</c:otherwise>
                </c:choose>
              </td>
              <td>
                <c:choose>
                  <c:when test="${not empty piece.star3Effect}">
                    ${piece.star3Effect.name}
                  </c:when>
                  <c:otherwise>--</c:otherwise>
                </c:choose>
              </td>
              <td>
                <c:choose>
                  <c:when test="${not empty piece.star4Effect}">
                    ${piece.star4Effect.name}
                  </c:when>
                  <c:otherwise>--</c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>

        <%-- Hidden JSON resistance data for JavaScript --%>
        <script>
          var resistanceData = {
            <c:forEach var="piece" items="${userPieces}" varStatus="pieceStatus">
            "${piece.id}": {
              dr: 0, er: 0, rr: 0, pr: 0, fr: 0, cr: 0
              <c:forEach var="res" items="${piece.armorType.baseResistances}">
              <c:if test="${res.id.slotGroup == piece.armorSlot.slotGroup}">
              , dr: ${res.damageResistance}
              , er: ${res.energyResistance}
              , rr: ${res.radiationResistance}
              , pr: ${res.poisonResistance}
              , fr: ${res.fireResistance}
              , cr: ${res.cryoResistance}
              </c:if>
              </c:forEach>
            }<c:if test="${!pieceStatus.last}">,</c:if>
            </c:forEach>
          };
        </script>

        <h4>Loadout Resistance Totals</h4>
        <table class="table table-bordered" id="resistanceSummary">
          <thead>
          <tr>
            <th>Damage</th>
            <th>Energy</th>
            <th>Radiation</th>
            <th>Poison</th>
            <th>Fire</th>
            <th>Cryo</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td id="totalDr">0</td>
            <td id="totalEr">0</td>
            <td id="totalRr">0</td>
            <td id="totalPr">0</td>
            <td id="totalFr">0</td>
            <td id="totalCr">0</td>
          </tr>
          </tbody>
        </table>

      </c:otherwise>
    </c:choose>
  </div>

  <button class="btn btn-primary" type="submit">Save Loadout</button>
  <a class="btn btn-default" href="${pageContext.request.contextPath}/viewLoadouts">Cancel</a>

</form>

<script>
  function updateTotals() {
    var totals = { dr: 0, er: 0, rr: 0, pr: 0, fr: 0, cr: 0 };

    document.querySelectorAll('input[name="armorPieceIds"]:checked').forEach(function(checkbox) {
      var pieceId = checkbox.getAttribute('data-piece-id');
      var res = resistanceData[pieceId];
      if (res) {
        totals.dr += res.dr;
        totals.er += res.er;
        totals.rr += res.rr;
        totals.pr += res.pr;
        totals.fr += res.fr;
        totals.cr += res.cr;
      }
    });

    document.getElementById('totalDr').textContent = totals.dr;
    document.getElementById('totalEr').textContent = totals.er;
    document.getElementById('totalRr').textContent = totals.rr;
    document.getElementById('totalPr').textContent = totals.pr;
    document.getElementById('totalFr').textContent = totals.fr;
    document.getElementById('totalCr').textContent = totals.cr;
  }

  document.querySelectorAll('input[name="armorPieceIds"]').forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
      if (!this.checked) {
        updateTotals();
        return;
      }

      var selectedSlot = this.getAttribute('data-slot');
      document.querySelectorAll('input[name="armorPieceIds"]:checked').forEach(function(other) {
        if (other === checkbox) return;
        if (other.getAttribute('data-slot') === selectedSlot) {
          other.checked = false;
        }
      });

      updateTotals();
    });
  });
</script>

</body>
</html>