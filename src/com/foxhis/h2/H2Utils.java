package com.foxhis.h2;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class H2Utils
{
  public static String buildCreateSql(Class<?> beanClass)
  {
    H2Table ta = (H2Table)beanClass.getAnnotation(H2Table.class);
    Map<H2Column,Field> has = new HashMap<H2Column,Field>();
    Field[] fields = beanClass.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(H2Column.class)) {
        has.put(field.getAnnotation(H2Column.class), field);
      }
    }

    StringBuffer buffer = new StringBuffer();
    buffer.append("CREATE TABLE IF NOT EXISTS ");
    buffer.append("".equals(ta.name()) ? beanClass.getSimpleName() : ta.name());
    buffer.append("(");
    int i = 0;
    int n = has.size();
    for (H2Column ha : has.keySet()) {
      buffer.append("".equals(ha.name()) ? ((Field)has.get(ha)).getName() : ha.name());
      buffer.append(" ");
      buffer.append(ha.tp());
      buffer.append(" ");
      buffer.append(ha.pk() ? "NOT NULL PRIMARY KEY" : "NULL");
      i++;
      buffer.append(i < n ? "," : "");
    }
    buffer.append(")");

    return buffer.toString();
  }
}