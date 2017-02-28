<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="theme"/>
		<title>Users</title>
	</head>
	<body>
		<legend>Users</legend>
		<g:if test="${flash.message}">
			<div class="row">
				<div class="message alert alert-success alert-dismissible col-md-4" role="alert" style="display: block; margin-top: 5px;"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>${flash.message}</div>
			</div>
		</g:if>
		<div class="row">
			<div class="col-xs-3">
				<input type="text" id="filterNameInput" class="form-control search" placeholder="Search name..." />
			</div>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th id="sortById"><a href="javascript:;">ID</a></th>
					<th id="sortByUsername"><a href="javascript:;">Username</a></th>
					<th id="sortByName"><a href="javascript:;">Name</a></th>
					<th id="sortByDepartment"><a href="javascript:;">Department</a></th>
					<th>Controls</th>
				</tr>
			</thead>
			<tbody id="table">
				<g:each in="${users}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.lastName}, ${user.firstName} ${user.middleName}</td>
						<td>${user.department}</td>
						<td>
							<g:link controller="user" action="edit" params="[id: user.id]"><i class="glyphicon glyphicon-edit"></i>&nbsp;Edit</g:link>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
		<script type="text/javascript">

			$('#filterNameInput').keyup(function() {
				var $rows = $('#table tr');
				var indexColumn = 2;
			    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

			    $rows.show().filter(function() {
			        var text = $(this).children(":eq("+indexColumn+")").text().replace(/\s+/g, ' ').toLowerCase();
			        return !~text.indexOf(val);
			    }).hide();
			});

			$('#sortById, #sortByUsername, #sortByName, #sortByDepartment')
		        .wrapInner('<span title="sort this column"/>')
		        .each(function(){
		            var th = $(this),
		                thIndex = th.index(),
		                inverse = false;
		            th.click(function(){
		                $('table').find('td').filter(function(){
		                    return $(this).index() === thIndex;
		                }).sortElements(function(a, b){
		                    return $.text([a]) > $.text([b]) ?
		                        inverse ? -1 : 1
		                        : inverse ? 1 : -1;
		                }, function(){
		                    // parentNode is the element we want to move
		                    return this.parentNode;
		                });
		                inverse = !inverse;
		            });
		        });
		</script>
	</body>
</html>
