<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员头像管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lu/memFace/">会员头像列表</a></li>
		<shiro:hasPermission name="lu:memFace:edit"><li><a href="${ctx}/lu/memFace/form">会员头像添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memFace" action="${ctx}/lu/memFace/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员id：</label>
				<form:input path="memId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>脸部id</th>
				<th>会员id</th>
				<th>脸部图片</th>
				<shiro:hasPermission name="lu:memFace:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memFace">
			<tr>
				<td><a href="${ctx}/lu/memFace/form?id=${memFace.id}">
					${memFace.faceId}
				</a></td>
				<td>
					${memFace.memId}
				</td>
				<td>
					${memFace.url}
				</td>
				<shiro:hasPermission name="lu:memFace:edit"><td>
    				<a href="${ctx}/lu/memFace/form?id=${memFace.id}">修改</a>
					<a href="${ctx}/lu/memFace/delete?id=${memFace.id}" onclick="return confirmx('确认要删除该会员头像吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>