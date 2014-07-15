package service;

import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import model.Company;

import java.util.List;
import java.io.Serializable;

@SessionScoped
@Named
public class ListaServ implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Company> listaUpd = new ArrayList<Company>();

	public List<Company> getListaUpd() {
		return listaUpd;
	}

	public void setListaUpd(List<Company> listaUpd) {
		this.listaUpd = listaUpd;
	}
	
	public void listaAdd(Company company){
		listaUpd.add(company);
	}
	
	public String toString(){
		return listaUpd.toString();
	}
	
}
