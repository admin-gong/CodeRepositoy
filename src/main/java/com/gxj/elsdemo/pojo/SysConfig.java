package com.gxj.elsdemo.pojo;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
 * @author beegxj
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="sys_config")
public class SysConfig implements Serializable {
  @Id
  @Column(name="variable")
  private String variable;

  @Override
  public String toString() {
    return "SysConfig{" +
            "variable='" + variable + '\'' +
            ", value='" + value + '\'' +
            ", setTime=" + setTime +
            ", setBy='" + setBy + '\'' +
            '}';
  }

  @Column(name="value")
  private String value;
  @Column(name="setTime")
  private Date setTime;
  @Column(name="setBy")
  private String setBy;

  public Date getSetTime() {
    return setTime;
  }

  public void setSetTime(Date setTime) {
    this.setTime = setTime;
  }

  public String getVariable() {
    return variable;
  }

  public void setVariable(String variable) {
    this.variable = variable;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getSetBy() {
    return setBy;
  }

  public void setSetBy(String setBy) {
    this.setBy = setBy;
  }

}
