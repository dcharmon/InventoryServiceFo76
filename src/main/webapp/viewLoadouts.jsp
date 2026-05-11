<%@ include file="taglib.jsp" %>

<c:set var="title" value="My Loadouts" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>My Loadouts</h2>

<c:if test="${not empty sessionScope.flashMessage}">
    <div class="alert alert-success alert-dismissible">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
            ${sessionScope.flashMessage}
    </div>
    <c:remove var="flashMessage" scope="session" />
</c:if>

<%-- Filter bar --%>
<div class="row" style="margin-bottom: 15px;">
    <div class="col-xs-8">
        <div class="btn-group" role="group">
            <button type="button" class="btn btn-default active" id="filterAll"
                    onclick="filterLoadouts('all')">All</button>
            <button type="button" class="btn btn-default" id="filterStandard"
                    onclick="filterLoadouts('STANDARD')">Standard</button>
            <button type="button" class="btn btn-default" id="filterPa"
                    onclick="filterLoadouts('POWER_ARMOR')">Power Armor</button>
        </div>
    </div>
    <div class="col-xs-4 text-right">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/addLoadout">
            Add Loadout
        </a>
    </div>
</div>

<c:choose>
    <c:when test="${empty loadouts}">
        <p>You have no loadouts yet.</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="loadout" items="${loadouts}">
            <div class="panel panel-default loadout-panel"
                 data-type="${loadout.type}">
                <div class="panel-heading">
                    <h3 class="panel-title">
                            ${loadout.name}
                        <c:choose>
                            <c:when test="${loadout.type == 'POWER_ARMOR'}">
                                <span class="label label-warning">Power Armor</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-default">Standard</span>
                            </c:otherwise>
                        </c:choose>
                                <a class="btn btn-default btn-xs pull-right"
                                   href="${pageContext.request.contextPath}/exportLoadout?id=${loadout.id}">Export CSV</a>
                                <a class="btn btn-warning btn-xs pull-right"
                                   href="${pageContext.request.contextPath}/editLoadout?id=${loadout.id}">Edit</a>
                                <a class="btn btn-danger btn-xs pull-right"
                                   href="${pageContext.request.contextPath}/deleteLoadout?id=${loadout.id}"
                                   onclick="return confirm('Delete this loadout?');">Delete</a>
                    </h3>
                </div>
                <div class="panel-body">
                    <c:if test="${not empty loadout.notes}">
                        <p><em>${loadout.notes}</em></p>
                    </c:if>

                        <%-- Standard Armor --%>
                    <c:if test="${loadout.type == 'STANDARD'}">
                        <c:choose>
                            <c:when test="${empty loadout.armorPieces}">
                                <p>No armor pieces in this loadout.</p>
                            </c:when>
                            <c:otherwise>
                                <c:set var="totalDr" value="0" />
                                <c:set var="totalEr" value="0" />
                                <c:set var="totalRr" value="0" />
                                <c:set var="totalPr" value="0" />
                                <c:set var="totalFr" value="0" />
                                <c:set var="totalCr" value="0" />

                                <table class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Armor Type</th><th>Weight Class</th><th>Slot</th>
                                        <th>DR</th><th>ER</th><th>RR</th><th>PR</th><th>FR</th><th>CR</th>
                                        <th>&#9733; 1-Star</th><th>&#9733;&#9733; 2-Star</th>
                                        <th>&#9733;&#9733;&#9733; 3-Star</th><th>&#9733;&#9733;&#9733;&#9733; 4-Star</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="piece" items="${loadout.armorPieces}">
                                        <c:set var="resistance" value="${null}" />
                                        <c:forEach var="res" items="${piece.armorType.baseResistances}">
                                            <c:if test="${res.id.slotGroup == piece.armorSlot.slotGroup}">
                                                <c:set var="resistance" value="${res}" />
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not empty resistance}">
                                            <c:set var="totalDr" value="${totalDr + resistance.damageResistance}" />
                                            <c:set var="totalEr" value="${totalEr + resistance.energyResistance}" />
                                            <c:set var="totalRr" value="${totalRr + resistance.radiationResistance}" />
                                            <c:set var="totalPr" value="${totalPr + resistance.poisonResistance}" />
                                            <c:set var="totalFr" value="${totalFr + resistance.fireResistance}" />
                                            <c:set var="totalCr" value="${totalCr + resistance.cryoResistance}" />
                                        </c:if>
                                        <tr>
                                            <td>${piece.armorType.typeName}</td>
                                            <td><c:choose><c:when test="${not empty piece.armorType.weightClass}">${piece.armorType.weightClass}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
                                            <td>${piece.armorSlot.slotName}</td>
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
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr class="active">
                                        <td><strong>Total</strong></td><td></td><td></td>
                                        <td><strong>${totalDr}</strong></td>
                                        <td><strong>${totalEr}</strong></td>
                                        <td><strong>${totalRr}</strong></td>
                                        <td><strong>${totalPr}</strong></td>
                                        <td><strong>${totalFr}</strong></td>
                                        <td><strong>${totalCr}</strong></td>
                                        <td></td><td></td><td></td><td></td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                        <%-- Power Armor --%>
                    <c:if test="${loadout.type == 'POWER_ARMOR'}">
                        <c:choose>
                            <c:when test="${empty loadout.paFrames}">
                                <p>No power armor frames in this loadout.</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="frame" items="${loadout.paFrames}">
                                    <h5>
                                        <c:choose>
                                            <c:when test="${not empty frame.frameName}">${frame.frameName}</c:when>
                                            <c:otherwise>Frame #${frame.id}</c:otherwise>
                                        </c:choose>
                                    </h5>
                                    <c:choose>
                                        <c:when test="${empty frame.pieces}">
                                            <p>No pieces on this frame.</p>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="totalDr" value="0" />
                                            <c:set var="totalEr" value="0" />
                                            <c:set var="totalRr" value="0" />
                                            <c:set var="totalPr" value="0" />
                                            <c:set var="totalFr" value="0" />
                                            <c:set var="totalCr" value="0" />

                                            <table class="table table-striped table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>PA Type</th><th>Slot</th>
                                                    <th>DR</th><th>ER</th><th>RR</th><th>PR</th><th>FR</th><th>CR</th>
                                                    <th>&#9733; 1-Star</th><th>&#9733;&#9733; 2-Star</th>
                                                    <th>&#9733;&#9733;&#9733; 3-Star</th><th>&#9733;&#9733;&#9733;&#9733; 4-Star</th>
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
                                                    <c:if test="${not empty resistance}">
                                                        <c:set var="totalDr" value="${totalDr + resistance.damageResistance}" />
                                                        <c:set var="totalEr" value="${totalEr + resistance.energyResistance}" />
                                                        <c:set var="totalRr" value="${totalRr + resistance.radiationResistance}" />
                                                        <c:set var="totalPr" value="${totalPr + resistance.poisonResistance}" />
                                                        <c:set var="totalFr" value="${totalFr + resistance.fireResistance}" />
                                                        <c:set var="totalCr" value="${totalCr + resistance.cryoResistance}" />
                                                    </c:if>
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
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                                <tfoot>
                                                <tr class="active">
                                                    <td><strong>Total</strong></td><td></td>
                                                    <td><strong>${totalDr}</strong></td>
                                                    <td><strong>${totalEr}</strong></td>
                                                    <td><strong>${totalRr}</strong></td>
                                                    <td><strong>${totalPr}</strong></td>
                                                    <td><strong>${totalFr}</strong></td>
                                                    <td><strong>${totalCr}</strong></td>
                                                    <td></td><td></td><td></td><td></td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                </div>
            </div>
        </c:forEach>


    </c:otherwise>
</c:choose>

<p id="noResults" style="display:none;">No loadouts match the selected filter.</p>

<script src="${pageContext.request.contextPath}/js/viewLoadout.js"></script>

</body>
</html>
