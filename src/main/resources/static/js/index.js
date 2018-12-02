/**
 * @author Joey
 * @since 18-11-25 上午1:08
 * @version 1.0
 */

// 实例化点标记
function addMarker(position, icon, title, map) {
    marker = new AMap.Marker({
        icon: icon,
        position: position,
        // offset: new AMap.Pixel(-13, -30),
        title: title
    });
    marker.setMap(map);
}

// 创建一个 Icon
var startIcon = new AMap.Icon({
    // 图标尺寸
    size: new AMap.Size(25, 34),
    // 图标的取图地址
    image: '//a.amap.com/jsapi_demos/static/demo-center/icons/dir-marker.png',
    // 图标所用图片大小
    imageSize: new AMap.Size(135, 40),
    // 图标取图偏移量
    imageOffset: new AMap.Pixel(-9, -3)
});

// 创建一个 icon
var endIcon = new AMap.Icon({
    size: new AMap.Size(25, 34),
    image: '//a.amap.com/jsapi_demos/static/demo-center/icons/dir-marker.png',
    imageSize: new AMap.Size(135, 40),
    imageOffset: new AMap.Pixel(-95, -3)
});


/**
 * 保存位置信息
 *
 * @param position
 */
function savePosition(position) {
    let data = {};
    data.name = position.name === undefined ? '未知' : position.name;
    data.address = position.address;
    data.lng = position.lng;
    data.lat = position.lat;

    $.ajax({
        url: "/position/save",
        data: JSON.stringify(data),
        type: 'post',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        success: function (result) {
            if (result.resultCode === '1') {
                console.log('保存位置信息成功 ', result);
            } else {
                console.error('保存位置信息失败 ', result);
            }

        }
    });
}