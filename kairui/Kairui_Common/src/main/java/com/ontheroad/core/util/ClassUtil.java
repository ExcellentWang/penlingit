package com.ontheroad.core.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 类描述：查找指定路径下面实现指定接口的全部类
 * 类名称：com.daboo.core.util.test       
 * 创建人：邓强   
 * 创建时间：2017年2月16日 下午1:57:22     
 * 修改人：  
 * 修改时间：2017年2月16日 下午1:57:22     
 * 修改备注：     
 * @version   V1.0      
 */

public class ClassUtil {
    public static <T> List<Class<T>> getAllClassByInterface(Class<T> clazz){
		return getAllClassByInterface("",clazz);
	}
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> List<Class<T>> getAllClassByInterface(String packagename, Class<T> clazz){
        ArrayList<Class<T>> list = new ArrayList<>();
        //获取指定接口的实现类
        if (clazz.isInterface()) {
            try {
                ArrayList<Class> allClass = getAllClass(packagename);
                /**
                 * 循环判断路径下的所有类是否实现了指定的接口
                 * 并且排除接口类自己
                 */
                for (int i = 0; i < allClass.size(); i++) {
                    /**
                     * 判断是不是同一个接口
                     * isAssignableFrom该方法的解析，请参考博客：
                     * http://blog.csdn.net/u010156024/article/details/44875195
                     */
                    if (clazz.isAssignableFrom(allClass.get(i))) {
                        if (!clazz.equals(allClass.get(i))) {//自身并不加进去
                            list.add(allClass.get(i));
                        }else {

                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("出现异常");
            }
            //如果不是接口，则获取它的所有子类
        }else{
            try {
                ArrayList<Class> allClass = getAllClass(packagename);
                /**
                 * 循环判断路径下的所有类是否继承了指定类
                 * 并且排除父类自己
                 */
                for (int i = 0; i < allClass.size(); i++) {
                    /**
                     * isAssignableFrom该方法的解析，请参考博客：
                     * http://blog.csdn.net/u010156024/article/details/44875195
                     */
                    if (clazz.isAssignableFrom(allClass.get(i))) {
                        if (!clazz.equals(allClass.get(i))) {//自身并不加进去
                            list.add(allClass.get(i));
                        }else {

                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("出现异常");
            }
        }
        return list;
    }
    
    /**
     * 从一个指定路径下查找所有的类
     * @param name
     */
    @SuppressWarnings("rawtypes")
    private static ArrayList<Class> getAllClass(String packagename) {
        ArrayList<Class> list = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packagename.replace('.', '/');
        try {
            ArrayList<File> fileList = new ArrayList<>();
            /**
             * 这里面的路径使用的是相对路径
             * 如果大家在测试的时候获取不到，请理清目前工程所在的路径
             * 使用相对路径更加稳定！
             * 另外，路径中切不可包含空格、特殊字符等！
             * 本人在测试过程中由于空格，吃了大亏！！！
             */
            Enumeration<URL> enumeration = classLoader.getResources(path);
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                fileList.add(new File(url.getFile()));
            }
            for (int i = 0; i < fileList.size(); i++) {
                list.addAll(findClass(fileList.get(i),packagename));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 如果file是文件夹，则递归调用findClass方法，或者文件夹下的类
     * 如果file本身是类文件，则加入list中进行保存，并返回
     * @param file
     * @param packagename
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static ArrayList<Class> findClass(File file,String packagename) {
        ArrayList<Class> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        File[] files = file.listFiles();
        for (File file2 : files) {
            if (file2.isDirectory()) {
                assert !file2.getName().contains(".");//添加断言用于判断
                ArrayList<Class> arrayList = findClass(file2, packagename+"."+file2.getName());
                list.addAll(arrayList);
            }else if(file2.getName().endsWith(".class")){
                try {
                    //保存的类文件不需要后缀.class
                    list.add(Class.forName(packagename + '.' + file2.getName().substring(0,
                            file2.getName().length()-6)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
