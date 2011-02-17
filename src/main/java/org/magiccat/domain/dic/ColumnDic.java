package org.magiccat.domain.dic;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.magiccat.domain.Content;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-15
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({"ALL"})
@Entity
@DiscriminatorValue("COLUMN")
public class ColumnDic extends Dic{
  @OneToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.LAZY,
      mappedBy = "column"
  )
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  private Set<Content> contents;

  public Set<Content> getContents() {
    return contents;
  }

  public void setContents(Set<Content> contents) {
    this.contents = contents;
  }
}
