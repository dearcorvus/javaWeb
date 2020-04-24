<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <title>java后台</title>
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="robots" content="noindex,nofollow">

    <!-- HTML5 shim for IE8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->

	<link rel="stylesheet" href="../public/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../public/font-awesome/css/font-awesome.min.css" />
	<link rel="stylesheet" href="../public/admin/css/login.css" />

    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script>
        if (window.parent !== window.self) {
            document.write = '';
            window.parent.location.href = window.self.location.href;
            setTimeout(function() {
                document.body.innerHTML = '';
            }, 0);
        }
    </script>
</head>

<body>

    <div class="wrap">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <h1 class="text-center">后台管理</h1>
                    <form class="js-ajax-form" action="doLogin" method="post">
                        <div class="form-group">
                            <input type="text" id="input_username" class="form-control" name="username" placeholder="请输入用户名" title="请输入用户名" data-rule-required="true" data-msg-required="">
                        </div>

                        <div class="form-group">
                            <input type="password" id="input_password" class="form-control" name="password" placeholder="请输入密码" title="" data-rule-required="true" data-msg-required="">
                        </div>

                        <div class="form-group">
                            <div style="position: relative;">
                                <input type="text" name="captcha" placeholder="验证码" class="form-control captcha">
                                <img src="./captchaServlet" height="32px" width="150px" font-size="18px" alt="" onclick="changeCheckCode(this)" style="cursor: pointer;position:absolute;right:1px;top:1px;" >
								<script type="text/javascript">
									//图片点击事件
									function changeCheckCode(img) {
										img.src="captchaServlet?"+new Date().getTime();
                                       }
								</script>
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="hidden" name="redirect" value="">
                            <button class="btn btn-primary btn-block js-ajax-submit" type="submit" style="margin-left: 0px" data-loadingmsg="{:lang('LOADING')}">
								登录
                        	</button>
                        </div>

                    </form>
                </div>
            </div>

        </div>
    </div>

    <script src="../public/js/jquery-1.10.2.min.js" ></script>
    <script src="../public/js/wind.js" ></script>
    <script src="../public/admin/js/admin.js" ></script>
    <script>
        (function() {
            document.getElementById('input_username').focus();
        })();
    </script>
</body>

</html>