package com.ebank.controllers;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebank.dao.ClientDAO;
import com.ebank.model.Client;
import com.ebank.model.Profile;
import com.ebank.repo.ClientRepo;
import com.ebank.services.ClientService;
import com.ebank.services.ClientServiceImpl;
import com.ebank.services.ProfileService;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	
//	@Autowired
//	private ClientDAO clientDAO;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/{id}")
	public String displayClientInfo(@PathVariable("id") int id, Model model) {
		Client client = clientService.findById(id);
		model.addAttribute("name", client.getFirstName() + client.getLastName());
		model.addAttribute("card_number", client.getCardNumber());
		model.addAttribute("count_number", client.getCountNumber());
		model.addAttribute("amount", client.getAmount() + client.getCurrency());
		Logger.getGlobal().info(clientService.findById(id).getFirstName());
		Logger.getGlobal().info(clientService.findById(id).getProfile().getLogin());
		return "show";
	}
	
	@GetMapping("/auth")
	public String authUser(@RequestParam String login, @RequestParam String pass, Model model) {
		Client client = profileService.findByLogin(login).getClient();
		model.addAttribute("client", client);
		return profileService.findByLogin(login).getPass().equals(pass) ? "show" : "";
	}
	
	@PostMapping("/newClient")
	public ResponseEntity<String> newClient(@ModelAttribute("client") Client client, @ModelAttribute("profile") Profile profile) {
		//model.addAttribute("client", client);
		//model.addAttribute("profile", profile);
		//Profile createdProfile  = profileService.save(profile);
		//client.setProfile(createdProfile);
		//clientService.save(client);
		Logger.getGlobal().info(client.getFirstName() + profile.getPass());
		return ResponseEntity.ok().body(client.getFirstName());
	}
	
	String card_num;
	
	Statement createDbStatement() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/ebank?serverTimezone=UTC&useSSL=false", "root", "root"
					);
			return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	void sendDataToDB(String f_name, String l_name, String login, String pass) {
		try {
			createDbStatement().executeUpdate(
					"INSERT INTO clients_site_log_info (FirstName, LastName, Login, Password) values"
					+ " ('" + f_name + "','" + l_name + "','" + login + "','" + pass + "');"
					);
			
			ArrayList<String> currency = new ArrayList<String>();
			currency.add("euro");
			currency.add("$");
			currency.add("lei");
			
			card_num = String.valueOf(Math.random()).substring(3,15);
			
			createDbStatement().executeUpdate(
					"INSERT INTO clients_counts_info"
					+ " (FirstName, LastName, CountNumber, Amount, Currency, CardNumber, Password) values" 
							+ " ('" + f_name + "','" + l_name + "','" + String.valueOf(Math.random()).substring(3,17) + "','"
							+ new Random().nextInt(1000) + "','" + currency.get(new Random().nextInt(3))
							+ "','" + card_num + "','" + String.valueOf(Math.random()).substring(3,7) + "');"
					);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	Boolean ifClientExist(String cardNum) {
		try {
			ResultSet rs = createDbStatement().executeQuery(
					"select * from clients_counts_info where CardNumber = '" + cardNum + "';");
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping("/sign-up-page")
	public String goToSignUpPage() {
		card_num =  "";
		return "/WEB-INF/view/sign-up-page.jsp";
	}
	
	@RequestMapping("/new_account_info_page")
	public String goToNewAccInfoPage(HttpServletRequest request, Model model) {
		
		model.addAttribute("name", request.getParameter("first_name") + " " + request.getParameter("last_name"));
		model.addAttribute("login", request.getParameter("login"));
		model.addAttribute("password", request.getParameter("pass"));
		
		System.out.println(card_num);
		if (!ifClientExist(card_num)) {
			sendDataToDB(request.getParameter("first_name"),
					request.getParameter("last_name"), request.getParameter("login"), request.getParameter("pass")
					);
		}
		
		return "/WEB-INF/view/new_account_info_page.jsp";
	}
	
	@RequestMapping("sign-in-page")
	public String goToSignInPage() {
		card_num = "";
		
		return "/WEB-INF/view/sign-in-page.jsp";
	}
	
	@RequestMapping("client-info-page")
	public String goToClientAccPage(HttpServletRequest request, Model model) {
		try {
			ResultSet rs = createDbStatement().executeQuery(
					"select * from clients_site_log_info where Login = '" + request.getParameter("login") + "' and "
							+ "Password = '" + request.getParameter("pass") + "';");
			rs.first();
			
			ResultSet result = createDbStatement().executeQuery(
					"select * from clients_counts_info where FirstName = '" + rs.getString("FirstName") + "' and "
							+ "LastName = '" + rs.getString("LastName") + "';");
			result.first();
			
			model.addAttribute("name", result.getString("FirstName") + " " + result.getString("LastName"));
			model.addAttribute("count_num", result.getString("CountNumber"));
			model.addAttribute("amount", result.getString("Amount") + " " + result.getString("Currency"));
			model.addAttribute("card_num", result.getString("CardNumber"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/view/client-info-page.jsp";
	}
	
}
