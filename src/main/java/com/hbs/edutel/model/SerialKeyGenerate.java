package com.hbs.edutel.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Random;

import com.hbs.edutel.common.action.CommonValidator;
import com.hbs.edutel.common.model.interfaces.IUsers;
import com.hbs.edutel.util.common.ConstEnumUtil.EKeyGen;

public class SerialKeyGenerate implements Serializable
{
	private static final long		serialVersionUID	= 1L;

	private double					amount				= 0.0;
	private String					createdBy;
	private Timestamp				createdDate;
	private String					modifiedBy;
	private Timestamp				modifiedDate;
	private double					sellingAmount		= 0.0;
	private String					serialKey;
	private String					serialKeyStatus		= EKeyGen.Not_Sold.getStatus();
	private String					serialNo;
	private String					serialBatch;
	private String					serialPromo;
	private Boolean					status;
	private IUsers					createdUser;
	private SerialKeyUserMapping	serialKeyUserMap;

	public SerialKeyGenerate(String serialKey)
	{
		this.serialKey = serialKey;
	}

	public SerialKeyGenerate()
	{
		super();
	}

	public SerialKeyGenerate(String serialNo, double amount, double sellingAmount, EKeyGen eKeyGen, String createdBy, Timestamp createdDate, String modifiedBy, Timestamp modifiedDate, Boolean status)
	{
		super();
		this.serialNo = serialNo;
		this.amount = amount;
		this.sellingAmount = sellingAmount;
		this.serialKeyStatus = eKeyGen.getStatus();
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}

	public void generateKeyAndSetInSerialKey()
	{
		String keyModel = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sbKey = new StringBuilder(15);
		for (int j = 0; j < 15; j++)
		{
			sbKey.append(keyModel.charAt(rnd.nextInt(keyModel.length())));
		}
		String batch = "";
		if (CommonValidator.isNotNullNotEmpty(serialBatch))
			serialBatch.substring(serialBatch.indexOf("_"));
		setSerialKey(batch + sbKey.toString());
	}

	public double getAmount()
	{
		return amount;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public Timestamp getCreatedDate()
	{
		return createdDate;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public Timestamp getModifiedDate()
	{
		return modifiedDate;
	}

	public double getSellingAmount()
	{
		return sellingAmount;
	}

	public String getSerialKey()
	{
		return serialKey;
	}

	public String getSerialKeyStatus()
	{
		return serialKeyStatus;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public String getSerialBatch()
	{
		return serialBatch;
	}

	public void setSerialBatch(String serialBatch)
	{
		this.serialBatch = serialBatch;
	}

	public String getSerialPromo()
	{
		return serialPromo;
	}

	public void setSerialPromo(String serialPromo)
	{
		this.serialPromo = serialPromo;
	}

	public Boolean getStatus()
	{
		return status;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Timestamp createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public void setSellingAmount(double sellingAmount)
	{
		this.sellingAmount = sellingAmount;
	}

	public void setSerialKey(String serialKey)
	{
		this.serialKey = serialKey;
	}

	public void setSerialKeyStatus(EKeyGen eKeyGen)
	{
		setSerialKeyStatus(eKeyGen.getStatus());
	}

	public void setSerialKeyStatus(String serialKeyStatus)
	{
		this.serialKeyStatus = serialKeyStatus;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public void setStatus(Boolean status)
	{
		this.status = status;
	}

	public IUsers getCreatedUser()
	{
		return createdUser;
	}

	public void setCreatedUser(IUsers createdUser)
	{
		this.createdUser = createdUser;
	}

	public SerialKeyUserMapping getSerialKeyUserMap()
	{
		return serialKeyUserMap;
	}

	public void setSerialKeyUserMap(SerialKeyUserMapping serialKeyUserMap)
	{
		this.serialKeyUserMap = serialKeyUserMap;
	}

}
