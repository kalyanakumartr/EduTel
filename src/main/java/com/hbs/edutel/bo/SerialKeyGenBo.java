package com.hbs.edutel.bo;

import java.util.List;

import com.hbs.edutel.action.AdminSerialKeyGenerateAction;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.model.SerialKeyGenerate;
import com.hbs.edutel.model.SerialKeyUserMapping;
import com.hbs.edutel.util.JQueryDataTableParam;
import com.hbs.edutel.util.common.ConstEnumUtil.EKeyGen;

public interface SerialKeyGenBo
{

	public boolean createAndSaveSerialKey(AdminSerialKeyGenerateAction adminSerialKeyGenerateAction);

	public List<SerialKeyUserMapping> getPrintSerialKeyList(String keys, EKeyGen eKeyGen);

	public List<SerialKeyUserMapping> getSerialKeyUsersList(JQueryDataTableParam jdtParam);

	public List<SerialKeyUserMapping> getSerialKeyUsersList(SerialKeyParam skParam);

	public List<SerialKeyUserMapping> getSerialKeyUsersListAll(JQueryDataTableParam jdtParam);

	public boolean validateSerialKey(SerialKeyParam skParam, IUsers user);

	public List<SerialKeyGenerate> getValidatedSerialKeyList(SerialKeyParam skParam);

}
