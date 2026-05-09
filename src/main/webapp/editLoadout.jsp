<%@ include file="taglib.jsp" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<c:set var="title" value="Edit Loadout" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>Edit Loadout</h2>

<form class="form" method="post" action="${pageContext.request.contextPath}/editLoadout">

    <input type="hidden" name="id" value="${loadout.id}" />

    <div class="form-group">
        <label for="name">Loadout Name</label>
        <input class="form-control" type="text" id="name" name="name"
               value="${loadout.name}" required />
    </div>

    <div class="form-group">
        <label for="notes">Notes (optional)</label>
        <textarea class="form-control" id="notes" name="notes" rows="3">${loadout.notes}</textarea>
    </div>

    <div class="btn-group" role="group">
        <button type="button" class="btn btn-default active" id="btnStandard"
                onclick="setLoadoutType('STANDARD')">Standard Armor</button>
        <button type="button" class="btn btn-default" id="btnPowerArmor"
                onclick="setLoadoutType('POWER_ARMOR')">Power Armor</button>
    </div>
    <input type="hidden" name="type" id="loadoutType" value="STANDARD" />

    <%-- Standard Armor Section --%>
    <div id="standardSection">
        <c:choose>
            <c:when test="${empty userPieces}">
                <p>You have no armor pieces yet.
                    <a href="${pageContext.request.contextPath}/addUserArmorPiece">Add one first.</a>
                </p>
            </c:when>
            <c:otherwise>

                <div class="summary-sticky">
                    <h4>Loadout Summary</h4>
                    <table class="table table-bordered table-condensed" style="max-width:100%;">
                        <thead>
                        <tr>
                            <th>Slot</th>
                            <th>Armor Type</th>
                            <th>&#9733;</th><th>&#9733;&#9733;</th><th>&#9733;&#9733;&#9733;</th><th>&#9733;&#9733;&#9733;&#9733;</th>
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

                <div class="row"><div class="col-md-12"><h4>Left Arm</h4><my:slotTable slotName="Left Arm" figId="left-arm" /></div></div>
                <div class="row"><div class="col-md-12"><h4>Right Arm</h4><my:slotTable slotName="Right Arm" figId="right-arm" /></div></div>
                <div class="row"><div class="col-md-12"><h4>Torso</h4><my:slotTable slotName="Torso" figId="torso" /></div></div>
                <div class="row"><div class="col-md-12"><h4>Left Leg</h4><my:slotTable slotName="Left Leg" figId="left-leg" /></div></div>
                <div class="row"><div class="col-md-12"><h4>Right Leg</h4><my:slotTable slotName="Right Leg" figId="right-leg" /></div></div>

            </c:otherwise>
        </c:choose>
    </div><%-- end standardSection --%>

    <%-- Power Armor Section --%>
    <div id="powerArmorSection" style="display:none;">
        <c:choose>
            <c:when test="${empty userFrames}">
                <p>You have no power armor frames yet.
                    <a href="${pageContext.request.contextPath}/addUserPaFrame">Add one first.</a>
                </p>
            </c:when>
            <c:otherwise>
                <h4>Select a Frame</h4>
                <c:forEach var="frame" items="${userFrames}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <label>
                                <input type="checkbox" name="paFrameIds" value="${frame.id}"
                                       <c:if test="${selectedFrameIds.contains(frame.id)}">checked</c:if> />
                                <c:choose>
                                    <c:when test="${not empty frame.frameName}">${frame.frameName}</c:when>
                                    <c:otherwise>Frame #${frame.id}</c:otherwise>
                                </c:choose>
                            </label>
                        </div>
                        <c:if test="${not empty frame.pieces}">
                            <div class="panel-body">
                                <table class="table table-condensed table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Slot</th><th>PA Type</th>
                                        <th>DR</th><th>ER</th><th>RR</th><th>PR</th><th>FR</th><th>CR</th>
                                        <th>&#9733;</th><th>&#9733;&#9733;</th><th>&#9733;&#9733;&#9733;</th><th>&#9733;&#9733;&#9733;&#9733;</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="piece" items="${frame.pieces}">
                                        <tr>
                                            <td>${piece.paSlot.slotName}</td>
                                            <td>${piece.paType.typeName}</td>
                                            <td>${resolvedPaResistances[piece.id][0]}</td>
                                            <td>${resolvedPaResistances[piece.id][1]}</td>
                                            <td>${resolvedPaResistances[piece.id][2]}</td>
                                            <td>${resolvedPaResistances[piece.id][3]}</td>
                                            <td>${resolvedPaResistances[piece.id][4]}</td>
                                            <td>${resolvedPaResistances[piece.id][5]}</td>
                                            <td><c:choose><c:when test="${not empty piece.star1Effect}"><span title="${piece.star1Effect.description}">${piece.star1Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
                                            <td><c:choose><c:when test="${not empty piece.star2Effect}"><span title="${piece.star2Effect.description}">${piece.star2Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
                                            <td><c:choose><c:when test="${not empty piece.star3Effect}"><span title="${piece.star3Effect.description}">${piece.star3Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
                                            <td><c:choose><c:when test="${not empty piece.star4Effect}"><span title="${piece.star4Effect.description}">${piece.star4Effect.name}</span></c:when><c:otherwise>--</c:otherwise></c:choose></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div><%-- end powerArmorSection --%>

    <input type="hidden" id="hidden-left-arm"  name="armorPieceIds" value=""/>
    <input type="hidden" id="hidden-right-arm" name="armorPieceIds" value=""/>
    <input type="hidden" id="hidden-torso"     name="armorPieceIds" value=""/>
    <input type="hidden" id="hidden-left-leg"  name="armorPieceIds" value=""/>
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
