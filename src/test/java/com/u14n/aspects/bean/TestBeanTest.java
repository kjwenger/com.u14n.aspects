/**
 * 
 */
package com.u14n.aspects.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

import com.damnhandy.aspects.bean.JavaBean;

/**
 * @author Klaus Wenger
 * 
 * this test uses the
 * <a href="http://www.objectmentor.com/resources/articles/SelfShunPtrn.pdf">
 * self-shunt test pattern</a> for property change detection.
 */
public class TestBeanTest extends TestCase implements PropertyChangeListener {
    /**
     * Test method for {@link com.u14n.aspects.bean.TestBean#TestBean()}.
     */
    public void testTestBean() {
        assertTrue("testBean not an instance of JavaBean",
                TestBeanTest.testBean instanceof JavaBean);
    }
    /**
     * Test method for {@link com.damnhandy.aspects.bean.JavaBean#addPropertyChangeListener(java.beans.PropertyChangeListener)}.
     */
    public void testAddPropertyChangeListener() {
        JavaBean javaBean = (JavaBean) TestBeanTest.testBean;
        PropertyChangeListener[] propertyChangeListeners =
            javaBean.getPropertyChangeListeners();
        assertTrue("javaBean already had listeners",
                propertyChangeListeners == null
                || propertyChangeListeners.length == 0);
        javaBean.addPropertyChangeListener(this);
        propertyChangeListeners = javaBean.getPropertyChangeListeners();
        assertFalse("javaBean has no listeners or the wrong ones",
                propertyChangeListeners == null
                || propertyChangeListeners.length != 1);
        assertEquals("javaBean has the wrong listener", this,
                propertyChangeListeners[0]);
    }
//    /**
//     * Test method for {@link com.u14n.aspects.bean.TestBean#setBound(String)}.
//     */
//    public void testSetBound() {
//        resetSelfShunt();
//        String bound = TestBeanTest.testBean.getBound();
//        TestBeanTest.testBean.setBound(TestBeanTest.BOUND);
//        assertTrue("Property change event not triggered", this.propertyChanged);
//        assertEquals("Wrong property changed",
//                TestBeanTest.BOUND, this.propertyName);
//        assertEquals("Wrong old value replaced", bound, this.oldValue);
//        assertEquals("Wrong value set", TestBeanTest.BOUND, this.newValue);
//    }
    /**
     * Test method for {@link com.u14n.aspects.bean.TestBean#setMute(String)}.
     */
    public void testSetMute() {
        resetSelfShunt();
        TestBeanTest.testBean.setMute("muted");
        assertFalse("Property change event triggered", this.propertyChanged);
    }
//    /**
//     * Test method for {@link com.damnhandy.aspects.bean.JavaBean#removePropertyChangeListener(java.beans.PropertyChangeListener)}.
//     */
//    public void testRemovePropertyChangeListener() {
//        JavaBean javaBean = (JavaBean) TestBeanTest.testBean;
//        PropertyChangeListener[] propertyChangeListeners =
//            javaBean.getPropertyChangeListeners();
//        assertFalse("javaBean has no listeners or the wrong ones",
//                propertyChangeListeners == null
//                || propertyChangeListeners.length != 1);
////        assertEquals("javaBean has the wrong listener", this,
////                propertyChangeListeners[0]);
//        javaBean.removePropertyChangeListener(this);
//        propertyChangeListeners = javaBean.getPropertyChangeListeners();
//        assertTrue("javaBean still has listeners",
//                propertyChangeListeners == null
//                || propertyChangeListeners.length == 0);
//    }
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        this.propertyChanged = true; // Just to track that the event was called.
        this.propertyName = event.getPropertyName();
        this.oldValue = event.getOldValue();
        this.newValue = event.getNewValue();
    }
    /*
     * Nuke all previously set self-shunt values for a clean re-run. 
     */
    private void resetSelfShunt() {
        this.propertyChanged = false;
        this.propertyName = null;
        this.oldValue = null;
        this.newValue = null;
    }

    private boolean propertyChanged;
    private String propertyName;
    private Object oldValue;
    private Object newValue;

    private static TestBean testBean;
    static {
        TestBeanTest.testBean = new TestBean();
    }

    private static final String BOUND = "bound";
}
