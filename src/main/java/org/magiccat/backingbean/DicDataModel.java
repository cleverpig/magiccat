package org.magiccat.backingbean;

import com.icefaces.model.datamodel.LazyDataModel;
import org.magiccat.domain.Dic;
import org.magiccat.service.DicService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-8
 * Time: 上午10:43
 * To change this template use File | Settings | File Templates.
 */
public class DicDataModel extends LazyDataModel<Dic> {
  private DicService dicService;
  private String catType;
  private int rowsPerPage;
  private String sortField;
  private Boolean sortAscending;

  public DicDataModel(
      String catType,
      DicService dicService,
      int rowsPerPage,
      String sortField,
      Boolean sortAscending) {
    this.dicService = dicService;
    this.catType=catType;
    this.rowsPerPage=rowsPerPage;
    this.sortField=sortField;
    this.sortAscending=sortAscending;
  }

  @Override
  public int getRowsPerPage() {
    return rowsPerPage;
  }

  @Override
  public int countRows() {
    return dicService.count().intValue();
  }

  @Override
  public List<Dic> findRows(int startRow, int finishRow) {
    return dicService.queryPagedDics(catType,sortField,sortAscending,startRow,startRow+getRowsPerPage());
  }
}
