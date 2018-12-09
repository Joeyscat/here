/**
 * @author Joey
 * @since 18-11-25 上午1:08
 * @version 1.0
 */



<!-- 创建地图 -->
var map = new AMap.Map('container', {
    zoom: 18,
    // viewMode: '3D',
    resizeEnable: true
});

map.on('complete', function () {
    console.log('地图加载完成');

});


// ******************************* 标记相关 **********************
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


// ******************************* 位置信息相关 **********************
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

$('#nearby').on('click', function () {
    console.log('获取附近的人的位置');
    let data = {};
    data.name = myPosition.name === undefined ? null : myPosition.name;
    data.createdBy = 'joey';
    data.lng = myPosition.lng;
    data.lat = myPosition.lat;
    $.ajax({
        url: "/position/nearby",
        data: JSON.stringify(data),
        type: 'post',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        success: function (result) {
            console.log('获取坐标成功 ', result);
            const positions = result.data;
            if (positions === null){
                console.log("附近没有人");
                return;
            }
            positions.forEach(function (position) {
                addMarker([position.lng, position.lat], endIcon, position.address, map);
            })
        }
    });
});

$('#random').on('click', function () {
    console.log('获取随机位置');

    $.ajax({
        url: "/position/random",
        method: 'POST',
        success: function (result) {
            console.log('获取坐标成功 ', result);
            const positions = result.data;
            positions.forEach(function (position) {
                addMarker([position.lng, position.lat], endIcon, position.address, map);
            })
        }
    });
});


// ******************************* 定位相关 **********************

// 保存当前位置信息
let myPosition = {};
// 定位插件
let geolocation;
/**
 * AMap.Geolocation插件
 * 实现定位
 */
AMap.plugin('AMap.Geolocation', function () {
    console.log('定位中...');
    geolocation = new AMap.Geolocation({
        // 是否使用高精度定位,默认true
        enableHighAccuracy: true,
        // 设置定位超时时间,默认:无穷大
        timeout: 10000,
        // 定位按钮停靠位置的偏移量,默认:Pixel(10,20)
        buttonOffset: new AMap.Pixel(10, 20),
        //定位成功后是否自动调整地图视野到定位点
        zoomToAccuracy: true
    });

    map.addControl(geolocation);

    geolocation.getCurrentPosition(function (status, result) {
        if (status === 'complete') {
            onComplete(result)
        } else {
            onError(result)
        }
    });
});

/**
 * 定位按钮绑定
 */
$('#get-location').on('click', function () {
    console.log('定位...');

    geolocation.getCurrentPosition(function (status, result) {
        if (status === 'complete') {
            onComplete(result)
        } else {
            onError(result)
        }
    });
});

// 完成定位
function onComplete(data) {
    // data是具体的定位信息
    myPosition.lng = data.position.lng;
    myPosition.lat = data.position.lat;

    // map.setCenter(new AMap.LngLat(myPosition.lng, myPosition.lat));

    console.log('定位结果：' + data.position);
    console.log('定位类别：' + data.location_type);
    if (data.accuracy) {
        console.log('精度：' + data.accuracy + ' 米');
    }
    console.log('是否经过偏移：' + (data.isConverted ? '是' : '否'));

    geoCoder.getAddress([myPosition.lng, myPosition.lat], function (status, result) {
        if (status === 'complete' && result.info === 'OK') {
            // result为对应的地理位置详细信息
            myPosition.address = result.regeocode.formattedAddress;
            console.log('当前位置 ', myPosition.address);
        } else {
            console.log('位置查询失败 ', result);
            myPosition.address = '未知';
        }
    });

    savePosition(myPosition);

    // document.getElementById('status').innerHTML = '定位成功';
    // var str = [];
    // str.push('定位结果：' + data.position);
    // str.push('定位类别：' + data.location_type);
    // if (data.accuracy) {
    // str.push('精度：' + data.accuracy + ' 米');
    // }//如为IP精确定位结果则没有精度信息
    // str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
    // document.getElementById('result').innerHTML = str.join('<br>');
}

//解析定位错误信息
function onError(data) {
    console.log('定位失败 ', data.message);
}

// ******************************* 地理编码相关 **********************

// 这个变量用来调用地理编码功能
let geoCoder;
// AMap.Geocoder 地理编码与逆地理编码服务，提供地址与坐标间的相互转换
AMap.plugin('AMap.Geocoder', function () {
    geoCoder = new AMap.Geocoder({
        // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
        city: '010'
    });
});
