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

  public DicDataModel(String catType,DicService dicService,int rowsPerPage) {
    this.dicService = dicService;
    this.catType=catType;
    this.rowsPerPage=rowsPerPage;
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
    return dicService.queryPagedDics(catType,startRow,startRow+getRowsPerPage());
  }
}
