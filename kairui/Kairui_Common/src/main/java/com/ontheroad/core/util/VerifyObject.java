package com.ontheroad.core.util;

import java.util.Collection;

public class VerifyObject
{
  public static boolean verifyString(String s)
  {
    if ((s != null) && (!s.trim().equals(""))) {
      return true;
    }
    return false;
  }

  public static <E> boolean verifyCollection(Collection<E> c)
  {
    if ((c != null) && (c.size() > 0)) {
      return true;
    }
    return false;
  }

  public static <E> boolean verifyArray(E[] a)
  {
    if ((a != null) && (a.length > 0)) {
      return true;
    }
    return false;
  }
}