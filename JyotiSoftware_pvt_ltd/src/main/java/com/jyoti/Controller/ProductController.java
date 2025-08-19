package com.jyoti.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jyoti.Repository.ProductRepository;
import com.jyoti.model.ContactForm;
import com.jyoti.model.Product;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/home")
	public String showHomePage(Model model) { // <-- use Model, not Product
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);
		return "home";
	}

	@GetMapping("/portfolio")
	public String showPortfolioPage() {
		return "portfolio";
	}

	@GetMapping("/website")
	public String showWebsiteServicePage() {
		return "website";
	}

	@GetMapping("/app")
	public String showAppServicePage() {
		return "app-service";
	}

	@GetMapping("/seo")
	public String showSeoServicePage() {
		return "seo-service";
	}

	@GetMapping("/hosting")
	public String showHostingServicePage() {
		return "hosting-service";
	}

	@GetMapping("/blog")
	public String showBlogPage() {
		return "blog";
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}

	@GetMapping("/service")
	public String showServicePage() {
		return "service";
	}

	// Always provide the form-backing bean
	@ModelAttribute("contactForm")
	public ContactForm contactForm() {
		return new ContactForm();
	}

	// ONE handler for both URLs
	@GetMapping({ "/contact", "/contact-us" })
	public String showContactPage(Model model) {
		model.addAttribute("title", "Contact Us");
		model.addAttribute("ctaText", "Schedule a Free Call");
		return "contact";
	}

	// Simple submit: log and show a success banner (no CSRF, no security)
	@PostMapping("/contact")
	public String submitContact(@ModelAttribute("contactForm") ContactForm form, RedirectAttributes ra) {

		System.out.println("Contact message:");
		System.out.println("Name: " + form.getName());
		System.out.println("Email: " + form.getEmail());
		System.out.println("Subject: " + form.getSubject());
		System.out.println("Message: " + form.getMessage());

		// Post/Redirect/Get to avoid resubmission on refresh
		ra.addFlashAttribute("sent", true);
		return "redirect:/contact";
	}

	@GetMapping("/privacy-policy")
	public String privacy() {
		return "privacy-policy";
	}

	@GetMapping("/terms")
	public String terms() {
		return "terms";
	}

	@GetMapping("/cookie-policy")
	public String cookies() {
		return "cookie-policy";
	}

}
