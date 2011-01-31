package org.magiccat.test;

import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
public class AbstractBaseTestCase<T> extends TestCase{
  private T bean;
  private String beanName;
  private String configLocation;
  public static String DEFAULT_CONFIG_LOCATION="applicationContext.xml";

  public AbstractBaseTestCase(String beanName,String configLocation) {
    super();
    this.beanName=beanName;
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
    bean= (T) context.getBean(beanName);
  }

  @Override
  public void tearDown() throws Exception {
    super.tearDown();
    bean=null;
  }

  public T getBean() {
    return bean;
  }
}
