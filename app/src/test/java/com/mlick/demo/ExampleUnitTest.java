package com.mlick.demo;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest implements Serializable {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        System.out.print("out");
    }

    @Test
    public void showMainMethod() {
        System.out.print(MainActivity.class.getName());
    }

    @Test
    public void testMapValue() {
        Map<Integer, ArrayList<TestBean>> map = new HashMap<>();
        ArrayList<TestBean> arrayList = new ArrayList();
        arrayList.add(new TestBean("s1", false));
        arrayList.add(new TestBean("s2", false));
        arrayList.add(new TestBean("s3", false));

        ArrayList<TestBean> arrayList2 = new ArrayList();
        arrayList2.add(new TestBean("s1", false));
        arrayList2.add(new TestBean("s2", false));
        arrayList2.add(new TestBean("s3", false));

        //浅拷贝
//        ArrayList<TestBean> arrayList3 = new ArrayList<>(Arrays.asList(new TestBean[arrayList.size()]));
//        Collections.copy(arrayList3, arrayList);
        ArrayList<TestBean> arrayList3 = null;
        try {
            arrayList3 = (ArrayList<TestBean>) deepCopy(arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        map.put(1, arrayList);
        map.put(2, arrayList2);
        map.put(3, arrayList3);


        TestBean testBean = map.get(1).get(0);
        if (testBean.isClick) {
            testBean.isClick = false;
        } else {
            testBean.isClick = true;
        }

        System.out.print(map.get(1).get(0).isClick);
        System.out.print(map.get(2).get(0).isClick);
        System.out.print(map.get(3).get(0).isClick);
    }

    public class TestBean implements Serializable {
        public String name;
        public boolean isClick;

        public TestBean(String s1, boolean b) {
            this.name = s1;
            this.isClick = b;
        }
    }

    @Test
    public void testStringButter() {
        StringBuffer buffer = new StringBuffer("123456789");
        buffer.deleteCharAt(buffer.length() - 1);
        System.out.print(buffer.toString());
    }

    @Test
    public void testArrayList() {
        ArrayList<SetPlusGvBean> plusGvList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            SetPlusGvBean b1 = new SetPlusGvBean(i, -1);
            SetPlusGvBean b2 = new SetPlusGvBean(i);
            SetPlusGvBean b3 = new SetPlusGvBean(i);
            SetPlusGvBean b4 = new SetPlusGvBean(i);
        }
    }

    public class SetPlusGvBean {
        public int weekItem;//代表周期几
        public int flag = 0;// -1 表示星期  || 0 表示未选中
        public String name;// 表示名字

        public SetPlusGvBean(int i) {
            weekItem = i;
        }

        public SetPlusGvBean(int flag, int i) {
            weekItem = i;
            this.flag = flag;
        }
    }

    public ArrayList deepCopy(ArrayList src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        return (ArrayList) in.readObject();
    }

    @Test
    public void testStringRelace() {
        String res = "事实上,ddd,嘿嘿";
        res = res.replaceAll("嘿嘿", "哈哈");
        System.out.print(res);
    }


    @Test
    public void testNetWork() {
        long begin1;
        begin1 = System.currentTimeMillis();
        System.out.println(getNetWorkIP());
        System.out.println((System.currentTimeMillis() - begin1));

    }

    @Test
    public void testNetWork2() {
        long begin2;
        begin2 = System.currentTimeMillis();
        System.out.println(getNetWorkIP2());
        System.out.println((System.currentTimeMillis() - begin2));

    }


    public static String getNetWorkIP() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress
                            .isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        sb.append(inetAddress.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            return "";
        }
        return sb.toString();
    }

    public static String getNetWorkIP2() {
        StringBuffer sb = new StringBuffer();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress
                            .isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        sb.append(inetAddress.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            return "";
        }
        return sb.toString();
    }

    @Test
    public void testMemory() {
        long startTime = System.currentTimeMillis();
        Integer sum = 0;
        for (int i = 0; i < 50000; i++) {
            sum += i;
        }
        System.out.println(">>>" + (System.currentTimeMillis() - startTime));

        long startTime2 = System.currentTimeMillis();
        int sum2 = 0;
        for (int i = 0; i < 50000; i++) {
            sum2 += i;
        }
        System.out.print(">>>" + (System.currentTimeMillis() - startTime2));
    }
}