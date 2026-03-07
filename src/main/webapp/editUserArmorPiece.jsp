<%@ include file="taglib.jsp" %>

<c:set var="title" value="Edit Armor Piece" />

<!DOCTYPE html>
<html lang="en">

<c:import url="head.jsp" />

<body class="container">

<h2>Edit Armor Piece</h2>

<form class="form"
      method="post"
      action="${pageContext.request.contextPath}/editUserArmorPiece">

    <input type="hidden" name="id" value="${piece.id}" />

    <div class="form-group">
        <label for="userId">User ID</label>
        <input class="form-control"
               type="number"
               id="userId"
               name="userId"
               value="${piece.userId}"
               required />
    </div>

    <div class="form-group">
        <label for="armorTypeId">Armor Type</label>
        <select class="form-control" id="armorTypeId" name="armorTypeId">
            <c:forEach var="type" items="${armorTypes}">
                <option value="${type.id}"
                        <c:if test="${type.id == piece.armorType.id}">selected</c:if>>
                        ${type.typeName}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="armorSlotId">Armor Slot</label>
        <select class="form-control" id="armorSlotId" name="armorSlotId">
            <c:forEach var="slot" items="${armorSlots}">
                <option value="${slot.id}"
                        <c:if test="${slot.id == piece.armorSlot.id}">selected</c:if>>
                        ${slot.slotName}
                </option>
            </c:forEach>
        </select>
    </div>

    <button class="btn btn-primary" type="submit">Save</button>
    <a class="btn btn-default"
       href="${pageContext.request.contextPath}/viewUserArmorPieces">
        Cancel
    </a>
</form>

</body>
</html>