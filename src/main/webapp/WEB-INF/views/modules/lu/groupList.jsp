<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目批数管理</title>
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
		<li class="active"><a href="${ctx}/lu/group/">项目批数列表</a></li>
		<%-- <shiro:hasPermission name="lu:group:edit"><li><a href="${ctx}/lu/group/form">项目批数添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="group" action="${ctx}/lu/group/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <ul class="ul-form">
			<li><label>批数：</label>
				<form:input path="number" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>批次开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${group.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>批次结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${group.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名</th>
				<th>批数</th>
				<th>批次开始时间</th>
				<th>批次结束时间</th>
				<shiro:hasPermission name="lu:group:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="group">
			<tr>
				<td>
					${group.project.name}
				</td>
				<td>
					${group.number}
				</td>
				<td>
					${group.startTime}
				</td>
				<td>
					${group.endTime}
				</td>
				<shiro:hasPermission name="lu:group:edit"><td>
					<a href="${ctx}/lu/memRec/subscribe?groId=${group.groId}">预约</a>
    				<%-- <a href="${ctx}/lu/group/form?id=${group.id}">修改</a>
					<a href="${ctx}/lu/group/delete?id=${group.id}" onclick="return confirmx('确认要删除该项目批数吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>