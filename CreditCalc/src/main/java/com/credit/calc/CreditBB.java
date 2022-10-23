package com.credit.calc;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
//@SessionScoped
public class CreditBB implements Serializable {
	private Double amount;
	private Double years;
	private Double interest;
	private Double installment;
	
	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalcErr}")
	private ResourceBundle txtCalcErr;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalc}")
	private ResourceBundle txtCalc;

	@Inject
	FacesContext ctx;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getYears() {
		return years;
	}

	public void setYears(Double years) {
		this.years = years;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getInstallment() {
		return installment;
	}
	
	public void setInstallment(Double installment) {
		this.installment = installment;
	}

	public String calc() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			installment = (amount + (amount * interest / 100)) / (years * 12);
			installment = Math.round(installment * 100.0) / 100.0;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, txtCalc.getString("result") + ": " + installment, null));
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR " + e.getMessage(), null));
		}
		return null;
	}

}
