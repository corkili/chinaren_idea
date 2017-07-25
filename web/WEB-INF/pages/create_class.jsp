<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/4/8
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="title.app_name"/></title>

    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
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
    <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">

</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%@ include file="left_top.jspf"%>

        <!-- top navigation -->
        <%@ include file="right_top.jspf"%>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3><spring:message code="title.app_name" /></h3>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>创建班级</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <!-- start form for validation -->
                                <form:form commandName="class" action="createClass" method="post"
                                           id="create_class_form" name="create_class_form" onsubmit="return checkCreateClassForm();">
                                    <form:hidden path="managerId" id="managerId" name="managerId"/>
                                    <label for="className">班级名称 <span class="required">*</span> :</label>
                                    <form:input path="className" type="text" id="className" class="form-control"
                                                name="className" required="required" onchange="checkClassName()"/>

                                    <label for="school">学校 <span class="required">*</span> :</label>
                                    <form:input path="school" type="text" id="school" class="form-control"
                                                name="school" required="required" onchange="checkSchool()"/>

                                    <label for="gradeYear">年级 <span class="required">*</span> :</label>
                                    <form:input path="gradeYear" type="text" id="gradeYear" class="form-control"
                                                name="school" required="required" placeholder="e.g. 2017" onchange="checkYear()"/>

                                    <label for="province">省份 <span class="required">*</span> :</label>
                                    <form:select path="province" id="province" name="province" class="form-control"
                                                 required="required" onchange="checkProvince();change_city();">
                                        <form:option value="0">---请选择省份---</form:option>
                                        <c:forEach items="${provinces}" var="province">
                                            <form:option value="${province.province}">${province.province}</form:option>
                                        </c:forEach>
                                    </form:select>

                                    <label for="city">城市 <span class="required">*</span> :</label>
                                    <form:select path="city" id="city" name="city" class="form-control"
                                                 required="required" onchange="checkCity();change_area();">
                                        <form:option value="0">---请选择城市---</form:option>
                                    </form:select>

                                    <label for="area">地区 <span class="required">*</span> :</label>
                                    <form:select path="area" id="area" name="area" class="form-control"
                                                 required="required" onchange="checkArea()">
                                        <form:option value="0">---请选择地区---</form:option>
                                    </form:select>

                                    <label for="decription">个人简介 :</label>
                                    <form:textarea path="decription" id="decription" required="required"
                                                   class="form-control" name="decription" data-parsley-trigger="keyup"
                                                   data-parsley-maxlength="200"
                                              data-parsley-validation-threshold="10"/>

                                    <br/>
                                    <input type="submit" class="btn btn-primary" value="<spring:message code="button.submit"/>"/>
                                    <c:if test="${has_error}">
                                        <br/>
                                            <p style="color: red">${error_message}</p>
                                    </c:if>
                                </form:form>
                                <!-- end form for validations -->

                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">
                <spring:message code="text.footer"/>
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
</div>

<!-- jQuery -->
<script src="../vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../vendors/nprogress/nprogress.js"></script>
<!-- bootstrap-progressbar -->
<script src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="../vendors/iCheck/icheck.min.js"></script>
<!-- bootstrap-wysiwyg -->
<script src="../vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
<script src="../vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
<script src="../vendors/google-code-prettify/src/prettify.js"></script>
<!-- jQuery Tags Input -->
<script src="../vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
<!-- Switchery -->
<script src="../vendors/switchery/dist/switchery.min.js"></script>
<!-- Select2 -->
<script src="../vendors/select2/dist/js/select2.full.min.js"></script>
<!-- Parsley -->
<script src="../vendors/parsleyjs/dist/parsley.min.js"></script>
<!-- Autosize -->
<script src="../vendors/autosize/dist/autosize.min.js"></script>
<!-- jQuery autocomplete -->
<script src="../vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
<!-- starrr -->
<script src="../vendors/starrr/dist/starrr.js"></script>
<!-- Custom Theme Scripts -->
<script src="../build/js/custom.min.js"></script>
<script src="../js/validate.js"></script>
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
        var citySelector = document.getElementById('city');
        if (cityList == null) {
            citySelector.length = 1;
            document.getElementById('area').length = 1;
            return;
        }
        var count = 0;
        for (var key in cityList) {
            count++;
        }
        citySelector.length = count + 1;
        var i = 1;
        for (key in cityList) {
            citySelector[i] = new Option(cityList[key], cityList[key]);
            i++;
        }
    }

    function change_area() {
        var cityList = cityMap[provinceMap[document.getElementById('province').value]];
        var citySelector = document.getElementById('city');
        var areaSelector = document.getElementById('area');
        if (cityList == null) {
            citySelector.length = 1;
            return;
        }
        if (citySelector.value === "0") {
            areaSelector.length = 1;
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
        areaSelector.length = count + 1;
        var i = 1;
        for (var key in areaList) {
            areaSelector[i] = new Option(areaList[key], areaList[key]);
            i++;
        }
    }

</script>


</body>
</html>

