<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/7/24
  Time: 10:36
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

    <title>登录</title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="../vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
</head>

<body class="login">
<div>
    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form:form commandName="user" action="login" method="post" id="login_form" name="login_form">
                    <h1>
                        <strong><spring:message code="title.app_name"/></strong><br/><br/>
                        <spring:message code="login.form.title"/>
                    </h1>
                    <form:hidden path="userId" value="0" userId="userId" name="userId"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <form:input path="email" id="email" name="email"
                                    type="email" cssClass="form-control" placeholder="Email" required="required"/>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <form:input path="password" id="password" name="password"
                                    type="password" cssClass="form-control" placeholder="Password" required="required"/>
                    </div>
                    <div class="col-md-8 col-sm-8 col-xs-8">
                        <input type="text" id="captcha" name="captcha" class="form-control"
                               maxlength="6" placeholder="Verification code" required="required"/>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-4">
                        <img id="captchaImage" src="captcha" onclick="reImg()"/>
                    </div>
                    <c:if test="${has_error}">
                        <div class="col-md-12 col-sm-12 col-xs-12 has-error">
                            <p style="color: red">${error_message}</p>
                        </div>
                    </c:if>
                    <script type="text/javascript">
                        function reImg(){
                            var img = document.getElementById("captchaImage");
                            img.src = "captcha?timestamp=" + (new Date()).valueOf();
                        }
                    </script>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <input id="submit" type="submit" class="btn btn-default submit"
                               value="<spring:message code="button.login"/>" tabindex="3">
                        <a id="to_register" href="register" style="display: none"></a>
                        <input type="button" class="btn btn-default submit" value="<spring:message code="button.register"/>"
                               onclick="document.getElementById('to_register').click();" tabindex="4">
                    </div>

                    <div class="clearfix"></div>
                    <div class="separator">
                        <p class="change_link">
                            <a href="resetPWD" class="to_register"> 忘记密码？ </a>
                        </p>
                        <div class="clearfix"></div>
                        <br />
                        <div>
                            <h1><i class="fa fa-paw"></i> SCU-No.1</h1>
                            <p>©2017 All Rights Reserved. SCU-No.1</p>
                        </div>
                    </div>
                </form:form>
            </section>
        </div>
    </div>
</div>

<script src="../js/validate.js"></script>

</body>
</html>
