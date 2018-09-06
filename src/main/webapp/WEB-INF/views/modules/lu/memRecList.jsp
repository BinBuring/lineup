<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员预约管理</title>
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
		<li class="active"><a href="${ctx}/lu/memRec/">会员预约列表</a></li>
		<shiro:hasPermission name="lu:memRec:edit"><li><a href="${ctx}/lu/memRec/form">会员预约添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memRec" action="${ctx}/lu/memRec/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目：</label>
				<sys:treeselect id="proId" name="proId" value="${memRec.proId}" labelName="project.name" labelValue="${memRec.project.name}"
					title="项目" url="/lu/memRec/treeData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="memTel" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>批次：</label>
				<form:input path="groNum" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>日期：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${memRec.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员</th>
				<th>手机号</th>
				<th>项目</th>
				<th>预约状态</th>
				<th>预约批次</th>
				<th>预约批次开始时间</th>
				<th>预约批次结束时间</th>
				<shiro:hasPermission name="lu:memRec:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memRec">
			<tr>
				<td><a href="${ctx}/lu/memRec/form?id=${memRec.id}">
					${memRec.member.name}
				</a></td>
				<td>
					${memRec.member.tel}
				</td>
				<td>
					${memRec.project.name}
				</td>
				<td>
					${fns:getDictLabel(memRec.recState, 'rec_state', '')}
				</td>
				<td>
					${memRec.group.number}
				</td>
				<td>
					${memRec.group.startTime}
				</td>
				<td>
					${memRec.group.endTime}
				</td>
				<shiro:hasPermission name="lu:memRec:edit"><td>
    				<a href="${ctx}/lu/memRec/form?id=${memRec.id}">修改</a>
					<a href="${ctx}/lu/memRec/delete?id=${memRec.id}" onclick="return confirmx('确认要删除该会员预约吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>