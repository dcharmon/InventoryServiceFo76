<%@ include file="taglib.jsp" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

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
    <input class="form-control" type="text" id="name" name="name"
           placeholder="e.g. Rad Farming Build" required />
  </div>

  <div class="form-group">
    <label for="notes">Notes (optional)</label>
    <textarea class="form-control" id="notes" name="notes" rows="3"
              placeholder="Any notes about this loadout..."></textarea>
  </div>

  <c:choose>
    <c:when test="${empty userPieces}">
      <p>You have no armor pieces yet.
        <a href="${pageContext.request.contextPath}/addUserArmorPiece">Add one first.</a>
      </p>
    </c:when>
    <c:otherwise>

      <%-- Loadout Summary Table --%>
      <div class="summary-sticky">
        <h4>Loadout Summary</h4>
        <table class="table table-bordered table-condensed" style="max-width:100%;">
          <thead>
          <tr>
            <th>Slot</th>
            <th>Armor Type</th>
            <th>&#9733;</th>
            <th>&#9733;&#9733;</th>
            <th>&#9733;&#9733;&#9733;</th>
            <th>&#9733;&#9733;&#9733;&#9733;</th>
            <th>DR</th><th>ER</th><th>RR</th><th>PR</th><th>FR</th><th>CR</th>
          </tr>
          </thead>
          <tbody>
          <my:summaryRow slotName="Left Arm"  rowId="summary-left-arm"  />
          <my:summaryRow slotName="Right Arm" rowId="summary-right-arm" />
          <my:summaryRow slotName="Torso"     rowId="summary-torso"     />
          <my:summaryRow slotName="Left Leg"  rowId="summary-left-leg"  />
          <my:summaryRow slotName="Right Leg" rowId="summary-right-leg" />
          <tr class="active">
            <td><strong>Totals</strong></td>
            <td colspan="5"></td>
            <td id="totalDr"><strong>0</strong></td>
            <td id="totalEr"><strong>0</strong></td>
            <td id="totalRr"><strong>0</strong></td>
            <td id="totalPr"><strong>0</strong></td>
            <td id="totalFr"><strong>0</strong></td>
            <td id="totalCr"><strong>0</strong></td>
          </tr>
          </tbody>
        </table>
      </div>

      <%-- Slot selection tables --%>
      <div class="row">
        <div class="col-md-12">
          <h4>Left Arm</h4>
          <my:slotTable slotName="Left Arm" figId="left-arm" />
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <h4>Right Arm</h4>
          <my:slotTable slotName="Right Arm" figId="right-arm" />
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <h4>Torso</h4>
          <my:slotTable slotName="Torso" figId="torso" />
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <h4>Left Leg</h4>
          <my:slotTable slotName="Left Leg" figId="left-leg" />
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <h4>Right Leg</h4>
          <my:slotTable slotName="Right Leg" figId="right-leg" />
        </div>
      </div>

    </c:otherwise>
  </c:choose>

  <input type="hidden" id="hidden-left-arm" name="armorPieceIds" value=""/>
  <input type="hidden" id="hidden-right-arm" name="armorPieceIds" value=""/>
  <input type="hidden" id="hidden-torso" name="armorPieceIds" value=""/>
  <input type="hidden" id="hidden-left-leg" name="armorPieceIds" value=""/>
  <input type="hidden" id="hidden-right-leg" name="armorPieceIds" value=""/>

  <br/>
  <button class="btn btn-primary" type="submit">Save Loadout</button>
  <a class="btn btn-default" href="${pageContext.request.contextPath}/viewLoadouts">Cancel</a>

</form>

<script>
  var resistanceData = {
    <c:forEach var="piece" items="${userPieces}" varStatus="pieceStatus">
    "${piece.id}": {
      name: "${piece.armorType.typeName}",
      dr: ${resolvedResistances[piece.id][0]},
      er: ${resolvedResistances[piece.id][1]},
      rr: ${resolvedResistances[piece.id][2]},
      pr: ${resolvedResistances[piece.id][3]},
      fr: ${resolvedResistances[piece.id][4]},
      cr: ${resolvedResistances[piece.id][5]}
    }<c:if test="${!pieceStatus.last}">,</c:if>
    </c:forEach>
  };
</script>
<script src="${pageContext.request.contextPath}/js/loadout.js"></script>

</body>
</html>