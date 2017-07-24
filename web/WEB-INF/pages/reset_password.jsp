<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/7/24
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>找回密码</title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="../vendors/animate.css/animate.min.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-wysiwyg -->
    <link href="../vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
    <!-- Select2 -->
    <link href="../vendors/select2/dist/css/select2.min.css" rel="stylesheet">
    <!-- Switchery -->
    <link href="../vendors/switchery/dist/switchery.min.css" rel="stylesheet">
    <!-- starrr -->
    <link href="../vendors/starrr/dist/starrr.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">

</head>
<body class="login">
<div>
    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form action="/resetPWD" method="post" id="reset_form" name="reset_form"
                      onsubmit="return checkResetPasswordForm();">
                    <h1>
                        <strong><spring:message code="title.app_name"/></strong><br/><br/>
                        <spring:message code="reset.form.title"/>
                    </h1>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="email" style="text-align: right">
                                邮箱<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <input id="email" name="email" onchange="checkEmail()" value="${email}"
                                        type="text" class="form-control" placeholder="Email" required="required"/>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-6 col-sm-6 col-xs-6" style="text-align: right">
                            <label class="control-label right" for="email" style="text-align: right">
                                <button type="button" class="btn btn-default" id="sendEmail" name="sendEmail"
                                        onclick="sendEmailFun()">免费获取验证码</button>
                            </label>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-6">
                            <input id="code" name="code" type="text" onchange="checkCode()" maxlength="6"
                                   class="form-control" placeholder="Email Code" required="required" value="${code}"/>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="password" style="text-align: right">
                                密码<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <input id="password" name="password"
                                        onchange="checkPassword()" type="password" maxlength="32" value="${password}"
                                        class="form-control" placeholder="Password" required="required"/>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="confirmPassword" style="text-align: right">
                                重复密码<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <input id="confirmPassword" name="confirmPassword" type="password"
                                   class="form-control" placeholder="Confirm Password" required="required"
                                   onchange="checkConfirmPassword()" maxlength="32"/>
                        </div>
                    </div>

                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-4 col-sm-4 col-xs-4">
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <img id="captchaImage" src="captcha" onclick="reImg()"/>
                            </div>
                        </div>
                        <div class="col-md-1 col-sm-1 col-xs-1"></div>
                        <div class="col-md-7 col-sm-7 col-xs-7">
                            <input type="text" id="captcha" name="captcha" class="form-control"
                                   onchange="checkVerificationCode()"
                                   maxlength="6" placeholder="Verification code" required="required"/>
                        </div>
                    </div>
                    <script type="text/javascript">
                        function reImg(){
                            var img = document.getElementById("captchaImage");
                            img.src = "captcha?timestamp=" + (new Date()).valueOf();
                        }
                    </script>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <input id="submit" type="submit" class="btn btn-default submit"
                               value="<spring:message code="button.submit"/>">
                        <input id="reset" type="reset" class="btn btn-default submit"
                               value="<spring:message code="button.reset"/>">
                    </div>
                    <c:if test="${has_error}">
                        <div class="col-md-12 col-sm-12 col-xs-12 has-error">
                            <p style="color: red">${error_message}</p>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">Remember password ?
                            <a href="login" class="to_register"> Log in </a>
                        </p>
                        <div class="clearfix"></div>
                        <br />
                        <div>
                            <h1><i class="fa fa-paw"></i> SCU-H.L.D.</h1>
                            <p>©2017 All Rights Reserved. SCU-H.L.D.!</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>

<script src="../vendors/jquery/dist/jquery.min.js"></script>
<script src="../js/validate.js"></script>
<script src="../js/email.js"></script>

</body>
</html>

