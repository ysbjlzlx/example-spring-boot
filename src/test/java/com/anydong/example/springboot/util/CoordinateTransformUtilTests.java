package com.anydong.example.springboot.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CoordinateTransformUtilTests {
    CoordinateTransformUtil coordinateTransformUtil = new CoordinateTransformUtil();

    /**
     * 高德 Web 服务 API 坐标转换服务
     * Doc: https://lbs.amap.com/api/webservice/guide/api/convert
     */
    @Test
    public void wgs84ToGcj02Test() {
        double lng = 116.481499; //116.487585177952
        double lat = 39.990475; //39.991754014757
        double[] gcj02 = this.coordinateTransformUtil.wgs84ToGcj02(lng, lat);
        System.out.println(gcj02[0]);
        System.out.println(gcj02[1]);
    }

    /**
     * 校验：https://tool.lu/coordinate/
     */
    @Test
    public void gcj02ToWgs84Test() {
        double lng = 116.487585177952; //116.481499
        double lat = 39.991754014757; //39.990475
        double[] wgs84 = this.coordinateTransformUtil.gcj02ToWgs84(lng, lat);
        System.out.println(wgs84[0]);
        System.out.println(wgs84[1]);
    }

    /**
     * 百度 Web 服务 API 坐标转换服务
     * Doc: http://lbs.baidu.com/index.php?title=webapi/guide/changeposition
     * URL: https://api.map.baidu.com/geoconv/v1/?coords=116.481499,39.990475&from=1&to=5&ak=ACcbe2e69d6aa971c782d0da8cc560da
     */
    @Test
    public void wgs84ToBd09Test() {
        double lng = 116.481499; //116.49412480447363
        double lat = 39.990475; //39.997721127970269
        double[] bd09 = this.coordinateTransformUtil.wgs84ToBd09(lng, lat);
        System.out.println(bd09[0]);
        System.out.println(bd09[1]);
    }

    /**
     * 校验：https://tool.lu/coordinate/
     */
    @Test
    public void bd09ToWgs84Test() {
        double lng = 116.49412480447363; //116.481499
        double lat = 39.997721127970269; //39.990475
        double[] wgs84 = this.coordinateTransformUtil.bd09ToWgs84(lng, lat);
        System.out.println(wgs84[0]);
        System.out.println(wgs84[1]);
    }

    /**
     * url: https://api.map.baidu.com/geoconv/v1/?coords=116.481499,39.990475&from=3&to=5&ak=ACcbe2e69d6aa971c782d0da8cc560da
     */
    @Test
    public void gcj02ToBd09Test() {
        double lng = 116.481499; //116.48806956500994
        double lat = 39.990475; //39.9963310359532
        double[] bd09 = this.coordinateTransformUtil.gcj02ToBd09(lng, lat);
        System.out.println(bd09[0]);
        System.out.println(bd09[1]);
    }
    
    /**
     * 校验：https://tool.lu/coordinate/
     */
    @Test
    public void bd09ToGcj02Test() {
        double lng = 116.48806956500994; //116.481499
        double lat = 39.9963310359532; //39.990475
        double[] gcj02 = this.coordinateTransformUtil.bd09ToGcj02(lng, lat);
        System.out.println(gcj02[0]);
        System.out.println(gcj02[1]);
    }

}
