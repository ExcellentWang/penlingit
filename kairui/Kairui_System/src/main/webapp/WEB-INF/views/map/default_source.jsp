<%--
  Created by IntelliJ IDEA.
  User: kedong
  Date: 2017/6/28 0028
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>


    var gaodeMapLayer = new ol.layer.Tile({
        source: new ol.source.XYZ({
//            url: 'http://wprd0{1-4}.is.autonavi.com/appmaptile?lang=zh_cn&size=1&style=7&x={x}&y={y}&z={z}'
            url: 'http://mt2.google.cn/vt/lyrs=m@258000000&hl=zh-CN&gl=CN&src=app&x={x}&y={y}&z={z}&s=Ga'
        })
    });

    var GD_view = new ol.View({
        center: [114.28, 30.55],
        projection: 'EPSG:4326',
        zoom: 11
    });

    var view = new ol.View({
        center: [12728693, 3568015],
        zoom: 12
    });
    var map = new ol.Map({
        layers: [gaodeMapLayer],
        target: 'map',
        view: GD_view
    });

</script>
