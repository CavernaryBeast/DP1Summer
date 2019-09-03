
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Activity;
import domain.Comment;
import domain.Conference;
import domain.Report;

@Transactional
@Service
public class CommentService {

	@Autowired
	private CommentRepository	commentRepository;

	@Autowired
	private ReportService		reportService;

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private ActivityService		activityService;


	public Comment create() {

		final Comment res = new Comment();

		final Date moment = new Date(System.currentTimeMillis() - 1);

		res.setMoment(moment);

		res.setAuthor(null);

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
	public Comment saveToReport(final Comment comment, final int reportId) {

		Assert.notNull(comment);
		Assert.isTrue(reportId != 0);

		Comment saved;

		final Date moment = new Date(System.currentTimeMillis() - 1);

		comment.setMoment(moment);

		final Report report = this.reportService.findOne(reportId);

		Assert.isTrue(comment.getAuthor().equals(report.getReviewer()));

		//Lets see if this work
		saved = this.commentRepository.save(comment);
		report.getComments().add(saved);
		this.reportService.save(report);

		return saved;
	}

	//We check that who wants to save the comment is the owner of the comment's Report
	public Comment saveToConference(final Comment comment, final int conferenceId) {

		Assert.notNull(comment);
		Assert.isTrue(conferenceId != 0);

		Comment saved;

		final Date moment = new Date(System.currentTimeMillis() - 1);

		comment.setMoment(moment);

		final Conference conference = this.conferenceService.findOne(conferenceId);

		//Lets see if this work
		saved = this.commentRepository.save(comment);
		conference.getComments().add(saved);
		this.conferenceService.save(conference);

		return saved;
	}

	//We check that who wants to save the comment is the owner of the comment's Report
	public Comment saveToActivity(final Comment comment, final int activityId) {

		Assert.notNull(comment);
		Assert.isTrue(activityId != 0);

		Comment saved;

		final Date moment = new Date(System.currentTimeMillis() - 1);

		comment.setMoment(moment);

		final Activity activity = this.activityService.findOne(activityId);

		//Lets see if this work
		saved = this.commentRepository.save(comment);
		activity.getComments().add(saved);
		this.activityService.save2(activity);

		return saved;
	}

	//We check that who wants to delete the comment is the owner of the comment's Report
	public void deleteFromReport(final Comment comment) {

		Assert.notNull(comment);

		final Report report = this.reportService.findByComment(comment.getId());

		report.getComments().remove(comment);
		this.reportService.save(report);

		this.commentRepository.delete(comment);
	}

	public Collection<Double> findAvgMinMaxStddevConferenceComments() {

		final Collection<Double> res = this.commentRepository.findAvgMinMaxStddevConferenceComments();

		Assert.notNull(res);
		Assert.notEmpty(res);

		return res;
	}

	public Collection<Double> findAvgMinMaxStddevActivityComments() {

		final Collection<Double> res = this.commentRepository.findAvgMinMaxStddevActivityComments();

		Assert.notNull(res);
		Assert.notEmpty(res);

		return res;
	}

}
