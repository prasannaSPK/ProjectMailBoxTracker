
/************************************************************
* 
This Project is implemented as a part of Academics by students of University of central Missouri,LeesSummit,MO 
Students: Sai Prasanna Kumar Korlakunta, Gowtami
Instructor: Dr.Sam Ramanujan
Stream Of Study: Big Data Analytics and Information Technology
Date Of Project Submitted: 30th April 2022
 

*************************************************/
package model;

import java.util.Date;

public class MailsBean {
	private int Letter_ID;
	private int MAIL_BOX_NO;
    private String FROM_ADDRESS;
    private String TO_ADDRESS;
    private Date DELIVERY_DATE_OF_LETTER;
    
    public int getLetter_ID() {
		return Letter_ID;
	}
	public void setLetter_ID(int letter_ID) {
		Letter_ID = letter_ID;
	}
    
	public int getMAIL_BOX_NO() {
		return MAIL_BOX_NO;
	}
	public void setMAIL_BOX_NO(int mAIL_BOX_NO) {
		MAIL_BOX_NO = mAIL_BOX_NO;
	}
	public String getFROM_ADDRESS() {
		return FROM_ADDRESS;
	}
	public void setFROM_ADDRESS(String fROM_ADDRESS) {
		FROM_ADDRESS = fROM_ADDRESS;
	}
	public String getTO_ADDRESS() {
		return TO_ADDRESS;
	}
	public void setTO_ADDRESS(String tO_ADDRESS) {
		TO_ADDRESS = tO_ADDRESS;
	}
	public Date getDELIVERY_DATE_OF_LETTER() {
		return DELIVERY_DATE_OF_LETTER;
	}
	public void setDELIVERY_DATE_OF_LETTER(Date dELIVERY_DATE_OF_LETTER) {
		DELIVERY_DATE_OF_LETTER = dELIVERY_DATE_OF_LETTER;
	}
	
	
    
    
}
