package com.quedacoder.WorkTaskApplication.enums;

public enum ProcessArea {
	
	AR ("Accounts Receivables"),
	CM ("Credit Management"),
	DM ("Dispute Management"),
	BL ("Billing"),
	AP ("Accounts Payables"),
	PC ("Product Costing"),
	PO ("Procurement"),
	FI ("Finance"),
	GL ("General Ledger");
	
	private final String processArea;
	
	ProcessArea(String processArea) {
		this.processArea = processArea;
	}
	
	public String getProcessArea() {
		return processArea;
	}

}
