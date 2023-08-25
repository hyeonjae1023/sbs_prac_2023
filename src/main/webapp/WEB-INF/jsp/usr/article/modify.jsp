<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 수정" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
<div class="container mx-auto px-3">
<form action="../article/doModify" method="POST" class="table-box-type-1">
<input type="hidden" name="id" value="${article.id }"/>
<table>
<colgroup>
<col width="200"/>
</colgroup>
<tbody>
<tr>
<th>번호</th>
<td>${article.id}</td>
</tr>
<tr>
<th>작성날짜</th>
<td>${article.regDateForPrint}</td>
</tr>
<tr>
<th>수정날짜</th>
<td>${article.updateDateForPrint}</td>
</tr>
<tr>
<th>작성자</th>
<td>${article.writerName}</td>
</tr>
<tr>
<th>제목</th>
<td>
<input type="text" class="w-96" name="title" placeholder="title" value="${article.title}"/>
</td>
</tr>
<tr>
<th>내용</th>
<td>
<input type="text" class="w-96" name="body" placeholder="body" value="${article.body}"/>
</td>
</tr>
<tr>
<th>수정</th>
<td>
<input type="submit" value="수정" />
<button type="button" onclick="history.back();">뒤로가기</button>
</td>
</tr>
</tbody>
</table>
</form>

<div class="btns">
<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
<a href="../article/modify?id=${article.id}" class="btn-text-link">게시물 수정</a>
<c:if test="${article.exta__actorCanDelete}">
<a class="btn-text-link" onclick="if( confirm('정말 삭제하시겠습니까?') == false ) return false;" href="../article/doDelete?id=${article.id}">게시물 삭제</a>
</c:if>
</div>
</div>
</section>
<%@ include file="../common/foot.jspf"%>