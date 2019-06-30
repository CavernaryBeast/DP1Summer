
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Report;
import repositories.CommentRepository;

@Transactional
@Service
public class CommentService {

	@Autowired
	private CommentRepository	commentRepository;

	@Autowired
	private ReportService		reportService;


	public Comment create() {

		final Comment res = new Comment();

		return res;
	}

	public Collection<Comment> findAll() {

		final Collection<Comment> res = this.commentRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Comment findOne(final int id) {

		Assert.isTrue(id != 0);

		final Comment res = this.commentRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	//We check that who wants to save the comment is the owner of the comment's Report
	public Comment save(final Comment comment, final int reportId) {

		Assert.notNull(comment);
		Assert.isTrue(reportId != 0);

		Comment saved;

		final Report report = this.reportService.findOne(reportId);

		//Lets see if this work
		saved = this.commentRepository.save(comment);
		report.getComments().add(saved);
		this.reportService.save(report);

		return saved;
	}

	//We check that who wants to delete the comment is the owner of the comment's Report
	public void delete(final Comment comment) {

		Assert.notNull(comment);

		final Report report = this.reportService.findByComment(comment.getId());

		report.getComments().remove(comment);
		this.reportService.save(report);

		this.commentRepository.delete(comment);
	}

}
