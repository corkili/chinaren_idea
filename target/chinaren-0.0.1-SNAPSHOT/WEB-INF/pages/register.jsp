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

    <title>注册</title>

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
                <form:form commandName="user" action="register" method="post"
                           id="register_form" name="register_form" onsubmit="return checkRegisterForm();">
                    <h1>
                        <strong><spring:message code="title.app_name"/></strong><br/><br/>
                        <spring:message code="register.form.title"/>
                    </h1>
                    <form:hidden path="userId" value="0" userId="userId" name="userId"/>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="email" style="text-align: right">
                                邮箱<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <form:input path="email" id="email" name="email" cssErrorClass="error" onchange="checkEmail()"
                                        type="text" cssClass="form-control" placeholder="Email" required="required"/>
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
                            <form:input path="password" id="password" name="password" cssErrorClass="error"
                                        onchange="checkPassword()" type="password" maxlength="32"
                                        cssClass="form-control" placeholder="Password" required="required"/>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="confirmPassword" style="text-align: right">
                                重复密码<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <input id="confirmPassword" name="confirmPassword" type="password" value="${confirmPassword}"
                                   class="form-control" placeholder="Confirm Password" required="required"
                                   onchange="checkConfirmPassword()" maxlength="32"/>
                        </div>
                    </div>

                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="name" style="text-align: right">
                                真实姓名<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <form:input path="name" id="name" name="name" cssErrorClass="error" onchange="checkName()"
                                        type="text" cssClass="form-control" placeholder="Real name" required="required"/>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="sex" style="text-align: right">
                                性别<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-4 col-sm-4 col-xs-4">
                            <label>
                                男&nbsp;&nbsp;&nbsp;<form:radiobutton path="sex" id="sex_m" name="sex" cssErrorClass="error" value="男"
                                            cssClass="iradio_flat-green" required="required" checked="checked"/>
                            </label>
                        </div>
                        <div class="col-md-4 col-sm-4 col-xs-4">
                            <label>
                                女&nbsp;&nbsp;&nbsp;<form:radiobutton path="sex" id="sex_f" name="sex" cssErrorClass="error" value="女"
                                            cssClass="iradio_flat-green" required="required"/>
                            </label>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="phone" style="text-align: right">
                                手机号<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <form:input path="phone" id="phone" name="phone" cssErrorClass="error" onchange="checkPhone()"
                                        type="text" cssClass="form-control" placeholder="Phone" required="required" maxlength="11"/>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="province" style="text-align: right">
                                省份<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <form:select path="province" id="province" name="province" cssErrorClass="error"
                                         onchange="change_city();checkProvince()"
                                        type="text" cssClass="select2-selection--single form-control" placeholder="Province" required="required">
                                <form:option value="0">---请选择省份---</form:option>
                                <c:forEach items="${provinces}" var="province">
                                    <form:option value="${province.province}">${province.province}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="city" style="text-align: right">
                                城市<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <form:select path="city" id="city" name="city" cssErrorClass="error"
                                         onchange="change_area();checkCity()"
                                         type="text" cssClass="select2-selection--single form-control" placeholder="City" required="required">
                                <form:option value="0">---请选择城市---</form:option>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group col-md-12 col-sm-12 col-xs-12">
                        <div class="col-md-3 col-sm-3 col-xs-3" style="text-align: right">
                            <label class="control-label right" for="area" style="text-align: right">
                                地区<span class="required">*</span>
                            </label>
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9">
                            <form:select path="area" id="area" name="area" cssErrorClass="error" onchange="checkArea()"
                                         type="text" cssClass="select2-selection--single form-control" placeholder="Area" required="required">
                                <form:option value="0">---请选择地区---</form:option>
                            </form:select>
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
                               value="<spring:message code="button.register"/>">
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
                        <p class="change_link">Already a member ?
                            <a href="login" class="to_register"> Log in </a>
                        </p>
                        <div class="clearfix"></div>
                        <br />
                        <div>
                            <h1><i class="fa fa-paw"></i> SCU-H.L.D.</h1>
                            <p>©2017 All Rights Reserved. SCU-H.L.D.!</p>
                        </div>
                    </div>
                </form:form>
            </section>
        </div>
    </div>
</div>

<script src="../vendors/jquery/dist/jquery.min.js"></script>
<script src="../js/validate.js"></script>
<script src="../js/email.js"></script>
<script type="text/javascript">
    var provinceMap = {};
    <c:forEach items="${provinces}" var="province">
    provinceMap['${province.province}'] = '${province.provinceId}';
    </c:forEach>

    var cityMap = {};
    var temp;
    <c:forEach items="${cities}" var="cityList">
    temp = {};
    <c:forEach items="${cityList.value}" var="city">
    temp['${city.cityId}'] = '${city.city}';
    </c:forEach>
    cityMap['${cityList.key}'] = temp;
    </c:forEach>

    var areaMap = {};
    <c:forEach items="${areas}" var="areaList">
    temp = {};
    <c:forEach items="${areaList.value}" var="area">
    temp['${area.areaId}'] = '${area.area}';
    </c:forEach>
    areaMap['${areaList.key}'] = temp;
    </c:forEach>

    function change_city() {
        var cityList = cityMap[provinceMap[document.getElementById('province').value]];
        if (cityList == null) {
            return;
        }
        var citySelector = document.getElementById('city');
        var count = 0;
        for (var key in cityList) {
            count++;
        }
        citySelector.length = count + 1
        var i = 1;
        for (key in cityList) {
            citySelector[i] = new Option(cityList[key], cityList[key]);
            i++;
        }
    }

    function change_area() {
        var cityList = cityMap[provinceMap[document.getElementById('province').value]];
        if (cityList == null) {
            return;
        }
        var citySelector = document.getElementById('city');
        if (citySelector.value === "0") {
            return;
        }
        var areaList = {};
        var count = 0;
        for (var key in cityList) {
            if (cityList[key] === citySelector.value) {
                areaList = areaMap[key];
            }
        }
        var count = 0;
        for (var key in areaList) {
            count++;
        }
        var areaSelector = document.getElementById('area');
        areaSelector.length = count + 1;
        var i = 1;
        for (var key in areaList) {
            areaSelector[i] = new Option(areaList[key], areaList[key]);
            i++;
        }
    }
    var wait = 60;

</script>

</body>
</html>

