package com.ontheroad.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortList<E> {
	
	public void Sort(List<E> list, final String method, final String sort) {
		Collections.sort(list, new Comparator<E>() {
			public int compare(Object a, Object b) {
				double ret = 0;
				try {
					Method m1 = ((E) a).getClass().getMethod(method, null);
					Method m2 = ((E) b).getClass().getMethod(method, null);
					if (sort != null && "desc".equals(sort))// 倒序
						ret = Double.parseDouble(m2.invoke(((E) b), null).toString())-Double.parseDouble(m1.invoke(((E) a), null).toString());
					else
						// 正序  
						ret = Double.parseDouble(m1.invoke(((E) a), null).toString())-Double.parseDouble(m2.invoke(((E) b), null).toString());
				} catch (NoSuchMethodException ne) {
					System.out.println(ne);
				} catch (IllegalAccessException ie) {
					System.out.println(ie);
				} catch (InvocationTargetException it) {
					System.out.println(it);
				}
				return (int)ret;
			}
		});
	}
	
	public void Sort2(List<E> list, final String method, final String sort){  
        Collections.sort(list, new Comparator() {             
            public int compare(Object a, Object b) {  
                int ret = 0;  
                try{  
                    Method m1 = ((E)a).getClass().getMethod(method, null);  
                    Method m2 = ((E)b).getClass().getMethod(method, null);  
                    if(sort != null && "desc".equals(sort))//鍊掑簭  
                        ret = m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a), null).toString());   
                    else//姝ｅ簭  
                        ret = m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());  
                }catch(NoSuchMethodException ne){  
                    System.out.println(ne);  
                }catch(IllegalAccessException ie){  
                    System.out.println(ie);  
                }catch(InvocationTargetException it){  
                    System.out.println(it);  
                }  
                return ret;  
            }  
         });  
    }  
}
