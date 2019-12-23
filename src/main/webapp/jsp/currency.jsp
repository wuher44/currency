<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" %>
<html>
<style>
    body {
        background-size: 100vw;
        align-items: center;
    }

    .mainContainer {
        padding: 10px;
        margin-left: 10px;
        margin-right: 10px; /* auto;*/
        margin-top: 25px;
    }

    .formContainer {
        padding: 20px;
        color: white;
        font-size: 11px;
        font-family: "Times New Roman";
        border: 1px solid lightcoral;
        width: auto;
    }
</style>
<body>
<div class="mainContainer">
    <div class="formContainer" style="background: rgba(5,17,43,0.4)">
        <form method="post" action="/currency" style="margin-top: 10px; margin-left: 10px;">
            <input type="date" name="exchangeDate" value="${selectedDate}"/>
            <input type="submit" value="Select"><br>
            <select name="selectedBase">
                <option <c:if test="${prevSel eq'EUR'}">selected</c:if>>EUR</option>
                <option <c:if test="${prevSel eq'PLN'}">selected</c:if>>PLN</option>
                <option <c:if test="${prevSel eq'GBP'}">selected</c:if>>GBP</option>
                <option <c:if test="${prevSel eq'USD'}">selected</c:if>>USD</option>
            </select>
        </form>


    </div>
    <c:if test="${selectedDate==null}">
        <h2 style="color: black; text-align: center">
            <p id="date"></p>
            <script>
                document.getElementById("date").innerHTML = Date();
            </script>
        </h2>
    </c:if>
    <c:if test="${currenciesRates !=null}">
        <h2 style="black: white; text-align: center">
                ${selectedDate}
        </h2>
        <div style="padding: 5px; background-color: rgba(5,17,43,0.4); margin-top: 20px; text-align: center; font-size: 30px; color: white; border: 1px solid lightcoral;">

            <c:forEach var="rate" items="${currenciesRates.rates}">
                <div>
                        ${rate.key}
                    <fmt:formatNumber value="${rate.value}" maxFractionDigits="3" type="number"/>
                </div>
            </c:forEach>

        </div>
    </c:if>
</div>
</body>
</html>