package com.credit.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CreditBB {
	private String amount;
	private String years;
	private String interest;
	private Double installment;

	@Inject
	FacesContext ctx;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Double getInstallment() {
		return installment;
	}
	
	public void setInstallment(Double installment) {
		this.installment = installment;
	}

	public boolean doTheMath() {
		try {
			double years = Double.parseDouble(this.years);
			double amount = Double.parseDouble(this.amount);
			double interest = Double.parseDouble(this.interest);

			installment = (amount + (amount * interest / 100)) / (years * 12);
			installment = Math.round(installment * 100.0) / 100.0;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "results" if ok
	public String calc() {
		if (doTheMath()) {
			return "results";
		}
		return null;
	}

}
