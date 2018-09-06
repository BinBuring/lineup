<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理</title>
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
		<li class="active"><a href="${ctx}/lu/project/">项目列表</a></li>
		<shiro:hasPermission name="lu:project:edit"><li><a href="${ctx}/lu/project/form">项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="project" action="${ctx}/lu/project/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目名：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%-- <li><label>项目开放时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${project.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>项目结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${project.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>项目控制员：</label>
				<sys:treeselect id="administrator" name="administrator" value="${project.administrator}" labelName="" labelValue="${project.administrator}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名</th>
				<th>项目开放时间</th>
				<th>项目结束时间</th>
				<th>项目控制员</th>
				<th>每批人数</th>
				<th>每批时间/分</th>
				<th>项目地址</th>
				<th>项目特色</th>
				<shiro:hasPermission name="lu:project:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="project">
			<tr>
				<td><a href="${ctx}/lu/project/form?id=${project.id}">
					${project.name}
				</a></td>
				<td>
					${project.startTime}
				</td>
				<td>
					${project.endTime}
				</td>
				<td>
					${project.administrator.name}
				</td>
				<td>
					${project.groupNum}
				</td>
				<td>
					${project.groupData}
				</td>
				<td>
					${project.address}
				</td>
				<td>
					${project.features}
				</td>
				<shiro:hasPermission name="lu:project:edit"><td>
    				<a href="${ctx}/lu/project/form?id=${project.id}">修改</a>
					<a href="${ctx}/lu/project/delete?id=${project.id}" onclick="return confirmx('确认要删除该项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>