<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <title>{:lang('ADMIN_CENTER')}</title>
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="robots" content="noindex,nofollow">

    <!-- HTML5 shim for IE8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <![endif]-->

    <link href="./../public/assets/themes/flatadmin/bootstrap.min.css" rel="stylesheet">
    <link href="./../public/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="./../public/css/login.css" rel="stylesheet">

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
                    <form class="js-ajax-form" action="{:url('public/doLogin')}" method="post">
                        <div class="form-group">
                            <input type="text" id="input_username" class="form-control" name="username" placeholder="{:lang('USERNAME_OR_EMAIL')}" title="{:lang('USERNAME_OR_EMAIL')}" value="" data-rule-required="true" data-msg-required="">
                        </div>

                        <div class="form-group">
                            <input type="password" id="input_password" class="form-control" name="password" placeholder="{:lang('PASSWORD')}" title="{:lang('PASSWORD')}" data-rule-required="true" data-msg-required="">
                        </div>

                        <div class="form-group">
                            <div style="position: relative;">
                                <input type="text" name="captcha" placeholder="验证码" class="form-control captcha">
                                <captcha height="32" width="150" font-size="18" style="cursor: pointer;position:absolute;right:1px;top:1px;" />
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
    <script src="__PUBLIC_THEME__/js/jquery-1.10.2.min.js"></script>
    <script src="__PUBLIC_THEME__/js/wind.js"></script>
    <script src="__ADMIN_THEME__/js/admin.js"></script>
    <script>
        (function() {
            document.getElementById('input_username').focus();
        })();
    </script>
</body>

</html>