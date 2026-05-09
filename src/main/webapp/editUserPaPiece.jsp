<%@ include file="taglib.jsp" %>

<c:set var="title" value="Edit PA Piece" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<c:import url="navbar.jsp" />

<h2>Edit Power Armor Piece</h2>

<form class="form" method="post" action="${pageContext.request.contextPath}/editUserPaPiece">

    <input type="hidden" name="id" value="${piece.id}" />

    <div class="form-group">
        <label for="paTypeId">PA Type</label>
        <select class="form-control" id="paTypeId" name="paTypeId">
            <c:forEach var="type" items="${paTypes}">
                <option value="${type.id}"
                        <c:if test="${type.id == piece.paType.id}">selected</c:if>>
                        ${type.typeName}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="paSlotId">PA Slot</label>
        <select class="form-control" id="paSlotId" name="paSlotId">
            <c:forEach var="slot" items="${paSlots}">
                <option value="${slot.id}"
                        data-allows-legendary="${slot.allowsLegendary}"
                        <c:if test="${slot.id == piece.paSlot.id}">selected</c:if>>
                        ${slot.slotName}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="paFrameId">Assign to Frame (optional)</label>
        <select class="form-control" id="paFrameId" name="paFrameId">
            <option value="">-- Unassigned --</option>
            <c:forEach var="frame" items="${userFrames}">
                <option value="${frame.id}"
                        <c:if test="${not empty piece.paFrame && frame.id == piece.paFrame.id}">selected</c:if>>
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
                    <option value="${effect.id}"
                            <c:if test="${not empty piece.star1Effect && effect.id == piece.star1Effect.id}">selected</c:if>>
                            ${effect.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="star2EffectId">&#9733;&#9733; 2-Star Effect (optional)</label>
            <select class="form-control" id="star2EffectId" name="star2EffectId">
                <option value="">-- None --</option>
                <c:forEach var="effect" items="${star2Effects}">
                    <option value="${effect.id}"
                            <c:if test="${not empty piece.star2Effect && effect.id == piece.star2Effect.id}">selected</c:if>>
                            ${effect.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="star3EffectId">&#9733;&#9733;&#9733; 3-Star Effect (optional)</label>
            <select class="form-control" id="star3EffectId" name="star3EffectId">
                <option value="">-- None --</option>
                <c:forEach var="effect" items="${star3Effects}">
                    <option value="${effect.id}"
                            <c:if test="${not empty piece.star3Effect && effect.id == piece.star3Effect.id}">selected</c:if>>
                            ${effect.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="star4EffectId">&#9733;&#9733;&#9733;&#9733; 4-Star Effect (optional)</label>
            <select class="form-control" id="star4EffectId" name="star4EffectId">
                <option value="">-- None --</option>
                <c:forEach var="effect" items="${star4Effects}">
                    <option value="${effect.id}"
                            <c:if test="${not empty piece.star4Effect && effect.id == piece.star4Effect.id}">selected</c:if>>
                            ${effect.name}
                    </option>
                </c:forEach>
            </select>
        </div>

    </div><%-- end legendarySection --%>

    <button class="btn btn-primary" type="submit">Save</button>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/viewUserPaPieces">Cancel</a>

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

        star2.disabled = !has1;
        star3.disabled = !has2;
        star4.disabled = !has3;

        if (!has1) { star2.value = ''; star3.value = ''; star4.value = ''; }
        if (!has2) { star3.value = ''; star4.value = ''; }
        if (!has3) { star4.value = ''; }
    }

    function updateLegendaryVisibility() {
        var slotSelect = document.getElementById('paSlotId');
        var selected = slotSelect.options[slotSelect.selectedIndex];
        var allowsLegendary = selected.getAttribute('data-allows-legendary') === 'true';
        document.getElementById('legendarySection').style.display = allowsLegendary ? 'block' : 'none';
    }

    document.getElementById('star1EffectId').addEventListener('change', updateStarDropdowns);
    document.getElementById('star2EffectId').addEventListener('change', updateStarDropdowns);
    document.getElementById('star3EffectId').addEventListener('change', updateStarDropdowns);
    document.getElementById('paSlotId').addEventListener('change', updateLegendaryVisibility);

    // Run on page load — also initialize star chain state from existing values
    updateLegendaryVisibility();
    updateStarDropdowns();
</script>

</body>
</html>
