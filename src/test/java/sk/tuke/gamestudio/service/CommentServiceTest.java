package sk.tuke.gamestudio.service;

import org.junit.Test;
import sk.tuke.gamestudio.entity.Comment;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CommentServiceTest {
    private CommentService createService() {
        return new CommentServiceJDBC();
    }

    @Test
    public void testReset() {
        CommentService service = createService();
        service.reset();
        assertEquals(0, service.getComments("tetravex").size());
    }

    @Test
    public void testAddComment() {
        CommentService service = createService();
        service.reset();
        Date date = new Date();
        service.addComment(new Comment("tetravex", "Peter", "koment", date));

        List<Comment> commments = service.getComments("tetravex");

        assertEquals("tetravex", commments.get(0).getGame());
        assertEquals("Peter", commments.get(0).getPlayer());
        assertEquals("koment", commments.get(0).getComment());
        assertEquals(date, commments.get(0).getCommentedOn());
    }
}
