/**
 * 
 */
package com.u14n.aspects.bean;

import java.beans.PropertyVetoException;

import com.damnhandy.aspects.bean.Observable;
import com.damnhandy.aspects.bean.Constrained;
import com.damnhandy.aspects.bean.NotBound;

/**
 * @author Klaus Wenger
 */
@Observable
public class TestBean {
    /**
     * 
     */
    public TestBean() {
        super();
    }
    public String getBound() {
        return bound;
    }
    public void setBound(String bound) {
        this.bound = bound;
    }
    public String getMute() {
        return mute;
    }
    public void setMute(String mute) {
        this.mute = mute;
    }
    /**
     * @return
     */
    public String getConstrained() {
        return this.constrained;
    }
    /**
     * @param constrained
     */
    public void setConstrained(String constrained) throws PropertyVetoException {
        this.constrained = constrained;
    }

    String bound;
    @NotBound
    String mute;
    @Constrained
    String constrained;
}
