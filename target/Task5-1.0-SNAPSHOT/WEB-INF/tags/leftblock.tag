<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="left block form tag" pageEncoding="UTF-8"%>
<link rel="stylesheet" href='<c:url value="/style/leftblock.css"/>' type="text/css">
<div class="my-left-block">
<c:if test="${sessionScope.get('authorized') == 'true'}">
    <a href="${pageContext.request.contextPath}/game" class="btn btn-danger btn-lg active" role="button" aria-pressed="true" >Играть</a>
    <a href="${pageContext.request.contextPath}/shop" class="btn btn-primary btn-lg active" role="button" aria-pressed="true" >Магазин</a>
    <a href="${pageContext.request.contextPath}/achievements" class="btn btn-success btn-lg active" role="button" aria-pressed="true" >Достижения</a>
</c:if>
    <a href="${pageContext.request.contextPath}/rating" class="btn btn-warning btn-lg active" role="button" aria-pressed="true" >Рейтинг</a>
</div>
