<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="shop item form tag" pageEncoding="UTF-8"%>
<%@attribute name="description" required="true"%>
<link rel="stylesheet" href='<c:url value="/style/achievement.css"/>' type="text/css">
<div class="achieve">
    <div>
        <p class="description_achieve">${description}</p>
    </div>
</div>
