/**
 * Created by ASUS on 2016/11/16.
 */
var curWwwPath = window.document.location.href;
$(document).ready(function () {
    var $province = $("select[name='province']");
    var url = $province.attr("url");
    var $city = $("select[name='city']")
    $province.change(function () {
        var parentId = $(this).val();
        $city.empty();
        getRegionOption($city, parentId, url);
    });
    getRegionOption($province, 0, url);
    var firstParentId = $province.val();
    getRegionOption($city, firstParentId, url);
});
function getRegionOption($element, parentId, url) {
    $.ajax({
        type: "GET",
        async: false,
        url: "/admin" + url,
        data: {
            parentId: parentId
        },
        dataType: "json",
        success: function (data) {
            if (data.success == true) {
                for (var i = 0; i < data.result.length; i++) {
                    $element.append("<option value=" + data.result[i].id + ">" + data.result[i].name + "</option>");
                }
            } else {
                alert("地区选择不可用!");
            }
        }
    });
}
