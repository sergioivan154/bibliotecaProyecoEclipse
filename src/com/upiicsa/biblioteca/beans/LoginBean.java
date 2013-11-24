package com.upiicsa.biblioteca.beans;

import java.io.Serializable;

import com.upiicsa.biblioteca.dao.impl.UserDaoImpl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginBean")
@SessionScoped
/**
 *
 * @author User
 */
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String password;
	
	private String message, uname;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String loginProject() {

		UserDaoImpl userDaoImpl = new UserDaoImpl();
		boolean result = userDaoImpl.login(uname, password);
		String url = "";

		if (result) {
			HttpSession session = Util.getSession();
			session.setAttribute("usuario", uname);
			url = "gestion";
		} else {

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			
			context.addMessage("growl", new FacesMessage(
					"Usuario o contraseña incorrecto(s)", "Vuelve a intentar"));
			externalContext.getFlash().setKeepMessages(true);
			url = "index";
		}
		return url;
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();

		return "/index?faces-redirect=true";
	}
}