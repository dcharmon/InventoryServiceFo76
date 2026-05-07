<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="slotName" required="true" %>
<%@ attribute name="rowId"    required="true" %>

<tr id="${rowId}">
    <td>${slotName}</td>
    <td data-col="name" class="text-muted summary-name">&#x2014;</td>
    <td data-col="star1" class="summary-star1"></td>
    <td data-col="star2" class="summary-star2"></td>
    <td data-col="star3" class="summary-star3"></td>
    <td data-col="star4" class="summary-star4"></td>
    <td data-col="dr" class="summary-dr"></td>
    <td data-col="er" class="summary-er"></td>
    <td data-col="rr" class="summary-rr"></td>
    <td data-col="pr" class="summary-pr"></td>
    <td data-col="fr" class="summary-fr"></td>
    <td data-col="cr" class="summary-cr"></td>
</tr>