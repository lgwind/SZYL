package com.szyl.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IP {
	
	@SuppressWarnings("rawtypes")
	public static String is(){
		String ip_address="localhost";
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements())
			{
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				
				Enumeration addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements())
				{
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address)
					{
						String judge=netInterface.getName().toString();
//                      System.out.println("网段名："+netInterface.getName()+"\nIP地址： " + ip.getHostAddress());
						//System.out.println(judge);
						if(judge.equals("wlan2")){
							ip_address=ip.getHostAddress();
//							System.out.println("网段名："+netInterface.getName()+"\nIP地址： " + ip.getHostAddress());
						}else if(judge.equals("eth5")){
                            ip_address=ip.getHostAddress();
//                            System.out.println("网段名："+netInterface.getName()+"\nIP地址： " + ip.getHostAddress());
                        }
					}
				}
			}
		}catch (SocketException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return ip_address;
	}
	
	public static void main(String[] args){
		IP.is();
	}
}
