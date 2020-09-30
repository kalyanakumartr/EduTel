package com.hbs.edutel.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbs.edutel.bo.SerialKeyParam;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.action.login.DataTableObject;
import com.hbs.edutel.common.model.LabelValueBean;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.logger.CustomAuditLogger;
import com.hbs.edutel.model.SerialKeyUserMapping;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EGenerate;
import com.hbs.edutel.util.common.ConstEnumUtil.EKeyGen;
import com.hbs.edutel.util.common.ConstEnumUtil.EPage;
import com.hbs.edutel.util.common.ConstEnumUtil.EPromo;
import com.hbs.edutel.util.common.ConstEnumUtil.ERole;
import com.hbs.edutel.util.common.ConstEnumUtil.ESession;
import com.hbs.edutel.util.common.factory.UsersFactory;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class AdminSerialKeyGenerateAction extends AdminSerialKeyGenerateActionData
{
	private static final long		serialVersionUID	= -6266271056186876445L;
	private final CustomAuditLogger	caLogger			= new CustomAuditLogger(this.getClass());
	private final Font				normalFont			= FontFactory.getFont("Calibri", BaseFont.IDENTITY_H, true, 10, Font.NORMAL);

	public String createAndSaveSerialKey()
	{
		clearErrors();
		clearErrorsAndMessages();
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			user = sessionUser;
			if (keyGenBo.createAndSaveSerialKey(this))
			{
				caLogger.info(Create_Serial_Key, "createAndSaveSerialKey", EPage.Success.name(), this.getClass().getName(), user.getUsEmployeeId());

				addActionError("Serial key(s) has been created successfully.");
				return EPage.Success.name();
			}
			else
			{
				caLogger.info(Create_Serial_Key, "createAndSaveSerialKey", EPage.Failure.name(), this.getClass().getName(), user.getUsEmployeeId());
				addActionError("Failed to Generate Serial key(s).");
			}
		}
		return EPage.Failure.name();

	}

	private void createSerialKeyPdf(List<SerialKeyUserMapping> printKeyList) throws IOException
	{

		Document document = new Document(PageSize.A4);

		try
		{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + "printSerialKey" + ".pdf" + "\"");
			PdfWriter.getInstance(document, response.getOutputStream());

			// pdf margin as(left : 1.5 inches, right : 1 inch, top : 0.5 inch, bottom : 1 inch)
			document.setMargins(108, 72, 36, 72);
			document.open();

			for (SerialKeyUserMapping serialKeyMap : printKeyList)
			{

				PdfPTable pdfSerialKeyTable = new PdfPTable(1);
				pdfSerialKeyTable.setHorizontalAlignment(Element.ALIGN_LEFT);
				pdfSerialKeyTable.setTotalWidth(100);
				pdfSerialKeyTable.setWidths(new float[] { 0.5f });

				PdfPCell cell = new PdfPCell(new Phrase(serialKeyMap.getSerialKeyGenerate().getSerialNo(), normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER);
				pdfSerialKeyTable.addCell(cell);

				cell = new PdfPCell(new Phrase(serialKeyMap.getSerialKeyGenerate().getSerialKey(), normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER);
				pdfSerialKeyTable.addCell(cell);
				// DecimalFormat formatter = new DecimalFormat("\u20B9 000");

				cell = new PdfPCell(new Phrase("Rs. " + CommonUtil.getDecimalValueofAmt(serialKeyMap.getSerialKeyGenerate().getSellingAmount()), normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER);
				pdfSerialKeyTable.addCell(cell);

				cell = new PdfPCell(new Phrase(serialKeyMap.getSerialKeyGenerate().getSerialBatch(), normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER);
				pdfSerialKeyTable.addCell(cell);

				document.add(pdfSerialKeyTable);

				document.newPage();

			}

			document.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (response.getOutputStream() != null)
			{
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
	}

	public String generateSerialKey()
	{
		if (CommonValidator.isNotNullNotEmpty(request.getParameter("p")) == false)
		{
			clearErrors();
			clearErrorsAndMessages();
		}
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			generateSerialKeyBatchList();
			epromoList = EPromo.getEPromoList();
			usersList.clear();
			for (IUsers usr : userBo.getUserByRole(ERole.Marketing.name()))
			{
				usersList.add(new LabelValueBean(usr.getUsUserName(), usr.getUsEmployeeId() + "-" + ERole.Marketing.name()));
			}
			for (IUsers usr : userBo.getUserByRole(ERole.Franchisee.name()))
			{
				usersList.add(new LabelValueBean(usr.getUsUserName(), usr.getUsEmployeeId() + "-" + ERole.Franchisee.name()));
			}
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String getSerialKeyAssignedUsersList()
	{
		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			try
			{
				JQueryDataTableParam jdtParam = CommonUtil.getParam(request, columnsDisplay);
				jdtParam.AdvanceSearchFilter(serialKeyAdvancedSearchColumns.split(","));
				jdtParam.globalSearchFilter();
				List<SerialKeyUserMapping> skuMappingList = keyGenBo.getSerialKeyUsersList(jdtParam);
				List<SerialKeyUserMapping> skuMappingListAll = keyGenBo.getSerialKeyUsersListAll(jdtParam);
				List<ArrayList<String>> aaData = new ArrayList<ArrayList<String>>(0);
				for (SerialKeyUserMapping skuMap : skuMappingList)
				{
					ArrayList<String> newArray = new ArrayList<String>(0);
					newArray.add(skuMap.getUsers().getUsUserName());
					newArray.add(skuMap.getSerialKeyGenerate().getSerialBatch());
					newArray.add(skuMap.getSerialKeyGenerate().getSerialNo());
					newArray.add(skuMap.getSerialKeyGenerate().getSerialKey());
					newArray.add(CommonUtil.getDecimalValueofAmt(skuMap.getSerialKeyGenerate().getAmount()));
					newArray.add(skuMap.getSerialKeyGenerate().getSerialPromo());
					Date dt = new Date(skuMap.getSerialKeyGenerate().getCreatedDate().getTime());
					newArray.add(CommonUtil.getDateInFormat(dt, DATE_FORMAT_DD_MMM_YYYY_HH_MM_AM_PM));
					if (skuMap.getUsers().isFranchisee())
						newArray.add(ERole.Franchisee.name());
					else
						newArray.add(ERole.Employee.name());

					newArray.add(skuMap.getSerialKeyGenerate().getSerialKeyStatus());
					aaData.add(newArray);
				}
				DataTableObject dataTableObject = new DataTableObject();
				dataTableObject.setAaData(aaData);
				dataTableObject.setiTotalDisplayRecords(skuMappingListAll.size());
				dataTableObject.setiTotalRecords(skuMappingListAll.size());

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String jsonString = gson.toJson(dataTableObject);
				response.getWriter().write(jsonString);
				response.getWriter().close();
				caLogger.info(Search_Serial_Key, "getSerialKeyAssignedUsersList", EPage.Success.name(), this.getClass().getName(), sessionUser.getUsEmployeeId());
			}
			catch (Exception ex)
			{
				caLogger.error(Audit_Logging_Event_DataEntry, ex.getMessage(), EPage.Failure.name(), null, user.getUsEmployeeId());
			}
		}
		return EPage.Success.name();

	}

	public String printSerialKey()
	{
		clearErrors();
		clearErrorsAndMessages();
		String keys = request.getParameter("key");
		List<SerialKeyUserMapping> printKeyList = new ArrayList<SerialKeyUserMapping>();

		IUsers sessionUser = (IUsers) request.getSession().getAttribute(ESession.UserObject.getAttribute());
		if (sessionUser != null && (sessionUser.isAdmin() || sessionUser.isSuperAdmin()))
		{
			printKeyList = keyGenBo.getPrintSerialKeyList(keys, EKeyGen.Not_Sold);

			if (CommonValidator.isListFirstNotEmpty(printKeyList))
			{
				try
				{
					createSerialKeyPdf(printKeyList);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

			}
			return EPage.Success.name();
		}
		return EPage.AccessDenied.name();
	}

	public String serialKeyValidate()
	{
		clearErrors();
		clearErrorsAndMessages();
		return EPage.Success.name();
	}

	public String serialKeyValidateAndActivate()
	{
		clearErrors();
		clearErrorsAndMessages();
		SerialKeyParam skParam = new SerialKeyParam();
		skParam.serialNo = new String[1];
		skParam.serialKey = new String[1];
		skParam.serialKey[0] = serialKeyGenerate.getSerialKey();
		skParam.serialNo[0] = serialKeyGenerate.getSerialNo();
		skParam.serialKeyStatus = EKeyGen.Not_Sold.getStatus();
		user = UsersFactory.getInstance().getUsersInstance();
		if (keyGenBo.validateSerialKey(skParam, user))
		{
			user.setUsUsersType(EGenerate.Student.name());
			user.setUsSerialKey(skParam.serialKey[0]);
			user.setProfileBy(EPage.Student.name());
			schoolList = adminBo.getSchoolsListAll(new JQueryDataTableParam());
			cityList = CommonUtil.getLabelValueList(cities);
			stateList = CommonUtil.getLabelValueList(states);

			addActionError("Serial Key : " + serialKeyGenerate.getSerialKey() + "  and Serial No : " + serialKeyGenerate.getSerialNo() + " has been validated successfully.");
			caLogger.info(Authenticate_Serial_Key, "serialKeyActivation : Key : " + skParam.serialKey[0], EPage.Success.name(), this.getClass().getName(), null);

			return EPage.Success.name();
		}
		else
		{
			addActionError("Serial Key : " + serialKeyGenerate.getSerialKey() + "  and Serial No : " + serialKeyGenerate.getSerialNo()
					+ " is Invalid or already Validated. Please contact EduTel Customer Care.");
			caLogger.info(Authenticate_Serial_Key, "serialKeyActivation : Key : " + skParam.serialKey[0], EPage.Failure.name(), this.getClass().getName(), null);
		}
		return EPage.Failure.name();

	}

	private void generateSerialKeyBatchList()
	{
		serialBatchList = new ArrayList<LabelValueBean>();

		SimpleDateFormat sdfCurrentMonth = new SimpleDateFormat("MMM");
		String currentMonth = sdfCurrentMonth.format(new Date());
		int i = (CommonValidator.isEqual(currentMonth, "APR")) ? 0 : -1;
		while ( i < 2 )
		{
			String batch = getYear(i) + "_" + getYear(++i);
			serialBatchList.add(new LabelValueBean(batch, batch));
		}

	}

	private int getYear(int reqYear)
	{
		Calendar calYear = Calendar.getInstance();
		calYear.add(Calendar.YEAR, reqYear);
		return calYear.get(Calendar.YEAR);
	}
}
