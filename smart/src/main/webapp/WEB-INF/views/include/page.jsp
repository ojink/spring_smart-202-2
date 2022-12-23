<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<div class='page-list'>

<c:if test='${page.curBlock gt 1}'>
<a onclick='toPage(1)'><i class="fa-solid fa-angles-left"></i></a>
<a onclick='toPage(${page.beginPage-page.blockPage})'><i class="fa-solid fa-angle-left"></i></a>
</c:if>

<c:forEach var='no' begin="${page.beginPage}" end="${page.endPage}">
	<c:if test='${page.curPage eq no}'><span>${no}</span></c:if> 
	<c:if test='${page.curPage ne no}'><a onclick='toPage(${no})'>${no}</a></c:if> 
</c:forEach>
<c:if test='${page.curBlock lt page.totalBlock}'>
<a onclick="toPage(${page.endPage+1})"><i class="fa-solid fa-angle-right"></i></a>
<a onclick="toPage(${page.totalPage})"><i class="fa-solid fa-angles-right"></i></a>
</c:if>

</div>
<script>
function toPage(no){
	$('[name=curPage]').val( no );
	$('form').submit();
}
</script>




