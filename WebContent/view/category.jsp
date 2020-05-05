<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- HTML5 shim for IE8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->


    <link href="<%=request.getContextPath()%>/public/assets/themes/flatadmin/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/public/assets/simpleboot3/css/simplebootadmin.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/public/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        form .input-order {
            margin-bottom: 0px;
            padding: 0 2px;
            width: 42px;
            font-size: 12px;
        }

        form .input-order:focus {
            outline: none;
        }

        .table-actions {
            margin-top: 5px;
            margin-bottom: 5px;
            padding: 0px;
        }

        .table-list {
            margin-bottom: 0px;
        }

        .form-required {
            color: red;
        }
    </style>
    <script src="<%=request.getContextPath()%>/public/assets/js/jquery-1.10.2.min.js"></script>
    <script src="<%=request.getContextPath()%>/public/js/wind.js"></script>
    <script src="<%=request.getContextPath()%>/public/assets/js/bootstrap.min.js"></script>
    <script>
        Wind.css('artDialog');
        Wind.css('layer');
        $(function () {
            $("[data-toggle='tooltip']").tooltip({
                container:'body',
                html:true,
            });
            $("li.dropdown").hover(function () {
                $(this).addClass("open");
            }, function () {
                $(this).removeClass("open");
            });
        });
    </script>
    <if condition="APP_DEBUG">
        <style>
            #think_page_trace_open {
                z-index: 9999;
            }
        </style>
    </if>
</head>
<body>
	<div class="wrap">
		<ul class="nav nav-tabs">
			<li class="active"><a href="category.cat">分类管理</a></li>
			<li><a href="addCategory.cat">添加分类</a></li>
		</ul>
		<form method="post" class="js-ajax-form" action="{:url('AdminCategory/listOrder')}">
			<div class="table-actions">
				
			</div>
			<table class="table table-hover table-bordered table-list">
				<thead>
					<tr>
						<th>ID</th>
						<th>分类名称</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
	<script src="<%=request.getContextPath()%>/public/admin/js/admin.js"></script>
</body>
</html>