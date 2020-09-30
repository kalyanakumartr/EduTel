package com.hbs.edutel.bo.impl;

import java.util.Date;
import java.util.List;

import com.hbs.edutel.action.AdminSerialKeyGenerateAction;
import com.hbs.edutel.bo.SerialKeyGenBo;
import com.hbs.edutel.bo.SerialKeyParam;
import com.hbs.edutel.common.action.CommonActionDAOData;
import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.SerialKeyGenerate;
import com.hbs.edutel.model.SerialKeyUserMapping;
import com.hbs.edutel.util.CommonUtil;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EKeyGen;
import com.hbs.edutel.util.common.consts.ConstInterface;

public class SerialKeyGenBoImpl extends CommonActionDAOData implements SerialKeyGenBo, ConstInterface
{

	private static final long	serialVersionUID	= 2140684638814757953L;

	
	public boolean createAndSaveSerialKey(AdminSerialKeyGenerateAction adminSerialKeyGenerateAction)
	{
		return keyGenDAO.createAndSaveSerialKey(adminSerialKeyGenerateAction);
	}

	
	public List<SerialKeyUserMapping> getPrintSerialKeyList(String keys, EKeyGen eKeyGen)
	{
		return keyGenDAO.getPrintSerialKeyList(keys, eKeyGen);
	}

	
	public List<SerialKeyUserMapping> getSerialKeyUsersList(JQueryDataTableParam jdtParam)
	{
		return keyGenDAO.getSerialKeyUsersList(jdtParam);
	}

	
	public List<SerialKeyUserMapping> getSerialKeyUsersList(SerialKeyParam skParam)
	{
		return keyGenDAO.getSerialKeyUsersList(skParam);
	}

	
	public List<SerialKeyUserMapping> getSerialKeyUsersListAll(JQueryDataTableParam jdtParam)
	{
		return keyGenDAO.getSerialKeyUsersListAll(jdtParam);
	}

	
	public boolean validateSerialKey(SerialKeyParam skParam, IUsers user)
	{
		List<SerialKeyGenerate> serialKeyList = keyGenDAO.getSerialKeyList(skParam);
		List<SerialKeyGenerate> validatedSerialKeyList = keyGenDAO.getValidatedSerialKeyList(skParam);
		// Avoid Invalid Serial Key for Registration
		if (validatedSerialKeyList.isEmpty() && serialKeyList.isEmpty())
		{
			return false;
		}
		else if (CommonValidator.isListFirstNotEmpty(serialKeyList))
		{
			skParam = new SerialKeyParam();
			skParam.serialKeyGenerate = serialKeyList.iterator().next();
			skParam.serialKeyGenerate.setModifiedDate(CommonUtil.getTimeZoneDateInFormat(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_24, IST));
			skParam.serialKeyGenerate.setSerialKeyStatus(EKeyGen.Validated.getStatus());
			if (keyGenDAO.updateSerialKeyStatus(skParam) && CommonValidator.isNotNullNotEmpty(user))
			{
				user.setUsBatch(skParam.serialKeyGenerate.getSerialBatch());
				user.setSerialKey(skParam.serialKeyGenerate);
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			// Student_Registration by Student, If Key Validation Success but Unable to Register,
			// then Student can register later using the same Key.
			List<IUsers> userList = keyGenDAO.getUserbySerialKey(skParam);
			if (CommonValidator.isListFirstNotEmpty(userList))
			{
				IUsers mappedUser = userList.iterator().next();
				return CommonValidator.isEqual(mappedUser.getUsEmployeeId(), user.getUsEmployeeId());
			}
			else
			{
				return true;
			}

		}
	}

	
	public List<SerialKeyGenerate> getValidatedSerialKeyList(SerialKeyParam skParam)
	{
		return keyGenDAO.getValidatedSerialKeyList(skParam);
	}

}
