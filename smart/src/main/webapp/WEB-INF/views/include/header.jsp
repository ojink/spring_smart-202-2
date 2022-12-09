<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
header {
	border-bottom: 1px solid #aaa;
	align-items: center; 
	justify-content: space-between;
	padding: 0 100px;
}
header nav, header nav ul { display: flex; }
header nav ul { font-size: 18px; font-weight: bold; }
header nav ul li:not(:first-child) { margin-left: 50px; }
header nav li a.active, header nav li a:hover { color: #0040ff; font-weight: bold; }
</style> 
<header>
<nav>
	<ul>
		<li><a href='<c:url value="/"/>'><img src='img/hanul.logo.png'></a></li>
		<li><a href='list.cu' ${category eq 'cu' ? 'class="active"' : ''}>고객관리</a></li>
		<li><a href='list.hr' ${category eq 'hr' ? 'class="active"' : ''}>사원관리</a></li>
		<li><a>공지사항</a></li>
		<li><a>방명록</a></li>
		<li><a>공공데이터</a></li>
		<li><a>시각화</a></li>
	</ul>
</nav>
</header>