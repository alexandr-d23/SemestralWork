<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="shop item form tag" pageEncoding="UTF-8"%>
<%@attribute name="cost" required="true"%>
<%@attribute name="description" required="true"%>
<link rel="stylesheet" href='<c:url value="/style/user_purchase.css"/>' type="text/css">
<div class="achieve">
    <div>
        Цена: <p class="description_achieve">${cost}</p>
    </div>
    <div class="value_achieve">
        ${description}
    </div>
</div>
