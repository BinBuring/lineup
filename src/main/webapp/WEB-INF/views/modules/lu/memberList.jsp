<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
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
		<li class="active"><a href="${ctx}/lu/member/">会员列表</a></li>
		<shiro:hasPermission name="lu:member:edit"><li><a href="${ctx}/lu/member/form">会员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/lu/member/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%-- <li><label>年龄：</label>
				<form:input path="age" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:input path="sex" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>--%>
			<li><label>会员状态：</label>
				<form:select path="status" class="input-xlarge">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('mem_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>			
			</li> 
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员名</th>
				<th>当前已预约个数</th>
				<th>手机号</th>
				<th>年龄</th>
				<th>性别</th>
				<th>邮箱</th>
				<th>会员状态</th>
				<shiro:hasPermission name="lu:member:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member">
			<tr>
				<td><a href="${ctx}/lu/member/form?id=${member.id}">
					${member.name}
				</a></td>
				<td>
					${member.recNum}
				</td>
				<td>
					${member.tel}
				</td>
				<td>
					${member.age}
				</td>
				<td>
					${fns:getDictLabel(member.sex, 'sex', '')}
				</td>
				<td>
					${member.email}
				</td>
				<td>
					${fns:getDictLabel(member.status, 'mem_status', '')}
				</td>
				<shiro:hasPermission name="lu:member:edit"><td>
    				<a href="${ctx}/lu/member/form?id=${member.id}">修改</a>
					<a href="${ctx}/lu/member/delete?id=${member.id}" onclick="return confirmx('确认要删除该会员吗？', this.href)">删除</a>
					<c:choose>
					<c:when test="${member.status eq '1'}">
					<a href="${ctx}/lu/member/ban?id=${member.id}" onclick="return confirmx('确认要禁止该会员吗？', this.href)">禁止</a>
					</c:when>
					<c:otherwise>
					<a href="${ctx}/lu/member/relieve?id=${member.id}">解除</a>
					</c:otherwise>
					</c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>