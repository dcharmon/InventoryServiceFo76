<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="slotName" required="true" %>
<%@ attribute name="figId"    required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table id="table-${figId}" class="slot-table table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>Select</th><th>Armor Type</th>
        <th>&#9733;</th><th>&#9733;&#9733;</th>
        <th>&#9733;&#9733;&#9733;</th><th>&#9733;&#9733;&#9733;&#9733;</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <label>
                <input type="radio" name="slot-${figId}" value=""
                       data-fig="${figId}" data-piece-id=""
                       data-star1="" data-star2="" data-star3="" data-star4=""
                       class="armor-radio"
                ${empty selectedBySlot[slotName] ? 'checked' : ''} />
                <span class="sr-only">None</span>
            </label>
        </td>
        <td colspan="5"><em>None</em></td>
    </tr>
    <c:forEach var="piece" items="${piecesBySlot[slotName]}">
        <tr>
            <td>
                <label>
                    <input type="radio" name="slot-${figId}" value="${piece.id}"
                           data-fig="${figId}" data-piece-id="${piece.id}"
                           data-star1="<c:out value='${piece.star1Effect.name}' default='--'/>"
                           data-star2="<c:out value='${piece.star2Effect.name}' default='--'/>"
                           data-star3="<c:out value='${piece.star3Effect.name}' default='--'/>"
                           data-star4="<c:out value='${piece.star4Effect.name}' default='--'/>"
                           class="armor-radio"
                        ${selectedBySlot[slotName] == piece.id ? 'checked' : ''} />
                    <span class="sr-only">Select ${piece.armorType.typeName} for ${slotName}</span>
                </label>
            </td>
            <td>${piece.armorType.typeName}</td>
            <td><c:choose><c:when test="${not empty piece.star1Effect}">${piece.star1Effect.name}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty piece.star2Effect}">${piece.star2Effect.name}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty piece.star3Effect}">${piece.star3Effect.name}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
            <td><c:choose><c:when test="${not empty piece.star4Effect}">${piece.star4Effect.name}</c:when><c:otherwise>--</c:otherwise></c:choose></td>
        </tr>
    </c:forEach>
    </tbody>
</table>