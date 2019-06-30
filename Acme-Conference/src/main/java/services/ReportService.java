
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Report;
import domain.Reviewer;
import repositories.ReportRepository;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private ReviewerService		reviewerService;


	public Report create() {

		final Report res = new Report();
		final Collection<Comment> comments = new ArrayList<Comment>();
		res.setComments(comments);

		return res;
	}

	public Collection<Report> findAll() {

		final Collection<Report> res = this.reportRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Report findOne(final int id) {

		Assert.isTrue(id != 0);
		final Report res = this.reportRepository.findOne(id);

		Assert.notNull(res);

		return res;
	}

	public Report save(final Report report) {

		Assert.notNull(report);

		final Reviewer principal = this.reviewerService.findByPrincipal();
		Assert.isTrue(report.getReviewer().equals(principal));

		final Report saved;

		saved = this.reportRepository.save(report);

		return saved;
	}

	public void delete(final Report report) {

		Assert.notNull(report);
		Assert.isTrue(report.getId() != 0);

		final Reviewer principal = this.reviewerService.findByPrincipal();
		Assert.isTrue(report.getReviewer().equals(principal));

		this.reportRepository.delete(report);
	}

	public Collection<Report> findByReviewer(final int id) {

		Assert.isTrue(id != 0);

		final Collection<Report> res = this.reportRepository.findByReviewer(id);
		Assert.notNull(res);

		return res;
	}

	public Collection<Report> findOwnReports() {

		final int id = this.reviewerService.findByPrincipal().getUserAccount().getId();

		final Collection<Report> res = this.findByReviewer(id);

		return res;
	}

	public Report findByComment(final int commentId) {

		Assert.isTrue(commentId != 0);

		final Report res = this.reportRepository.findByComment(commentId);
		Assert.notNull(res);

		return res;
	}
}
