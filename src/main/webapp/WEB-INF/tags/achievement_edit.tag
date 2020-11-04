<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="shop item form tag" pageEncoding="UTF-8"%>
<%@attribute name="description" required="true"%>
<%@attribute name="requiredValue" required="true"%>
<%@attribute name="type" required="true"%>
<%@attribute name="id" required="true"%>
<link rel="stylesheet" href='<c:url value="/style/achievement_edit.css"/>' type="text/css">
<form>
    <div class="achieve">
        <div>
            <input type="text" name = "description" value= "${description}"/>
        </div>
        <div>
            <input type="text" name = "type" value= "${type}" />
        </div>
        <div class="value_achieve">
            <input type="hidden" name = "achievement-id" value="${id}"/>
            <input type="number" name = "value" value= "${requiredValue}"/>
        </div>
        <button formaction="${pageContext.request.contextPath}/admin" formmethod="post"
                type="submit" name="change" value="change" class="btn btn-dark">Изменить</button>
        <button formaction="${pageContext.request.contextPath}/admin" formmethod="post"
                type="submit" name="delete" value = "delete" class="btn btn-dark">Удалить</button>
    </div>
</form>

<%--<style>--%>
<%--    <%@include file="/style/achievement_edit.css" %>--%>
<%--</style>--%>