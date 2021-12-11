<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Simple items list</title>
</head>
<body>
<h2 class="mt-5" style="color: #703206">List of items</h2>
<hr>
<c:if test="${!items.isEmpty()}">
    <c:forEach items="${items}" var="itemDTO">
        <div>
            <p style="color: #770350">${itemDTO.getId()}</p>
            <ul style="color: #022e70">
                <li>Product name: ${itemDTO.getName()}</li>
                <li>Unit cost: ${itemDTO.getPrice()}</li>
                <li>Product description: ${itemDTO.getDescription()}</li>
            </ul>
        </div>
    </c:forEach>
</c:if>
<c:if test="${items.isEmpty()}">
    <div>
        <p>Empty...</p>
    </div>
</c:if>
<hr>
<p>
    <a href="http://localhost:8080/">
        <button type="back"> back</button>
    </a>
</p>
</body>
</html>

