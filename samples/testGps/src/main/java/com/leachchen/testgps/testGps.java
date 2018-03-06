package com.leachchen.testgps;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import gps.GpsAnalyzer;
import gps.event.EndOfStream;
import gps.event.GpsEvent;
import gps.event.GpsEventListener;
import gps.event.records.GPGSA;
import gps.event.records.GPRMC;

public class testGps {

    private static int distance;
    private static double beforeLatitude;
    private static double beforeLongitude;

    public static void main(String args[]) {
        GpsEventListener listenerAll = new ListenerAll();
        GpsEventListener listenerGPRMC = new ListenerGPRMC();
        GpsEventListener finishListener = new FinishListener();
        try {
            String filePath = System.getProperty("user.dir")+"/samples/testGps/gpsdata.txt";
            GpsAnalyzer analyzer = new GpsAnalyzer(new FileReader(filePath));
            analyzer.addGpsEventListener(GpsEvent.class, listenerAll);
            analyzer.addGpsEventListener(GPRMC.class, listenerGPRMC);
            analyzer.addGpsEventListener(EndOfStream.class, finishListener);
            analyzer.startAnalyzing();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class FinishListener implements GpsEventListener {

        public void eventFound(GpsEvent event) {
         System.out.println("总距离:" + (float) distance / 1000);
        }

    }


    static class ListenerAll implements GpsEventListener {

        public void eventFound(GpsEvent event) {
            if (event instanceof GPRMC) {
               // System.out.println("aaaaaaaaaaaa--------->GPRMC" );
            }else if(event instanceof GPGSA)
            {
               // System.out.println("bbbbbbbbbbbb--------->GPGSA" );
            }
        }

    }

    static class ListenerGPRMC implements GpsEventListener {


        public String date2yyyyMMddHHmm(Date date) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        }

        public void eventFound(GpsEvent event) {
            if (event instanceof GPRMC) {
                GPRMC gprmsc = (GPRMC) event;
                if (gprmsc.getLatitude() != 0) {
                    double[] data = EvilTransform.transform(gprmsc.getLatitude(), gprmsc.getLongitude());//Gps位置校正
                    String endDateStr = date2yyyyMMddHHmm(gprmsc.getReadingDate());
                    System.out.println("校正前:" + "getLatitude:" + gprmsc.getLatitude() + "--getLongitude:" + gprmsc.getLongitude() + " 校正后:" + "getLatitude:" + data[0] + "--getLongitude:" + data[1] + " 时间:" + endDateStr);

                    if (beforeLatitude != 0) {
                        distance += EvilTransform.GetShortDistance(beforeLongitude, beforeLatitude, data[1], data[0]);
                    }

                    beforeLatitude = data[0];
                    beforeLongitude = data[1];
                }
            }
        }
    }
}

