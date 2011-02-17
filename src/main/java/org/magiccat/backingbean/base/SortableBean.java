package org.magiccat.backingbean.base;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-9
 * Time: 上午6:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class SortableBean {
  // Sortable Headers
  private String sortColumnName;
  private boolean sortAscending;


  public SortableBean(String defaultSortColumn) {
    sortColumnName = defaultSortColumn;
    sortAscending = isDefaultAscending(defaultSortColumn);
  }

  /**
   * Is the default sortColumnName direction for the given column "sortAscending" ?
   */
  public abstract boolean isDefaultAscending(String sortColumn);

  /**
   * this function will be called when sortColumn or ascending changed.
   * it will cause datamodel to refetch data from database
   */
  public abstract void notifyDataModelChange();

  /**
   * Gets the sortColumnName column.
   *
   * @return column to sortColumnName
   */
  public String getSortColumnName() {
    return sortColumnName;
  }

  /**
   * Sets the sortColumnName column.
   *
   * @param sortColumnName column to sortColumnName
   */
  public void setSortColumnName(String sortColumnName) {
    if (!sortColumnName.equals(this.sortColumnName)) {
      this.sortColumnName = sortColumnName;
      notifyDataModelChange();
    }
  }

  /**
   * Is the sortColumnName sortAscending?
   *
   * @return true if the sortAscending sortColumnName otherwise false
   */
  public boolean isSortAscending() {
    return sortAscending;
  }

  /**
   * Sets sortColumnName type.
   *
   * @param sortAscending true for sortAscending sortColumnName, false for descending sortColumnName.
   */
  public void setSortAscending(boolean sortAscending) {
    if (sortAscending != (this.sortAscending)) {
      this.sortAscending = sortAscending;
      notifyDataModelChange();
    }
  }

}
