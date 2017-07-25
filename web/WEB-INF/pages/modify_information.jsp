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

    <style>
        .file {
            position: relative;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
    </style>

    <script type="text/javascript">
        //定义id选择器
        function Id(id){
            return document.getElementById(id);
        }
        //入口函数，两个参数分别为<input type='file'/>的id，还有一个就是图片的id，然后会自动根据文件id得到图片，然后把图片放到指定id的图片标签中
        function changeToop(fileid,imgid){
            var file = Id(fileid);
            if(file.value==''){
                //设置默认图片
                Id("myimg").src='';
            }else{
                preImg(fileid,imgid);
            }
        }
        //获取input[file]图片的url Important
        function getFileUrl(fileId) {
            var url;
            var file = Id(fileId);
            var agent = navigator.userAgent;
            if (agent.indexOf("MSIE")>=1) {
                url = file.value;
            } else if(agent.indexOf("Firefox")>0) {
                url = window.URL.createObjectURL(file.files.item(0));
            } else if(agent.indexOf("Chrome")>0) {
                url = window.URL.createObjectURL(file.files.item(0));
            }
            var extIndex = file.value.lastIndexOf(".");
            var ext = file.value.substring(extIndex,file.value.length).toUpperCase();
            if (ext != ".PNG") {
                alert("只允许上传PNG格式的图片！");
                Id('upload_file').disabled = true;
                return "";
            }
            Id('upload_file').disabled = false;
            return url;
        }
        //读取图片后预览
        function preImg(fileId,imgId) {
            var imgPre =Id(imgId);
            imgPre.src = getFileUrl(fileId);
        }
    </script>
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
                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>个人信息修改<small>${user.name}</small></h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <!-- start form for validation -->
                                <form:form commandName="user" action="modifyInformation" method="post"
                                           id="modify_info_form" name="modify_info_form" onsubmit="return checkModifyInformationForm();">
                                    <form:hidden path="userId" id="userId" name="userId" value="${user.userId}"/>
                                    <form:hidden path="email" id="email" name="email" value="${user.email}"/>
                                    <form:hidden path="password" id="password" name="password" value="${user.password}"/>
                                    <form:hidden path="headImage" id="headImage" name="headImage" value="${user.headImage}"/>
                                    <label for="name">姓名 <span class="required">*</span> :</label>
                                    <form:input path="name" type="text" id="name" class="form-control" name="name" required="required"
                                        onchange="checkName()"/>

                                    <label>性别 <span class="required">*</span> :</label>
                                    <p>
                                        男：
                                        <form:radiobutton path="sex" class="flat" name="gender" id="genderM" value="男" required="required"/> 女:
                                        <form:radiobutton path="sex" class="flat" name="gender" id="genderF" value="女" />
                                    </p>

                                    <label for="phone">手机号 <span class="required">*</span> :</label>
                                    <form:input path="phone" type="text" id="phone" class="form-control" name="phone"
                                                data-parsley-trigger="change" required="required" onchange="checkPhone()"/>

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

                                    <label for="introduction">个人简介 :</label>
                                    <form:textarea path="introduction" id="introduction" required="required"
                                                   class="form-control" name="introduction" data-parsley-trigger="keyup"
                                                   data-parsley-maxlength="100"
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
                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>头像修改<small>${user.name}</small></h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <!-- start form for validation -->
                                <form action="modifyHeadImage" method="post"
                                      enctype="multipart/form-data" class="form-horizontal">
                                    <div class="ln_solid"></div>
                                    <div class="form-group">
                                        <div class="col-md-5 col-sm-5 col-xs-5">
                                            <a class="file"><spring:message code="tip.upload"/>
                                                <input type="file" name="image" id="file_selector" class="btn btn-round"
                                                       placeholder="<spring:message code="tip.upload"/>" required="required"
                                                       accept=".png" onchange="changeToop('file_selector', 'imagePreview');">
                                            </a>
                                        </div>
                                        <div class="col-md-1  col-sm-1 col-xs-1">
                                            <input type="submit" value="<spring:message code="button.submit" />"
                                                   class="btn btn-round btn-success" id="upload_file" onclick="return checkInput(this.form);">
                                            <script>
                                                function checkInput(form) {
                                                    if (form.image.value == null || form.image.value == '') {
                                                        alert("请选择一个文件");
                                                        return false;
                                                    }
                                                    return true;
                                                }
                                            </script>
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <div id="preview" class="col-sm-5 col-md-5 col-xs-5">
                                        <img id="imagePreview" src="" alt="" class="img-circle img-responsive">
                                    </div>
                                </form>
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

</script>


</body>
</html>

