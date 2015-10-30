package com.u14n.aspects;

import junit.framework.TestCase;

/**
 * @author Klaus Wenger
 */
public class VerbosenessTest extends TestCase {
    public void testSyserrAccess() {
        System.err.println("VerbosenessTest.testSyserrAccess()");
    }
    public void testSysoutAccess() {
        System.out.println("VerbosenessTest.testSysoutAccess()");
    }
}
