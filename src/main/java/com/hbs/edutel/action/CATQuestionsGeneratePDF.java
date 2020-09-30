package com.hbs.edutel.action;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.model.OnlineExam;
import com.hbs.edutel.model.OnlineExamQuestion;
import com.hbs.edutel.model.OnlineExamQuestionAnswerMapping;
import com.hbs.edutel.model.OnlineTestSeriesExamQuestionAnswerMapping;
import com.lowagie.text.Anchor;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CATQuestionsGeneratePDF
{
	private static Font		normalFont		= FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, true, 10, Font.NORMAL);
	private static Font		anchorFont		= FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, true, 10, Font.NORMAL, Color.BLUE);
	private static Font		titleFont		= FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, true, 12, Font.BOLD | Font.UNDERLINE);
	private static Font		boldFont		= FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, true, 10, Font.BOLD);
	private static Font		greetingsFont	= FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, true, 12, Font.BOLD);

	private static float	indentionLeft	= 20f;

	public static void createCATQuestionsPDF(List<OnlineExamQuestion> onlineExamQuestionList, OnlineExam onlineExam, OnlineExamQuestionMCQAction oeExamQuestionAction)
	{
		OutputStream outputStream = null;

		try
		{
			oeExamQuestionAction.getResponse().setContentType("application/pdf");
			oeExamQuestionAction.getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + onlineExam.getOeExamName() + ".pdf" + "\"");
			outputStream = oeExamQuestionAction.getResponse().getOutputStream();
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, outputStream);

			document.setMargins(36, 36, 36, 36);// LEFT_RIGHT_TOP_BOTTOM_72pt=1 inch
			document.open();

			String contextPath = oeExamQuestionAction.getRequest().getContextPath();
			String contextPathArr[] = contextPath.split("/");
			contextPath = oeExamQuestionAction.getRequest().getSession().getServletContext().getRealPath(contextPathArr[0]);
			String logoImageName = "/images/logo.png";

			Image logoImage = null;

			logoImage = Image.getInstance(contextPath + logoImageName);
			logoImage.setAlignment(Image.RIGHT);
			logoImage.scaleAbsolute(100f, 50f);

			Paragraph titlePara = new Paragraph(("CAT Exam").toUpperCase(), titleFont);
			titlePara.setAlignment(Element.ALIGN_CENTER);

			addNewLine(document, 1);

			PdfPTable headerTable = new PdfPTable(2);
			headerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			headerTable.setTotalWidth(100);
			headerTable.setWidthPercentage(100);
			headerTable.setSpacingBefore(0f);
			headerTable.setSpacingAfter(0f);
			headerTable.setWidths(new float[] { 80f, 20f });

			PdfPCell cell_title = new PdfPCell(titlePara);
			cell_title.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_title.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(cell_title);

			PdfPCell cell_logo = new PdfPCell(logoImage);
			cell_logo.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_logo.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(cell_logo);

			document.add(headerTable);

			addExamInfoTable(document, onlineExam);

			addNewLine(document, 1);

			addExamQuestionsTable(document, onlineExamQuestionList, oeExamQuestionAction);

			addNewLine(document, 1);

			Paragraph greetingsPara = new Paragraph(("ALL THE BEST").toUpperCase(), greetingsFont);
			greetingsPara.setAlignment(Element.ALIGN_CENTER);
			document.add(greetingsPara);

			document.close();

		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (outputStream != null)
				{
					outputStream.flush();
					outputStream.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	private static void addExamInfoTable(Document document, OnlineExam onlineExam)
	{
		try
		{
			PdfPTable examInfoTable = new PdfPTable(2);
			examInfoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			examInfoTable.setTotalWidth(100);
			examInfoTable.setWidthPercentage(100);
			examInfoTable.setSpacingBefore(0f);
			examInfoTable.setSpacingAfter(0f);
			examInfoTable.setWidths(new float[] { 50f, 50f });

			Chunk subTitle = new Chunk("Subject : ", normalFont);
			Chunk subName = new Chunk(onlineExam.getOeSubject(), boldFont);
			Paragraph subPara = new Paragraph();
			subPara.add(subTitle);
			subPara.add(subName);

			Chunk chapTitle = new Chunk("Chapter : ", normalFont);
			Chunk chapName = new Chunk(onlineExam.getOeChapter(), boldFont);
			Paragraph chapPara = new Paragraph();
			chapPara.add(chapTitle);
			chapPara.add(chapName);

			Chunk examTitle = new Chunk("Exam Name : ", normalFont);
			Chunk examName = new Chunk(onlineExam.getOeExamName(), boldFont);
			Paragraph examPara = new Paragraph();
			examPara.add(examTitle);
			examPara.add(examName);

			Chunk boardTitle = new Chunk("Board : ", normalFont);
			Chunk boardName = new Chunk(onlineExam.getOeSchoolType(), boldFont);
			Paragraph boardPara = new Paragraph();
			boardPara.add(boardTitle);
			boardPara.add(boardName);

			Chunk dateTitle = new Chunk("Exam Date & Time : ", normalFont);
			Chunk examDate = new Chunk(onlineExam.getOeExamDate().toString(), boldFont);
			Paragraph examDatePara = new Paragraph();
			examDatePara.add(dateTitle);
			examDatePara.add(examDate);

			Chunk durationTitle = new Chunk("Exam Duration : ", normalFont);
			Chunk examDuration = new Chunk(String.valueOf(onlineExam.getOeExamDuration()), boldFont);
			Paragraph examDurationPara = new Paragraph();
			examDurationPara.add(durationTitle);
			examDurationPara.add(examDuration);

			PdfPCell cellExamName = new PdfPCell(examPara);
			cellExamName.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellExamName.setBorder(Rectangle.NO_BORDER);
			examInfoTable.addCell(cellExamName);

			PdfPCell cellBoardName = new PdfPCell(boardPara);
			cellBoardName.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellBoardName.setBorder(Rectangle.NO_BORDER);
			examInfoTable.addCell(cellBoardName);

			PdfPCell cellSubject = new PdfPCell(subPara);
			cellSubject.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellSubject.setBorder(Rectangle.NO_BORDER);
			examInfoTable.addCell(cellSubject);

			PdfPCell cellChapter = new PdfPCell(chapPara);
			cellChapter.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellChapter.setBorder(Rectangle.NO_BORDER);
			examInfoTable.addCell(cellChapter);

			PdfPCell cellDate = new PdfPCell(examDatePara);
			cellDate.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDate.setBorder(Rectangle.NO_BORDER);
			examInfoTable.addCell(cellDate);

			PdfPCell cellDuration = new PdfPCell(examDurationPara);
			cellDuration.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDuration.setBorder(Rectangle.NO_BORDER);
			examInfoTable.addCell(cellDuration);
			document.add(examInfoTable);

		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
	}

	private static void addNewLine(Document document, int noOfLine)
	{
		try
		{
			Paragraph paragraph = new Paragraph(" ");
			for (int i = 0; i < noOfLine; i++)
			{
				document.add(paragraph);
			}
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
	}

	private static void addExamQuestionsTable(Document document, List<OnlineExamQuestion> onlineExamQuestionList, OnlineExamQuestionMCQAction oeExamQuestionAction)
	{
		try
		{

			if (CommonValidator.isListFirstNotEmpty(onlineExamQuestionList))
			{
				int index = 0;
				PdfPTable questionTable = new PdfPTable(2);
				questionTable.setHorizontalAlignment(Element.ALIGN_LEFT);
				questionTable.setTotalWidth(100);
				questionTable.setWidthPercentage(100);
				questionTable.setSpacingBefore(0f);
				questionTable.setSpacingAfter(0f);
				questionTable.setWidths(new float[] { 5f, 95f });
				for (OnlineExamQuestion oeExamQuestion : onlineExamQuestionList)
				{
					Phrase qNo = new Phrase(String.valueOf(index + 1) + "." + "\u00A0" + "\u00A0" + "\u00A0", boldFont);
					PdfPCell cell_1 = new PdfPCell(qNo);
					cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell_1.setBorder(Rectangle.NO_BORDER);
					cell_1.setSpaceCharRatio(2f);
					questionTable.addCell(cell_1);
					PdfPCell cell_2 = null;
					if (CommonValidator.isNotNullNotEmpty(oeExamQuestion.getOeQuestion()))
					{
						Paragraph qustionText = new Paragraph(oeExamQuestion.getOeQuestion(), normalFont);
						qustionText.setAlignment(Element.ALIGN_LEFT);
						qustionText.setIndentationLeft(indentionLeft);
						cell_2 = new PdfPCell(qustionText);

					}
					else if (CommonValidator.isNotNullNotEmpty(oeExamQuestion.getOeQuestionImageURL()))
					{
						try
						{
							Image questionImage = null;
							questionImage = Image.getInstance(oeExamQuestion.getOeQuestionImageURL());
							float docWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
							float imgWidth;
							if (docWidth > questionImage.getWidth())
							{
								imgWidth = questionImage.getWidth() - indentionLeft;
							}
							else
							{
								imgWidth = docWidth - indentionLeft;
							}

							questionImage.scaleAbsolute(imgWidth, questionImage.getHeight());
							questionImage.setIndentationLeft(indentionLeft);
							questionImage.setAlignment(Image.LEFT);

							cell_2 = new PdfPCell(questionImage);

						}
						catch (MalformedURLException e)
						{
							e.printStackTrace();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}

					}
					cell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell_2.setBorder(Rectangle.NO_BORDER);
					cell_2.setSpaceCharRatio(2f);

					questionTable.addCell(cell_2);

					Phrase blank_space = new Phrase(" ", boldFont);
					PdfPCell cell_3 = new PdfPCell(blank_space);
					cell_3.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell_3.setBorder(Rectangle.NO_BORDER);
					cell_3.setSpaceCharRatio(2f);

					questionTable.addCell(cell_3);

					PdfPTable ansTable = addAnswersTable(document, oeExamQuestion, oeExamQuestionAction);
					PdfPCell cell_4 = new PdfPCell(ansTable);
					cell_4.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell_4.setBorder(Rectangle.NO_BORDER);
					cell_4.setSpaceCharRatio(2f);

					questionTable.addCell(cell_4);

					index++;
				}

				document.add(questionTable);

			}
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
	}

	private static PdfPTable addAnswersTable(Document document, OnlineExamQuestion onlineExamQuestion, OnlineExamQuestionMCQAction oeExamQuestionAction)
	{
		PdfPTable answersTable = new PdfPTable(4);
		try
		{
			if (CommonValidator.isNotNullNotEmpty(onlineExamQuestion))
			{
				if (CommonValidator.isSetFirstNotEmpty(onlineExamQuestion.getOeAnswers()))
				{
					int ansIndex = 0;

					answersTable.setHorizontalAlignment(Element.ALIGN_LEFT);
					answersTable.setTotalWidth(100);
					answersTable.setWidthPercentage(100);
					answersTable.setSpacingBefore(0f);
					answersTable.setSpacingAfter(0f);
					answersTable.setWidths(new float[] { 5f, 45f, 5f, 45f });
					for (OnlineExamQuestionAnswerMapping oeQA : onlineExamQuestion.getOeAnswers())
					{
						Phrase ansNo = new Phrase(String.valueOf(ansIndex + 1) + " ) " + "\u00A0" + "\u00A0" + "\u00A0", boldFont);
						PdfPCell cell_1 = new PdfPCell(ansNo);
						cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell_1.setBorder(Rectangle.NO_BORDER);
						cell_1.setSpaceCharRatio(5f);
						answersTable.addCell(cell_1);

						PdfPCell cell_2 = new PdfPCell();
						if (CommonValidator.isNotNullNotEmpty(oeQA.getOeAnswerText()))
						{
							Paragraph answerText = new Paragraph(oeQA.getOeAnswerText(), normalFont);
							answerText.setAlignment(Element.ALIGN_LEFT);
							answerText.setIndentationLeft(indentionLeft);
							cell_2.addElement(answerText);
						}
						else if (CommonValidator.isNotNullNotEmpty(oeQA.getOeAnswerImageURL()))
						{
							try
							{
								Image answerImage = null;
								System.out.println("Cell Width : " + cell_1.getWidth() + "##" + cell_2.getWidth());
								answerImage = Image.getInstance(oeQA.getOeAnswerImageURL());
								System.out.println("ans image width : " + answerImage.getWidth());
								// float docWidth = document.getPageSize().getWidth() -
								// document.leftMargin() - document.rightMargin();
								float docWidth = (float) 250.0;
								System.out.println("doc width : " + docWidth);
								float imgWidth;
								if (docWidth > answerImage.getWidth())
								{
									imgWidth = answerImage.getWidth() - indentionLeft;
								}
								else
								{
									imgWidth = docWidth - indentionLeft;
								}

								System.out.println("modified ans image width : " + imgWidth);

								answerImage.scaleAbsolute(imgWidth, answerImage.getHeight());
								System.out.println("modified ans image width new: " + answerImage.getWidth());

								answerImage.setIndentationLeft(indentionLeft);
								answerImage.setAlignment(Image.LEFT);

								cell_2.addElement(answerImage);

							}
							catch (MalformedURLException e)
							{
								e.printStackTrace();
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}

						}
						cell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell_2.setBorder(Rectangle.NO_BORDER);
						cell_2.setSpaceCharRatio(5f);
						answersTable.addCell(cell_2);

						ansIndex++;
					}

				}

			}
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		return answersTable;
	}

	public static void exportUnvalidatedTestSeriesAnsPDF(List<OnlineTestSeriesExamQuestionAnswerMapping> oeTestSeriesUnvalidatedAnswersList, DashBoardAction dashBoardAction)
	{
		OutputStream outputStream = null;
		String fileName = "UnvalidatedTestSeriesAnswers_" + new SimpleDateFormat("MM-dd-yyyy HH-mm").format(new Date()).toString();
		try
		{
			dashBoardAction.getResponse().setContentType("application/pdf");
			dashBoardAction.getResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf" + "\"");
			outputStream = dashBoardAction.getResponse().getOutputStream();
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, outputStream);

			document.setMargins(36, 36, 36, 36);// LEFT_RIGHT_TOP_BOTTOM_72pt=1 inch
			document.open();

			String contextPath = dashBoardAction.getRequest().getContextPath();
			String contextPathArr[] = contextPath.split("/");
			contextPath = dashBoardAction.getRequest().getSession().getServletContext().getRealPath(contextPathArr[0]);
			String logoImageName = "/images/logo.png";

			Image logoImage = null;

			logoImage = Image.getInstance(contextPath + logoImageName);
			logoImage.setAlignment(Image.RIGHT);
			logoImage.scaleAbsolute(100f, 50f);

			Paragraph titlePara = new Paragraph(("UNVALIDATED TEST SERIES ANSWERS").toUpperCase(), titleFont);
			titlePara.setAlignment(Element.ALIGN_CENTER);

			addNewLine(document, 1);

			PdfPTable headerTable = new PdfPTable(2);
			headerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			headerTable.setTotalWidth(100);
			headerTable.setWidthPercentage(100);
			headerTable.setSpacingBefore(0f);
			headerTable.setSpacingAfter(0f);
			headerTable.setWidths(new float[] { 80f, 20f });

			PdfPCell cell_title = new PdfPCell(titlePara);
			cell_title.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_title.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(cell_title);

			PdfPCell cell_logo = new PdfPCell(logoImage);
			cell_logo.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_logo.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(cell_logo);

			document.add(headerTable);

			addNewLine(document, 1);

			addTestSeriesAnsTable(oeTestSeriesUnvalidatedAnswersList, document);

			document.close();
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (outputStream != null)
				{
					outputStream.flush();
					outputStream.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	private static void addTestSeriesAnsTable(List<OnlineTestSeriesExamQuestionAnswerMapping> oeTestSeriesUnvalidatedAnswersList, Document document)
	{
		try
		{
			if (CommonValidator.isListFirstNotEmpty(oeTestSeriesUnvalidatedAnswersList))
			{
				PdfPTable ansInfoTable = new PdfPTable(4);
				ansInfoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
				ansInfoTable.setTotalWidth(100);
				ansInfoTable.setWidthPercentage(100);
				ansInfoTable.setSpacingBefore(0f);
				ansInfoTable.setSpacingAfter(0f);
				ansInfoTable.setWidths(new float[] { 25f, 25f, 25f, 25f });

				Chunk stuNameTitle = new Chunk("Student Name", boldFont);
				Paragraph stuNamePara = new Paragraph();
				stuNamePara.add(stuNameTitle);

				Chunk fileNameTitle = new Chunk("File Name", boldFont);
				Paragraph fileNamePara = new Paragraph();
				fileNamePara.add(fileNameTitle);

				Chunk examNameTitle = new Chunk("Exam Name", boldFont);
				Paragraph examNamePara = new Paragraph();
				examNamePara.add(examNameTitle);

				Chunk uploadDateTitle = new Chunk("Date", boldFont);
				Paragraph uploadDatePara = new Paragraph();
				uploadDatePara.add(uploadDateTitle);

				PdfPCell cellStuName = new PdfPCell(stuNamePara);
				cellStuName.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellStuName.setBackgroundColor(Color.GRAY);
				ansInfoTable.addCell(cellStuName);

				PdfPCell cellFileName = new PdfPCell(fileNamePara);
				cellFileName.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellFileName.setBackgroundColor(Color.GRAY);
				ansInfoTable.addCell(cellFileName);

				PdfPCell cellExamName = new PdfPCell(examNamePara);
				cellExamName.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellExamName.setBackgroundColor(Color.GRAY);
				ansInfoTable.addCell(cellExamName);

				PdfPCell cellUploadDate = new PdfPCell(uploadDatePara);
				cellUploadDate.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellUploadDate.setBackgroundColor(Color.GRAY);
				ansInfoTable.addCell(cellUploadDate);
				for (OnlineTestSeriesExamQuestionAnswerMapping oeTestSeriesAnsMap : oeTestSeriesUnvalidatedAnswersList)
				{
					String url = "http://edutelacademy.com/onlineTestSeriesExamAnswerSearch.do?oeExamQuestionId=" + oeTestSeriesAnsMap.getOnlineExamQuestion().getOeQuestionId() + "&uid="
							+ Math.random();
					Chunk stuNameChunk = new Chunk(oeTestSeriesAnsMap.getCreatedUser().getUsUserName(), anchorFont);
					Paragraph stuName = new Paragraph();
					Anchor anchor = new Anchor(stuNameChunk);
					anchor.setReference(url);
					stuName.add(anchor);

					Chunk fileNameChunk = new Chunk(oeTestSeriesAnsMap.getUploadFileName(), normalFont);
					Paragraph fileName = new Paragraph();
					fileName.add(fileNameChunk);

					Chunk examNameChunk = new Chunk(oeTestSeriesAnsMap.getOnlineExamQuestion().getOnlineExam().getOeExamName(), normalFont);
					Paragraph examName = new Paragraph();
					examName.add(examNameChunk);

					Chunk uploadDateChunk = new Chunk(oeTestSeriesAnsMap.getCreatedDate().toString(), normalFont);
					Paragraph uploadDate = new Paragraph();
					uploadDate.add(uploadDateChunk);

					PdfPCell cell_1 = new PdfPCell(stuName);
					cell_1.setHorizontalAlignment(Element.ALIGN_LEFT);
					ansInfoTable.addCell(cell_1);

					PdfPCell cell_2 = new PdfPCell(fileName);
					cell_2.setHorizontalAlignment(Element.ALIGN_LEFT);
					ansInfoTable.addCell(cell_2);

					PdfPCell cell_3 = new PdfPCell(examName);
					cell_3.setHorizontalAlignment(Element.ALIGN_LEFT);
					ansInfoTable.addCell(cell_3);

					PdfPCell cell_4 = new PdfPCell(uploadDate);
					cell_4.setHorizontalAlignment(Element.ALIGN_LEFT);
					ansInfoTable.addCell(cell_4);
				}
				document.add(ansInfoTable);
			}

		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
	}
}
