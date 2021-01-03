package net.froihofer.dsfinance.web;

import net.froihofer.dsfinance.service.BankServerInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class IndexJsfBean {
    @EJB
    BankServerInterface bank;

    public boolean isCallerEmployee() {
        return bank.isCallerEmployee();
    }

    public void redirect() throws IOException {
        String targetPage = bank.isCallerEmployee() ? "customerSearch.xhtml" : "portfolio.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(targetPage);
    }
}
