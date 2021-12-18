package sg.edu.nus.CA.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.service.StudentService;
import sg.edu.nus.CA.model.LeaveHistory;





@Controller
@RequestMapping("/staff")
public class Staff {
	@Autowired
	StaffInterface sservice;
	
	@Autowired
	LeaveService lservice;


	// For students to view their own information
	@GetMapping(value = "/info/{id}")
	public String info(@PathVariable(value = "id") int id, Model model,HttpSession session) {
		
		if (!sservice.checkSession(session, "sta"))
			return "index";
		
		Staff sta = (Staff) session.getAttribute("Staff");
		model.addAttribute("Staff", sta);
		
		Staff current = sservice.findStaffById(id);
		model.addAttribute("StaffLeaveInfo", current);
		
		return "StaffView.html";
	}
}
