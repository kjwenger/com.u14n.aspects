/**
 * 
 */
package com.u14n.aspects.bean;

//import org.aspectj.lang.annotation.DeclareParents;
//import org.aspectj.lang.annotation.DeclareMixin;

import com.damnhandy.aspects.bean.JavaBeanAspect;
import com.damnhandy.aspects.bean.JavaBean;
import com.damnhandy.aspects.bean.JavaBeanMixin;

/**
 * @author Klaus Wenger
 */
public aspect JavaBeans extends JavaBeanAspect {
    declare parents
        : (@com.damnhandy.aspects.bean.Observable *) implements JavaBean;
    declare parents
        : (@com.damnhandy.aspects.bean.Observable *) extends JavaBeanMixin;

// TODO: Make annotated aspects work with maven
//    @DeclareParents("(@com.damnhandy.aspects.bean.Observable *)")
//        JavaBean javaBean;
//    @DeclareMixin("(@com.damnhandy.aspects.bean.Observable *)")
//    public static JavaBean createJavaBean(Object object) {
//        return new JavaBeanMixin(object);
//    }
}
