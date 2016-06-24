<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>管理中心</title>
    <link href="../css/common.css" rel="stylesheet" type="text/css">
    <link rel="Shortcut Icon" href="../image/logo.ico" />
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <link href="../dist/css/timeline.css" rel="stylesheet">
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="../bower_components/morrisjs/morris.css" rel="stylesheet">
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>

<div id="wrapper">
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <jsp:include page="top.jsp"></jsp:include>
        <jsp:include page="left.jsp"></jsp:include>
    </nav>
    <div id="page-wrapper">
        <div class="row" id="ajaxJsp">

            <!-- /.col-lg-12 -->
        </div>
    </div>
</div>
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>
<script src="../bower_components/raphael/raphael-min.js"></script>
<script src="../dist/js/sb-admin-2.js"></script>
<script charset="utf-8" src="../kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
</body>
<script>
    KindEditor.ready(function(K) {
        window.editor = K.create('textarea[name="detail"]');
    });
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $("a[name='ajaxModel']").click(function(){
            var url = $(this).attr("url");
            $.ajax({
                type: "GET",
                url: "/admin"+url,
                dataType: "html",
                success: function(data){
                    if(data.indexOf("loginForm")>0) {
                        window.location.href = "/admin/login.html";
                    }else {
                        $("#ajaxJsp").html(data);
                    }
                }
            });
        });
    });
</script>
