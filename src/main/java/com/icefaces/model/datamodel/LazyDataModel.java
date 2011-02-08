package com.icefaces.model.datamodel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import javax.faces.model.DataModel;

/**
 * <a href="LazyDataModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * This abstract class provides a mechanism for lazy-loading a JSF
 * DataModel (loading data on demand). Concrete subclasses must
 * implement the countRows(), getRowsPerPage(), and findingRows() methods, which are
 * designed to be independent of the underlying storage/persistence
 * mechanism.
 * </p>
 *
 * @author Neil Griffin
 *
 */
public abstract class LazyDataModel<DTO> extends DataModel {

  private static final Log log = LogFactory.getLog(LazyDataModel.class);

  private int rowCount = -1;
  private int rowIndex = -1;
  private List<DTO> wrappedData;
  private int wrappedDataBeginRowIndex = -1;
  private int wrappedDataEndRowIndex = -1;

  @Override public boolean isRowAvailable() {
    int rowIndex = getRowIndex();
    return (rowIndex >= 0) && (rowIndex < getRowCount());
  }

  @Override public int getRowCount() {

    if (rowCount == -1) {
      rowCount = countRows();
    }

    return rowCount;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  @Override public DTO getRowData() {

    if (getRowIndex() >= 0) {
      int adjustedRowIndex = getRowIndex() % getRowsPerPage();
      return getWrappedData().get(adjustedRowIndex);
    }
    else {
      return null;
    }
  }

  @Override public int getRowIndex() {
    return rowIndex;
  }

  @Override public void setRowIndex(int rowIndex) {

    if (rowIndex >= 0) {

      if ((rowIndex < getWrappedDataBeginRowIndex()) || (rowIndex > getWrappedDataEndRowIndex())) {

        if (log.isDebugEnabled()) {
          log.debug("rowIndex=[" + rowIndex + "] outside the range of cached wrapped data so clearing cache");
        }

        clearCache();
      }
    }

    this.rowIndex = rowIndex;
  }

  @Override public List<DTO> getWrappedData() {

    if (wrappedData == null) {

      int wrappedDataBeginRowIndex = rowIndex;
      int wrappedDataEndRowIndex = Math.min(rowIndex + getRowsPerPage() - 1, getRowCount() - 1);

      if (log.isDebugEnabled()) {
        log.debug(
            "finding new wrappedDataBeginRowIndex=[" + wrappedDataBeginRowIndex + "] wrappedDataEndRowIndex=[" +
                wrappedDataEndRowIndex + "]");
      }

      setWrappedData(findRows(wrappedDataBeginRowIndex, wrappedDataEndRowIndex));
      setWrappedDataEndRowIndex(wrappedDataEndRowIndex);
      setWrappedDataBeginRowIndex(wrappedDataBeginRowIndex);
    }

    return wrappedData;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void setWrappedData(Object wrappedData) {

    if (wrappedData == null) {
      this.wrappedData = null;
    }
    else {
      this.wrappedData = (List<DTO>)wrappedData;
    }
  }

  public int getWrappedDataBeginRowIndex() {
    return wrappedDataBeginRowIndex;
  }

  public void setWrappedDataBeginRowIndex(int wrappedDataBeginRowIndex) {
    this.wrappedDataBeginRowIndex = wrappedDataBeginRowIndex;
  }

  public int getWrappedDataEndRowIndex() {
    return wrappedDataEndRowIndex;
  }

  public void setWrappedDataEndRowIndex(int wrappedDataEndRowIndex) {
    this.wrappedDataEndRowIndex = wrappedDataEndRowIndex;
  }

  public void clearCache() {
    setRowCount(-1);
    setWrappedData(null);
    setWrappedDataBeginRowIndex(-1);
    setWrappedDataEndRowIndex(-1);
  }

  public abstract int getRowsPerPage();

  public abstract int countRows();

  public abstract List<DTO> findRows(int startRow, int finishRow);

}
