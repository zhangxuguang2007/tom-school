package com.tom.school.utility;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtility {

	public static Map describleAvaliableParameter(Object bean)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (bean == null) {
			return new HashMap();
		}
		Map description = new HashMap();
		if (bean instanceof DynaBean) {
			DynaProperty[] descriptors = ((DynaBean) bean).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				description.put(name, org.apache.commons.beanutils.BeanUtils
						.getProperty(bean, name));
			}
		} else {
			PropertyDescriptor[] descriptors = BeanUtilsBean.getInstance()
					.getPropertyUtils().getPropertyDescriptors(bean);
			Class clazz = bean.getClass();
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (name.startsWith("$")) {
					if (MethodUtils.getAccessibleMethod(clazz,
							descriptors[i].getReadMethod()) != null) {
						description.put(name,
								PropertyUtils.getNestedProperty(bean, name));
					}
				}
			}
		}
		return description;
	}

}