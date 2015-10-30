/**
 * 
 */
package com.u14n.aspects.bean;

import java.lang.reflect.Field;
//import java.beans.PropertyVetoException;

//import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.SoftException;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.FieldSignature;

import com.damnhandy.aspects.bean.JavaBean;
import com.damnhandy.aspects.bean.Observable;
import com.damnhandy.aspects.bean.PropertyChangeAspect;

/**
 * @author Klaus Wenger
 */
public aspect PropertyChanges extends PropertyChangeAspect {
    /**
     * @param bean
     * @param value
     */
    pointcut fieldSet(JavaBean javaBean, Object value)
        : set(* (@Observable *).*)
            && args(value)
            && target(javaBean);
    /**
     * @param javaBean
     * @param value
     */
    Object around(JavaBean javaBean, Object value) : fieldSet(javaBean, value) {
        FieldSignature fieldSignature = (FieldSignature)
            thisJoinPoint.getSignature();
        Field field = fieldSignature.getField();
        String fieldName = fieldSignature.getName();
        if (isBound(field)) {
            try {
                Object before = field.get(javaBean);
                if (isConstrained(field)) {
                    this.fireVetoableChange(before, value, fieldName, javaBean);
                }
                Object result = proceed(javaBean, value);
                this.firePropertyChange(before, result, fieldName, javaBean);
                return result;
            } catch (Exception e) {
                throw new SoftException(e);
            }
        } else {
            return proceed(javaBean, value);
        }
    }
// TODO: Make annotated aspects work with maven
//    @Pointcut("set(* (@Observable *).*) && args(value) && target(javaBean)")
//    void static settingField(JavaBean javaBean, Object value) { }
//    @Around("settingField(javaBean, value)")
//    @Around("set(* (@Observable *).*) && args(value) && target(javaBean)")
//    public Object fieldChangeAdvice(ProceedingJoinPoint thisJoinPoint,
//            JavaBean javaBean, Object value)
//        throws Throwable {
//        FieldSignature fieldSignature = (FieldSignature)
//            thisJoinPoint.getSignature();
//        Field field = fieldSignature.getField();
//        String fieldName = field.getName();
//        if (isBound(field)) {
//            try {
//                Object before = field.get(javaBean);
//                if (isConstrained(field)) {
//                    this.fireVetoableChange(before, value, fieldName, javaBean);
//                }
//                Object result =
//                    thisJoinPoint.proceed(new Object[] { javaBean, value });
//                this.firePropertyChange(before, result, fieldName, javaBean);
//                return result;
//            } catch (Exception e) {
//                throw new SoftException(e);
//            }
//        } else {
//            return thisJoinPoint.proceed(new Object[] { javaBean, value });
//        }
//    }
}
