<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="containerDivId" style="margin-top: 7rem; margin-right: 22rem; margin-left:24rem;">
    <c:forEach items="${floors}" var="f" varStatus="Count">
        <a href="rooms.jsp" style="text-decoration: none">
            <div id="${f.id}" class="hoverIndex btn  border border-info rounded shadow p-3 mb-5 bg-white rounded"
                                        onclick="Cookies.set('floorId', ${f.id})">
               <p>
                   <br>Mertebe ${f.name}<br> <br>
                   Otaqlarin sayi: 8 <br>
               </p>
            </div>
        </a>
    </c:forEach>
</div>