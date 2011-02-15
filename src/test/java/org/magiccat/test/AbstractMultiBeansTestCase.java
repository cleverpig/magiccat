package org.magiccat.test;

import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
public class AbstractMultiBeansTestCase extends TestCase{
  private HashMap<String,Object> beans;
  private String[] beanNames;
  private String configLocation;
  public static String DEFAULT_CONFIG_LOCATION="applicationContext.xml";

  public AbstractMultiBeansTestCase(String configLocation,String... beanNames) {
    super();
    this.beanNames=beanNames;
    beans=new HashMap<String,Object>();
    if (StringUtils.isEmpty(configLocation)){
      this.configLocation=DEFAULT_CONFIG_LOCATION;
    }
    else{
      this.configLocation=configLocation;
    }
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    ApplicationContext context=new ClassPathXmlApplicationContext(configLocation);
    for(String beanName:beanNames){
      Object bean= context.getBean(beanName);
      beans.put(beanName,bean);
    }
  }

  @Override
  public void tearDown() throws Exception {
    super.tearDown();
    beans=null;
    beanNames=null;
  }

  public Object getBean(String beanName) {
    return beans.get(beanName);
  }
}
