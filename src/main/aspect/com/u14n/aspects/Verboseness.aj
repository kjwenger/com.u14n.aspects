/**
 * 
 */
package com.u14n.aspects;

/**
 * @author Klaus Wenger
 */
public aspect Verboseness {
    pointcut syserrAccess() : get(* System.err);
    pointcut sysoutAccess() : get(* System.out);

    declare warning : syserrAccess() || sysoutAccess()
//        {
//            return "Please don't write messages to System out or err: " + thisJoinPointStaticPart.getSourceLocation();
//        }
        : "Please don't write messages to System out or err";
}
