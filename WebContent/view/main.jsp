<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh_CN" style="overflow: hidden;">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>java后台</title>
    <meta name="description" content="This is page-header (.page-header &gt; h1)">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- HTML5 shim for IE8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->
    <link href="<%=request.getContextPath()%>/public/assets/themes/flatadmin/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/public/assets/simpleboot3/css/simplebootadmin.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/public/font-awesome/css/font-awesome.min.css?page=index" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/public/assets/themes/flatadmin/simplebootadminindex.min.css">
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        /*-----------------导航hack--------------------*/
        .nav-list > li.open {
            position: relative;
        }

        .nav-list > li.open .back {
            display: none;
        }

        .nav-list > li.open .normal {
            display: inline-block !important;
        }

        .nav-list > li.open a {
            padding-left: 7px;
        }

        .nav-list > li .submenu > li > a {
            background: #fff;
        }

        .nav-list > li .submenu > li a > [class*="fa-"]:first-child {
            left: 20px;
        }

        .nav-list > li ul.submenu ul.submenu > li a > [class*="fa-"]:first-child {
            left: 30px;
        }
        /*----------------导航hack--------------------*/
    </style>
<body style="min-width:900px;overflow: hidden;">
<div id="loading"><i class="loadingicon"></i><span>Loading...</span></div>
<div id="right-tools-wrapper">
</div>
<div class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="{:url('admin/index/index')}" class="navbar-brand" style="min-width: 200px;text-align: center;">后台管理</a>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="navbar-collapse collapse" id="navbar-main">
            <div class="pull-left" style="position: relative;">
                <a id="task-pre" class="task-changebt"><i class="fa fa-chevron-left"></i></a>
                <div id="task-content">
                    <ul class="nav navbar-nav cmf-component-tab" id="task-content-inner">
                        <li class="cmf-component-tabitem noclose" app-id="0" app-url="{:url('main/index')}"
                            app-name="首页">
                            <a class="cmf-tabs-item-text">首页</a>
                        </li>
                    </ul>
                    <div style="clear:both;"></div>
                </div>
                <a id="task-next" class="task-changebt"><i class="fa fa-chevron-right"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right simplewind-nav">
                <li class="light-blue" style="border-left:none;display: none;" id="close-all-tabs-btn">
                    <a id="close-wrapper" href="javascript:void(0);" title="{:lang('CLOSE_TOP_MENU')}" style="color:#fff;font-size: 16px">
                        <i class="fa fa-times right_tool_icon"></i>
                    </a>
                </li>
                <li class="light-blue" style="border-left:none;">
                    <a id="refresh-wrapper" href="javacript:void(0);" title="{:lang('REFRESH_CURRENT_PAGE')}" style="color:#fff;font-size: 16px">
                        <i class="fa fa-refresh right_tool_icon"></i>
                    </a>
                </li>
                <li class="light-blue dropdown" style="border-left:none;">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                         <img class="nav-user-photo" width="30" height="30" src="<%=request.getContextPath()%>/public/assets/images/logo-18.png" alt="{$admin.user_login|default=''}">
                        <span class="user-info">
							${user}
						</span>
                        <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
                       	<li>
                             <a href="javascript:;"><i class="fa fa-cog"></i> 网站信息</a></li>
                         <li>
                             <a href="javascript:;"><i class="fa fa-user"></i> 个人信息</a></li>
                         <li>
                             <a href="javascript:;"><i class="fa fa-lock"></i> 密码修改</a>
                         </li>

                        <li><a href="logout.do"><i class="fa fa-sign-out"></i>退出 </a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="main-container container-fluid">

    <div class="sidebar" id="sidebar">
        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <a class="btn btn-sm btn-warning" href="__ROOT__/"
               title="{:lang('WEBSITE_HOME_PAGE')}"
               target="_blank"
               data-toggle="tooltip">
                <i class="fa fa-home"></i>
            </a>
            <a class="btn btn-sm btn-success" href="javascript:openapp('{:url('portal/AdminCategory/index')}','index_termlist','文章分类管理');" title="文章分类管理">
                <i class="fa fa-th"></i>
            </a>

            <a class="btn btn-sm btn-info" href="javascript:openapp('{:url('portal/AdminArticle/index')}','index_postlist','文章管理');" title="文章管理">
                <i class="fa fa-pencil"></i>
            </a>

            <a class="btn btn-sm btn-info"
               href="javascript:openapp('{:url('user/AdminAsset/index')}','userAdminAssetindex','资源管理',true);"
               title="资源管理"
               data-toggle="tooltip">
                <i class="fa fa-file"></i>
            </a>

            <a class="btn btn-sm btn-danger"
               href="javascript:openapp('{:url('admin/RecycleBin/index')}','index_recycle','回收站',true);"
               title="回收站"
               data-toggle="tooltip">
                <i class="fa fa-recycle"></i>
            </a>

            <a class="btn btn-sm btn-default"
               href="javascript:openapp('{:url('admin/Menu/index')}','index_menu','{:lang('ADMIN_MENU_INDEX')}',true);"
               title="{:lang('ADMIN_MENU_INDEX')}"
               data-toggle="tooltip">
                <i class="fa fa-list"></i>
            </a>


        </div>
        <div id="nav-wrapper">
            <ul class="nav nav-list">

            </ul>
        </div>

    </div>

    <div class="main-content">
        <div class="page-content" id="content">
            <iframe src="<%=request.getContextPath()%>/view/info.jsp" style="width:100%;height: 100%;" frameborder="0" id="appiframe-0"
                    class="appiframe"></iframe>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/public/assets/js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/public/js/wind.js"></script>
<script src="<%=request.getContextPath()%>/public/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/public/admin/js/admin.js"></script>
<script src="<%=request.getContextPath()%>/public/assets/simpleboot3/js/adminindex.js"></script>
<script>
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
        $("li.dropdown").hover(function () {
            $(this).addClass("open");
        }, function () {
            $(this).removeClass("open");
        });

        var menus= {:$menus_js_var};
        //读取url参数。尝试执行菜单功能。
        if(typeof(menus) != "undefined"){

            var tw = window.top;
            var twa =tw.location.href.split("#");
            var url = twa[1];
            var urlTmp = url;
            if (url !=null ){
                //去掉/ 去掉_ 全部小写。
                urlTmp = urlTmp.replace(/[\\/|_|]/g,"");
                urlTmp = urlTmp.replace(".html","");
                var menu = menus[urlTmp];
                if (menu){
                    openapp(url,menu.id+menu.app,menu.name,true);
                }
            }
        }




    });

    var ismenumin = $("#sidebar").hasClass("menu-min");
    $(".nav-list").on("click", function (event) {
        var closest_a = $(event.target).closest("a");
        if (!closest_a || closest_a.length == 0) {
            return
        }
        if (!closest_a.hasClass("dropdown-toggle")) {
            if (ismenumin && "click" == "tap" && closest_a.get(0).parentNode.parentNode == this) {
                var closest_a_menu_text = closest_a.find(".menu-text").get(0);
                if (event.target != closest_a_menu_text && !$.contains(closest_a_menu_text, event.target)) {
                    return false
                }
            }
            return
        }
        var closest_a_next = closest_a.next().get(0);
        if (!$(closest_a_next).is(":visible")) {
            var closest_ul = $(closest_a_next.parentNode).closest("ul");
            if (ismenumin && closest_ul.hasClass("nav-list")) {
                return
            }
            closest_ul.find("> .open > .submenu").each(function () {
                if (this != closest_a_next && !$(this.parentNode).hasClass("active")) {
                    $(this).slideUp(150).parent().removeClass("open")
                }
            });
        }
        if (ismenumin && $(closest_a_next.parentNode.parentNode).hasClass("nav-list")) {
            return false;
        }
        $(closest_a_next).slideToggle(150).parent().toggleClass("open");
        return false;
    });



</script>
</body>
</html>
