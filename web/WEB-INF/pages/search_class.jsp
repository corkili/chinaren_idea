<%--
  Created by IntelliJ IDEA.
  User: 李浩然
  Date: 2017/7/24
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title><spring:message code="title.app_name" /></title>

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
        th{
            text-align: center;
        }
        td{
            text-align: center;
        }
    </style>

    <script type="text/javascript">
        function displaySearchForm() {
            document.getElementById('searchDiv').style.display='';
        }
        
        function hideSearch() {
            document.getElementById('searchDiv').style.display='none';
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
                        <h3>我的班级</h3>
                    </div>
                </div>

                <div class="clearfix"></div>

                <!-- search form -->
                <div class="row" id="searchDiv" style="display: none">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>搜索班级</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">

                                <!-- start form for validation -->
                                <form action="searchClass" method="post" id="search_class_form" name="search_class_form"
                                      onsubmit="hideSearch();">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="col-md-4 col-sm-4 col-xs-4">
                                            <label for="province">省份 :</label>
                                            <select id="province" name="province" class="form-control" onchange="change_city();">
                                                <option value="">---请选择省份---</option>
                                                <c:forEach items="${provinces}" var="province">
                                                    <option value="${province.province}">${province.province}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4">
                                            <label for="city">城市 :</label>
                                            <select id="city" name="city" class="form-control" onchange="change_area();">
                                                <option value="">---请选择城市---</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4">
                                            <label for="area">地区 :</label>
                                            <select id="area" name="area" class="form-control">
                                                <option value="">---请选择地区---</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="ln_solid"></div>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="col-md-4 col-sm-4 col-xs-4">
                                            <label for="school">学校 :</label>
                                            <input type="text" id="school" class="form-control" name="school"/>
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4">
                                            <label for="gradeYear">年级 :</label>
                                            <input type="text" id="gradeYear" class="form-control" name="gradeYear" placeholder="e.g. 2017"/>
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4">
                                            <input type="hidden" name="action" value="1">
                                            <label for="className">班级名称 :</label>
                                            <input type="text" id="className" class="form-control" name="className" />
                                        </div>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="ln_solid"></div>
                                    </div>
                                    <div class="col-md-12 col-sm-12 col-xs-12" style="text-align: center">
                                        <input type="submit" class="btn btn-success" value="搜索"/>
                                        <input type="reset" class="btn btn-primary" value="重置"/>
                                        <input type="button" class="btn btn-dark" value="取消" onclick="hideSearch()"/>
                                    </div>
                                </form>
                                <!-- end form for validations -->

                            </div>
                        </div>
                    </div>
                </div>

                <!-- search class list -->
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>班级列表</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>

                            <div class="x_content">
                                <div class="table-responsive">
                                    <div>
                                        <button id="searchBtn" class="btn btn-success" style="margin-right: 5px;" type="button"
                                                onclick="displaySearchForm()">搜索班级</button>
                                        <br/>
                                    </div>
                                    <table id="datatable" class="table table-striped jambo_table" style="white-space: nowrap;">
                                        <thead>
                                        <tr class="headings">
                                            <th class="column-title">班级ID</th>
                                            <th class="column-title">班级名称</th>
                                            <th class="column-title">班长</th>
                                            <th class="column-title">学校</th>
                                            <th class="column-title">年级</th>
                                            <th class="column-title">地址</th>
                                            <th class="column-title">班级介绍</th>
                                            <th class="column-title no-link last"><span class="nobr">操作</span></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${classes}" varStatus="status">
                                            <c:choose>
                                                <c:when test="${status.index % 2 == 0}">
                                                    <tr class="even pointer">
                                                </c:when>
                                                <c:otherwise>
                                                    <tr class="odd pointer">
                                                </c:otherwise>
                                            </c:choose>
                                            <td class=" ">${classes[status.index].classId}</td>
                                            <td class=" ">${classes[status.index].className}</td>
                                            <td class=" ">${classes[status.index].managerName}</td>
                                            <td class=" ">${classes[status.index].school}</td>
                                            <td class=" ">${classes[status.index].gradeYear}</td>
                                            <td class=" ">${classes[status.index].province}&nbsp;
                                                    ${classes[status.index].city}&nbsp;${classes[status.index].area}</td>
                                            <td class=" ">
                                                <button type="button" class="btn btn-round btn-info"
                                                        onclick="sAlert('${classes[status.index].className}-班级介绍','${classes[status.index].description}')">
                                                    查看
                                                </button>
                                            </td>
                                            <td class=" last">
                                                <c:if test="${classes[status.index].managerId != user_id
                                                and not classes[status.index].classmates.contains(user_id)
                                                and not classes[status.index].notApplys.contains(user_id)}">
                                                    <form action="searchClass" method="post">
                                                        <input type="hidden" name="action" value="2">
                                                        <input type="hidden" name="class_id" value="${classes[status.index].classId}">
                                                        <input type="submit" value="申请加入班级"
                                                               onclick="return confirm('确认申请加入班级-${classes[status.index].className}？');"
                                                               class="btn btn-round btn-success">
                                                    </form>
                                                </c:if>
                                            </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
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
<!-- iCheck -->
<script src="../vendors/iCheck/icheck.min.js"></script>
<!-- Datatables -->
<script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
<script src="../vendors/jszip/dist/jszip.min.js"></script>
<script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
<script src="../vendors/pdfmake/build/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="../build/js/custom.min.js"></script>

<script src="../js/validate.js"></script>
<script src="../js/cusDialog.js"></script>

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
