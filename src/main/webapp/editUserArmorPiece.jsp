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
        <label for="armorTypeId">Armor Type ID</label>
        <input class="form-control"
               type="number"
               id="armorTypeId"
               name="armorTypeId"
               value="${piece.armorType.id}"
               required />
    </div>

    <div class="form-group">
        <label for="armorSlotId">Armor Slot ID</label>
        <input class="form-control"
               type="number"
               id="armorSlotId"
               name="armorSlotId"
               value="${piece.armorSlot.id}"
               required />
    </div>

    <button class="btn btn-primary" type="submit">Save</button>
    <a class="btn btn-default"
       href="${pageContext.request.contextPath}/viewUserArmorPieces">
        Cancel
    </a>
</form>

</body>
</html>