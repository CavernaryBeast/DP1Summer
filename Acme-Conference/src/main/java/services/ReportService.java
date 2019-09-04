
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.ReportRepository;
import domain.Comment;
import domain.Report;
import domain.Reviewer;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private ReviewerService		reviewerService;

	@Autowired
	private Validator			validator;

	@Autowired
	private SubmissionService	submissionService;


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

		//		final Collection<Report> ownReports = this.findOwnReports();
		//		Assert.isTrue(ownReports.contains(res));

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

	public Collection<Report> findBySubmissionId(final int submissionId) {
		Assert.notNull(submissionId, "SubmissionId nulo");
		Assert.isTrue(submissionId != 0, "SubmissionId 0");
		Collection<Report> res;
		res = this.reportRepository.findBySubmissionId(submissionId);
		Assert.notNull(res);

		return res;
	}

	//	public Report reconstruct(final Report report, final BindingResult binding, final int submissionId) {
	//		this.reviewerService.findByPrincipal();
	//		Assert.isTrue(report.getId() == 0);
	//		final Submission submission = this.submissionService.findOne2(submissionId);
	//		this.submissionService.checkSubmisisonCanBeSavedToReview(submissionId);
	//		report.setSubmission(submission);
	//		this.validator.validate(report, binding);
	//		return report;
	//	}

	//	public Collection<String> generateCollectionOfDecisions(){
	//		Collection<String> res = new HashSet<>();
	//		res.add("REJECT");
	//		res.
	//		REJECT|BORDER-LINE|ACCEPT
	//	}

}
