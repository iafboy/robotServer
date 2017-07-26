package io.pivotal.microservices.services;

import io.pivotal.microservices.model.Barycenter;
import io.pivotal.microservices.model.Coordinate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service(value="GisMapTransService")
public class GisMapTransService {
    protected Logger logger = Logger.getLogger(GisMapTransService.class
            .getName());

    public double[] trilateration(double x1, double y1, double d1, double x2, double y2, double d2, double x3, double y3, double d3) {
        double[] d = { 0.0, 0.0 };
        double a11 = 2 * (x1 - x3);
        double a12 = 2 * (y1 - y3);
        double b1 = Math.pow(x1, 2) - Math.pow(x3, 2) + Math.pow(y1, 2) - Math.pow(y3, 2) + Math.pow(d3, 2) - Math.pow(d1, 2);
        double a21 = 2 * (x2 - x3);
        double a22 = 2 * (y2 - y3);
        double b2 = Math.pow(x2, 2) - Math.pow(x3, 2) + Math.pow(y2, 2) - Math.pow(y3, 2) + Math.pow(d3, 2) - Math.pow(d2, 2);

        d[0] = (b1 * a22 - a12 * b2) / (a11 * a22 - a12 * a21);
        d[1] = (a11 * b2 - b1 * a21) / (a11 * a22 - a12 * a21);
        return d;

    }

    /**
     * 三角形质心定位算法实现
     * Triangle centroid location
     * @param r1 坐标1为圆心,距离为半径
     * @param r2
     * @param r3
     * @return
     */

    public Coordinate trilateration(Barycenter r1, Barycenter r2, Barycenter r3) {
        Coordinate p1 = null;// 有效交叉点1
        Coordinate p2 = null;// 有效交叉点2
        Coordinate p3 = null;// 有效交叉点3
        Coordinate zx=new Coordinate();//计算三点质心
        List<Coordinate> jds1 = jd(r1.getX(), r1.getY(), r1.getR(), r2.getX(), r2.getY(), r2.getR());// r1,r2交点
        if (jds1 != null && !jds1.isEmpty()) {
            for (Coordinate jd : jds1) {//有交点
                if (p1==null&&Math.pow(jd.getX()-r3.getX(),2) + Math.pow(jd.getY()-r3.getY(),2) <= Math.pow(r3.getR(),2)) {
                    p1 = jd;
                }else if(p1!=null){
                    if(Math.pow(jd.getX()-r3.getX(),2) + Math.pow(jd.getY()-r3.getY(),2)<= Math.pow(r3.getR(),2)){
                        if(Math.sqrt(Math.pow(jd.getX()-r3.getX(),2) + Math.pow(jd.getY()-r3.getY(),2))>Math.sqrt(Math.pow(p1.getX()-r3.getX(),2) + Math.pow(p1.getY()-r3.getY(),2))){
                            p1 = jd;
                        }
                    }
                }
            }
        } else {//没有交点定位错误
            return null;
        }
        List<Coordinate> jds2 = jd(r1.getX(), r1.getY(), r1.getR(), r3.getX(), r3.getY(), r3.getR());// r1,r2交点
        if (jds2 != null && !jds2.isEmpty()) {
            for (Coordinate jd : jds2) {//有交点
                if (p2==null&&Math.pow(jd.getX()-r2.getX(),2) + Math.pow(jd.getY()-r2.getY(),2) <= Math.pow(r2.getR(),2)) {
                    p2 = jd;

                }else if(p2!=null){
                    if(Math.pow(jd.getX()-r2.getX(),2) + Math.pow(jd.getY()-r2.getY(),2) <= Math.pow(r2.getR(),2)){
                        if(Math.pow(jd.getX()-r2.getX(),2) + Math.pow(jd.getY()-r2.getY(),2)>Math.sqrt(Math.pow(p2.getX()-r2.getX(),2) + Math.pow(p2.getY()-r2.getY(),2))){
                            p1 = jd;
                        }
                    }
                }
            }
        } else {//没有交点定位错误
            return null;
        }
        List<Coordinate> jds3 = jd(r2.getX(), r2.getY(), r2.getR(), r3.getX(), r3.getY(), r3.getR());// r1,r2交点
        if (jds3 != null && !jds3.isEmpty()) {
            for (Coordinate jd : jds3) {//有交点
                if (Math.pow(jd.getX()-r1.getX(),2) + Math.pow(jd.getY()-r1.getY(),2) <= Math.pow(r1.getR(),2)) {
                    p3 = jd;
                }else if(p3!=null){
                    if(Math.pow(jd.getX()-r1.getX(),2) + Math.pow(jd.getY()-r1.getY(),2) <= Math.pow(r1.getR(),2)){
                        if(Math.pow(jd.getX()-r1.getX(),2) + Math.pow(jd.getY()-r1.getY(),2)>Math.sqrt(Math.pow(p3.getX()-r1.getX(),2) + Math.pow(p3.getY()-r1.getY(),2))){
                            p3 = jd;
                        }
                    }
                }
            }
        } else {//没有交点定位错误
            return null;
        }

        zx.x=(p1.x+p2.x+p3.x)/3;//质心
        zx.y=(p1.y+p2.y+p3.y)/3;
        return zx;

    }

    /**
     * 求两个圆的交点
     * @param x1,y1 圆心1坐标
     * @param y1
     * @param r1 半径
     * @param x2
     * @param y2
     * @param r2
     * @return
     */
    private List<Coordinate> jd(double x1, double y1, double r1, double x2, double y2, double r2) {

        Map<Integer, double[]> p = new HashMap<Integer, double[]>();
        double d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));// 两圆心距离
        if (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < (r1 + r2)) {// 两圆向交

        }
        List<Coordinate>points  =new ArrayList<Coordinate>();//交点坐标
        Coordinate coor;
        if (d > r1 + r2 || d < Math.abs(r1 - r2)) {//相离或内含
            return null;
        } else if (x1 == x2 && y1 == y2) {//同心圆
            return null;// 同心圆 )
        }
        else if (y1 == y2 && x1 != x2) {
            double a = ((r1 * r1 - r2 * r2) - (x1 * x1 - x2 * x2)) / (2 * x2 - 2 * x1);
            if (d == Math.abs(r1 - r2) || d == r1 + r2) {// 只有一个交点时\
                coor=new Coordinate();
                coor.x=a;
                coor.y=y1;
                points.add(coor);
            } else{// 两个交点
                double t = r1 * r1 - (a - x1) * (a - x1);
                coor=new Coordinate();
                coor.x=a;
                coor.y=y1 + Math.sqrt(t);
                points.add(coor);
                coor=new Coordinate();
                coor.x=a;
                coor.y=y1 - Math.sqrt(t);
                points.add(coor);
            }
        } else if (y1 != y2) {
            double k, disp;
            k = (2 * x1 - 2 * x2) / (2 * y2 - 2 * y1);
            disp = ((r1 * r1 - r2 * r2) - (x1 * x1 - x2 * x2) - (y1 * y1 - y2 * y2)) / (2 * y2 - 2 * y1);// 直线偏移量
            double a, b, c;
            a = (k * k + 1);
            b = (2 * (disp - y1) * k - 2 * x1);
            c = (disp - y1) * (disp - y1) - r1 * r1 + x1 * x1;
            double disc;
            disc = b * b - 4 * a * c;// 一元二次方程判别式
            if (d == Math.abs(r1 - r2) || d == r1 + r2) {
                coor=new Coordinate();
                coor.x=(-b) / (2 * a);;
                coor.y= k * coor.x + disp;
                points.add(coor);
            } else {
                coor=new Coordinate();
                coor.x=((-b) + Math.sqrt(disc)) / (2 * a);
                coor.y= k * coor.x + disp;
                points.add(coor);
                coor=new Coordinate();
                coor.x=((-b) - Math.sqrt(disc)) / (2 * a);
                coor.y= k * coor.x + disp;
                points.add(coor);
            }
        }

        return points;
    }


//	public static double[] trilateration3(double x1, double y1, double d1, double x2, double y2, double d2, double x3, double y3, double d3) {
//		double[] d = { 0.0, 0.0 };
//		double l1
//
//		#include <math.h>
//
//		POINT ZX(int X1,int Y1,int X2,int Y2,int X3,int Y3) //参数分别为三角形的三个坐标点
//		{float L1,L2,L3,N;                                            //L1,L2,L3分别代表三条边的长,(N用来作交换用)
//		 POINT PN;                                                     //用来表示质心的坐标
//		 L1=sqrt((X1-X2)*(X1-X2)+(Y1-Y2)*(Y1-Y2));  //分别求出三条边的长
//		 L2=sqrt((X1-X3)*(X1-X3)+(Y1-Y3)*(Y1-Y3));
//		 L3=sqrt((X3-X2)*(X3-X2)+(Y3-Y2)*(Y3-Y2));
//
//		 if (L1<L2)                                                       //如果L2比L1大,就把两个数交换
//		    {N=L1;
//		     L1=L2;
//		     L2=N;}
//
//		 if (L1<L3)                                                       //如果L3比L1大,就把两个数交换
//		    {N=L1;
//		     L1=L3;
//		     L3=N;}
//		//经过两轮的比较和交换,可以确保L1是三条边中最大的一条
//		 if (L1>=(L2+L3))        //如果最大边大于等于两条小条的和,则三点构不成一个三角形
//		    {PN.x=0xffffffff;        //设置一个错误值
//		     PN.y=0xffffffff;
//		     return PN;}            //让函数返回错误值,这样调用函数之后就可以作出相应的判断
//
//		/*如果通过判断符合三角形的条件,求质心,质心就是重心,公式很简单.
//		就是X=(X1+X2+X3)/3;Y=(Y1+Y2+Y3)/3,如果要证明有点长,这里就不说.
//		你可以自己试着证明一下,或百度一下*/
//
//		 PN.x=(X1+X2+X3)/3;
//		 PN.y=(Y1+Y2+Y3)/3;
//		 return PN;}
//		return d;
//
//	}

}
