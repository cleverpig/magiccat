package org.magiccat.domain.dic;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"JpaDataSourceORMInspection"})
@Entity(name="dic")
@Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="catType",discriminatorType=DiscriminatorType.STRING)
public abstract class Dic implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(length = 4,nullable = false)
  private String entryId;//entry Id
  @Column(length = 50,nullable = false)
  private String entryVal;//entry value
  @Column(length = 2,nullable = false)
  private String entryOrder;
  @Column(nullable = false)
  private Boolean isEnabled;//entry is enabled?

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEntryId() {
    return entryId;
  }

  public void setEntryId(String entryId) {
    this.entryId = entryId;
  }

  public String getEntryVal() {
    return entryVal;
  }

  public void setEntryVal(String entryVal) {
    this.entryVal = entryVal;
  }

  public String getEntryOrder() {
    return entryOrder;
  }

  public void setEntryOrder(String entryOrder) {
    this.entryOrder = entryOrder;
  }

  public Boolean getEnabled() {
    return isEnabled;
  }

  public void setEnabled(Boolean enabled) {
    isEnabled = enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Dic)) return false;

    Dic dic = (Dic) o;

    if (entryId != null ? !entryId.equals(dic.entryId) : dic.entryId != null) return false;
    if (entryVal != null ? !entryVal.equals(dic.entryVal) : dic.entryVal != null) return false;
    if (isEnabled != null ? !isEnabled.equals(dic.isEnabled) : dic.isEnabled != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = entryId != null ? entryId.hashCode() : 0;
    result = 31 * result + (entryVal != null ? entryVal.hashCode() : 0);
    result = 31 * result + (isEnabled != null ? isEnabled.hashCode() : 0);
    return result;
  }
}
