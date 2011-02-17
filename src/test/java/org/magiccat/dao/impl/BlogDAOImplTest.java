package org.magiccat.dao.impl;

import org.magiccat.dao.BlogDAO;
import org.magiccat.dao.ColumnDicDAO;
import org.magiccat.domain.Blog;
import org.magiccat.domain.BlogComment;
import org.magiccat.domain.dic.ColumnDic;
import org.magiccat.test.AbstractMultiBeansTestCase;

import java.util.*;

import static org.magiccat.test.TestConstants.TEST_APPLICATION_CONTEXT_PATH;
/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class BlogDAOImplTest extends AbstractMultiBeansTestCase {
  private static final String BLOG_DAO_BEAN_NAME="blogDAO";
  private static final String COLUMN_DAO_BEAN_NAME="columnDicDAO";
  public BlogDAOImplTest() {
    super(TEST_APPLICATION_CONTEXT_PATH,BLOG_DAO_BEAN_NAME,COLUMN_DAO_BEAN_NAME);
  }

  public void testSaveWithoutComments() throws Exception {
    BlogDAO dao= (BlogDAO) getBean(BLOG_DAO_BEAN_NAME);
    Blog blog =new Blog();
    blog.setAuthor("cleverpig");
    blog.setContent("new content");
    blog.setTitle("new title");

    ColumnDicDAO columnDicDAO= (ColumnDicDAO) getBean(COLUMN_DAO_BEAN_NAME);
    ColumnDic columnDic=columnDicDAO.load(1);
    blog.setColumn(columnDic);

    blog.setComments(null);

    dao.save(blog);
  }

  public void testSaveWithComments() throws Exception {
    BlogDAO dao= (BlogDAO) getBean(BLOG_DAO_BEAN_NAME);
    Blog blog =new Blog();
    blog.setAuthor("cleverpig");
    blog.setContent("新内容");
    blog.setTitle("新标题");

    ColumnDicDAO columnDicDAO= (ColumnDicDAO) getBean(COLUMN_DAO_BEAN_NAME);
    ColumnDic columnDic=columnDicDAO.load(1);
    blog.setColumn(columnDic);

    BlogComment blogComment=new BlogComment();
    blogComment.setBlog(blog);
    blogComment.setCommentTime(new Date());
    blogComment.setUserId("cleverpig");
    blogComment.setContent("呵呵,评论一下");
    Set<BlogComment> blogComments=new HashSet<BlogComment>(1);
    blogComments.add(blogComment);

    blog.setComments(blogComments);

    dao.save(blog);
  }

  public void testUpdateContentWithoutComments() throws Exception {
    BlogDAO dao= (BlogDAO) getBean(BLOG_DAO_BEAN_NAME);
    Blog blog=dao.loadAndInitializeIt(1);
    blog.setContent("最新内容");
    dao.update(blog);
  }

  public void testUpdateContentWithComments() throws Exception {
    BlogDAO dao= (BlogDAO) getBean(BLOG_DAO_BEAN_NAME);
    Blog blog=dao.loadAndInitializeIt(1);
    blog.setContent("最新内容");
//    blog.setModifyDate(new Date());

    Set<BlogComment> blogComments=new HashSet<BlogComment>(2);

    BlogComment blogComment=new BlogComment();
    blogComment.setBlog(blog);
    blogComment.setCommentTime(new Date());
    blogComment.setUserId("cleverpig");
    blogComment.setContent("呵呵,新评论-1");
    blogComments.add(blogComment);

    blogComment=new BlogComment();
    blogComment.setBlog(blog);
    blogComment.setCommentTime(new Date());
    blogComment.setUserId("cleverpig");
    blogComment.setContent("呵呵,新评论-2");
    blogComments.add(blogComment);

    if (blog.getComments()==null){
      blog.setComments(blogComments);
    }
    else{
      blog.getComments().addAll(blogComments);
    }
    dao.update(blog);
  }
}
