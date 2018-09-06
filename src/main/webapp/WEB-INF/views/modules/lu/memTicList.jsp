<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员门票关系管理</title>
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
		<li class="active"><a href="${ctx}/lu/memTic/">会员门票关系列表</a></li>
		<shiro:hasPermission name="lu:memTic:edit"><li><a href="${ctx}/lu/memTic/form">会员门票关系添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memTic" action="${ctx}/lu/memTic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门票号：</label>
				<form:input path="ticketsId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
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
				<th>门票号</th>
				<th>会员id</th>
				<shiro:hasPermission name="lu:memTic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memTic">
			<tr>
				<td><a href="${ctx}/lu/memTic/form?id=${memTic.id}">
					${memTic.ticketsId}
				</a></td>
				<td>
					${memTic.memId}
				</td>
				<shiro:hasPermission name="lu:memTic:edit"><td>
    				<a href="${ctx}/lu/memTic/form?id=${memTic.id}">修改</a>
					<a href="${ctx}/lu/memTic/delete?id=${memTic.id}" onclick="return confirmx('确认要删除该会员门票关系吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>