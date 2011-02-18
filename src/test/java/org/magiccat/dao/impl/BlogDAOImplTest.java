package org.magiccat.dao.impl;

import org.magiccat.dao.BlogDAO;
import org.magiccat.dao.ColumnDicDAO;
import org.magiccat.dao.SiteUserDAO;
import org.magiccat.domain.Blog;
import org.magiccat.domain.BlogComment;
import org.magiccat.domain.SiteUser;
import org.magiccat.domain.dic.ColumnDic;
import org.magiccat.test.AbstractMultiBeansTestCase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.magiccat.test.TestConstants.TEST_APPLICATION_CONTEXT_PATH;
import static org.magiccat.dao.impl.BeanNameConstants.*;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-31
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class BlogDAOImplTest extends AbstractMultiBeansTestCase {
  public BlogDAOImplTest() {
    super(
        TEST_APPLICATION_CONTEXT_PATH,
        BLOG_DAO_BEAN_NAME,
        COLUMN_DAO_BEAN_NAME,
        SITEUSER_DAO_BEAN_NAME);
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

  private Blog constructNewBlogWithComments(
      String author,String title,String content,String comment){
    Blog blog =new Blog();
    blog.setAuthor(author);
    blog.setContent(title);
    blog.setTitle(content);

    ColumnDicDAO columnDicDAO= (ColumnDicDAO) getBean(COLUMN_DAO_BEAN_NAME);
    ColumnDic columnDic=columnDicDAO.load(1);
    blog.setColumn(columnDic);

    SiteUserDAO siteUserDAO= (SiteUserDAO) getBean(SITEUSER_DAO_BEAN_NAME);
    SiteUser siteUser=siteUserDAO.loadAndInitializeIt(1);

    BlogComment blogComment=new BlogComment();
    blogComment.setBlog(blog);
    blogComment.setCommentTime(new Date());
    blogComment.setBlogReviewer(siteUser);
    blogComment.setContent(comment);

    Set<BlogComment> blogComments=new HashSet<BlogComment>(1);
    blogComments.add(blogComment);

    siteUser.setBlogComments(blogComments);

    blog.setComments(blogComments);
    return blog;

  }
  public void testSaveWithComments() throws Exception {
    BlogDAO dao= (BlogDAO) getBean(BLOG_DAO_BEAN_NAME);
    Blog blog =constructNewBlogWithComments("cleverpig","新标题","新内容","咱来评论一下");
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

    SiteUserDAO siteUserDAO= (SiteUserDAO) getBean(SITEUSER_DAO_BEAN_NAME);
    SiteUser siteUser=siteUserDAO.loadAndInitializeIt(1);

    Set<BlogComment> blogComments=new HashSet<BlogComment>(2);

    BlogComment blogComment=new BlogComment();
    blogComment.setBlog(blog);
    blogComment.setCommentTime(new Date());
    blogComment.setContent("呵呵,新评论-1");
    blogComment.setBlogReviewer(siteUser);

    blogComments.add(blogComment);

    blogComment=new BlogComment();
    blogComment.setBlog(blog);
    blogComment.setCommentTime(new Date());
    blogComment.setContent("呵呵,新评论-2");
    blogComment.setBlogReviewer(siteUser);
    blogComments.add(blogComment);

    siteUser.setBlogComments(blogComments);

    if (blog.getComments()==null){
      blog.setComments(blogComments);
    }
    else{
      blog.getComments().addAll(blogComments);
    }
    dao.update(blog);
  }

  public void testDeleteById() throws Exception {
    BlogDAO dao= (BlogDAO) getBean(BLOG_DAO_BEAN_NAME);
    Blog blog =constructNewBlogWithComments("cleverpig","新标题","新内容","咱来评论一下");
    Integer newId=dao.save(blog);
    assertNotNull(newId);

    dao.deleteById(newId);
  }
}
