<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="footer form tag" pageEncoding="UTF-8"%>
<%@attribute name="footerText" required="true"%>
<link rel="stylesheet" href='<c:url value="/style/footer.css"/>' type="text/css">
<footer class = "my_footer">
    "${footerText}"
</footer>