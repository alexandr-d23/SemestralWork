<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="shop item form tag" pageEncoding="UTF-8"%>
<%@attribute name="name" required="true"%>
<%@attribute name="score" required="true"%>
<link rel="stylesheet" href='<c:url value="/style/top_user.css"/>' type="text/css">
<div class="achieve">
    <div>
        <p class="description_achieve">${name}</p>
    </div>
    <div class="value_achieve">
        ${score}
    </div>
</div>

