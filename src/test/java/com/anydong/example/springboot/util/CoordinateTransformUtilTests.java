package com.anydong.example.springboot.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CoordinateTransformUtilTests {
    private CoordinateTransformUtil transformUtil = new CoordinateTransformUtil();

    @Test
    public void gcj02ToWgs84Test() {
        double lng = 113.0;
        double lat = 22.0;
        double[] res = this.transformUtil.gcj02ToWgs84(lng, lat);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }

    @Test
    public void wgs84ToGcj02Test() {
        double lng = 116.481499;
        double lat = 39.990475;
        double[] res = this.transformUtil.wgs84ToGcj02(lng, lat);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
