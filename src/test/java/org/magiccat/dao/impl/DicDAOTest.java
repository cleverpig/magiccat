package org.magiccat.dao.impl;

import org.magiccat.dao.DicDAO;
import org.magiccat.dao.OrderCondition;
import org.magiccat.dao.QueryCondition;
import org.magiccat.domain.Dic;
import org.magiccat.test.AbstractBaseTestCase;

import java.util.ArrayList;
import java.util.List;

import static org.magiccat.test.TestConstants.TEST_APPLICATION_CONTEXT_PATH;
/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-2-6
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class DicDAOTest extends AbstractBaseTestCase<DicDAO>{

  public DicDAOTest() {
    super("dicDAO", TEST_APPLICATION_CONTEXT_PATH);
  }

  public void testLoadByCatTypesAndEntryId() throws Exception {
    DicDAO dao=getBean();
    Dic dic=dao.loadByCatTypesAndEntryId("01","01");
    assertNotNull(dic);
  }

  public void testListByCatTypes() throws Exception {
    DicDAO dao=getBean();
    List<Dic> dics=dao.listByCatTypes("01");
    assertNotNull(dics);
    assertTrue(dics.size()>0);
  }

  public void testCountWithQueryParams() throws Exception {
    DicDAO dao=getBean();
    assertTrue(dao.count("catTypes", "01") > 0);
  }

  public void testCountWithQueryConditions() throws Exception {
    DicDAO dao=getBean();
    List<QueryCondition> q=new ArrayList<QueryCondition>();
    q.add(new QueryCondition("catTypes","01",QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
    q.add(new QueryCondition("entryId","02",QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
    assertTrue(dao.count(q) > 0);
  }

  public void testQueryPagedResultWithParams() throws Exception {
    DicDAO dao=getBean();
    List<Dic> dics=dao.queryPagedResult("catTypes","01","entryId",true,1,5);
    assertTrue(dics!=null && dics.size()>0);

    dics=dao.queryPagedResult("catTypes","01","entryId",true,3,5);
    assertTrue(dics!=null && dics.size()>0);
  }

  public void testQueryPagedResultWithConditions() throws Exception {
    DicDAO dao=getBean();
    List<QueryCondition> q=new ArrayList<QueryCondition>();
    q.add(new QueryCondition("catTypes","01",QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
//    q.add(new QueryCondition("entryId","01",QueryCondition.EQ_OP,QueryCondition.AND_RELATION));

    List<OrderCondition> o=new ArrayList<OrderCondition>();
    o.add(new OrderCondition("entryId",true));

    List<Dic> dics=dao.queryPagedResult(q,o,1,5);
    assertTrue(dics!=null && dics.size()>0);

    dics=dao.queryPagedResult(q,o,3,5);
    assertTrue(dics!=null && dics.size()>0);
  }

  public void testQueryWithConditions() throws Exception {
    DicDAO dao=getBean();
    List<QueryCondition> q=new ArrayList<QueryCondition>();
    q.add(new QueryCondition("catTypes","01",QueryCondition.EQ_OP,QueryCondition.AND_RELATION));
    q.add(new QueryCondition("entryId","01",QueryCondition.EQ_OP,QueryCondition.AND_RELATION));

    List<OrderCondition> o=new ArrayList<OrderCondition>();
    o.add(new OrderCondition("entryId",true));

    List<Dic> dics=dao.query(q,o);
    assertTrue(dics!=null && dics.size()>0);
  }

}
