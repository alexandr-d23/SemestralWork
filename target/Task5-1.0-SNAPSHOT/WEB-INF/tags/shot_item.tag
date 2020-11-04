<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="shop item form tag" pageEncoding="UTF-8"%>
<%@attribute name="id" required="true"%>
<%@attribute name="cost" required="true"%>
<%@attribute name="description" required="true"%>
<link rel="stylesheet" href='<c:url value="/style/shop_item.css"/>' type="text/css">
<form action="${pageContext.request.contextPath}/shop" method="post">
    <div>
    Цена: <p class="description_achieve">${cost}</p>
    </div>
    <div class="value_achieve">
        ${description}
        <input type="hidden" name = "id" value="${id}"/>
    </div>
    <button type="submit" name="color-button" class="btn btn-dark">Купить</button>
</form>
