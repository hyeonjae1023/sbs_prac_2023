<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 작성" />
<%@ include file="../common/head.jspf"%>

<section class="mt-5">
<div class="container mx-auto px-3">
<form action="../article/doWrite" method="POST" class="table-box-type-1">
<input type="hidden" name="id" value="${article.id}"/>

<table>
<colgroup>
<col width="200"/>
</colgroup>
<tbody>
<tr>
<th>작성자</th>
 <td>${rq.loginedMember.nickname}</td>
</tr>
<tr>
<th>제목</th>
 <td>
 <input type="text"  class="input input-bordered w-full max-w-xs" name="title" placeholder="제목"/>
 </td>
</tr>
<tr>
<th>내용</th>
 <td>
 <textarea class="w-full textarea textarea-bordered" name="body" placeholder="내용"></textarea>
 </td>
</tr>
<tr>
<th>작성</th>
<td>
<input type="submit" class="btn btn-primary" value="작성" />
<button type="button" class="btn btn-outline btn-primary" onclick="history.back();">뒤로가기</button>
</td>
</tr>
</tbody>
</table>
</form>
</div>
</section>
<%@ include file="../common/foot.jspf"%>
