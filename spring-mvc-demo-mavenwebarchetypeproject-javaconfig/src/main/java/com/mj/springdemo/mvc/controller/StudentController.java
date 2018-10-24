package com.mj.springdemo.mvc.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mj.springdemo.mvc.model.Student;
import com.mj.springdemo.mvc.service.StudentService;
import com.mj.springdemo.mvc.util.constants.ApplicationOptionsConstants;
import com.mj.springdemo.mvc.util.constants.MessageConstants;

@Controller
@RequestMapping(value = "student")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	@Qualifier("propertiesFactoryBeanConstantsImpl")
	ApplicationOptionsConstants appOptionsConstants;
	
	@Autowired
	MessageSource messageSource;

	private Map<String, String> countryOptions = new LinkedHashMap<>();
	private Map<String, String> languageOptions = new LinkedHashMap<>();
	private Map<String, String> osOptions = new LinkedHashMap<>();

	/*----------Start: CRUD endpoints----------*/
	//Retrieve
		@GetMapping(value = "/list")
		public String listStudents(Model model,
				//flash attribute from redirecting controller
				@ModelAttribute("message") final String message) {	
			model.addAttribute("students", studentService.listStudents());
			model.addAttribute("message", message);
			return "student-list";
		}
		
		@PostMapping(value = "/searchByFirstOrLastName")
		public String listStudents(@RequestParam(name="searchName") String searchName, Model model, Locale locale) {
			if(searchName.isEmpty()) {
				return "redirect:/student/list";
			}
			
			List<Student> students = studentService.searchByFirstOrLastName(searchName);
			model.addAttribute("students", students);
			model.addAttribute("message", messageSource.getMessage(MessageConstants.N_RECORDS_FOUND, new Object[]{students.size()}, locale));
			return "student-list";
		}		
	
	//Create
		@GetMapping(value = "/add")
		public String showAddForm(Model model) {
			model.addAttribute("student", new Student());
			return "student-crud-form";
		}
		
		@PostMapping(value = "/addOrUpdate")
		public String processAddOrUpdateForm(@Valid @ModelAttribute(name = "student") Student student, BindingResult bindingResult,
				Model model, Locale locale, RedirectAttributes redirectAttrs) {
			
			if (bindingResult.hasErrors()) {
				return "student-crud-form";
			}
			
			if(studentService.addOrUpdateStudent(student)) {
				//note: id determines if record is being added or updated
				String messageConstant = (student.getId() == 0 ? MessageConstants.RECORD_SUCCESSFULLY_ADDED_FOR : MessageConstants.RECORD_SUCCESSFULLY_UPDATED_FOR);
				
				//note: flash attribute will be set to the model of the controller handling the redirect
				redirectAttrs.addAttribute("message", messageSource.getMessage(messageConstant, new Object[]{student.getFirstName()}, locale));
			}
			
			//caller to be redirected to student list endpoint
			return "redirect:/student/list";
		}
	
	//Update
		@GetMapping(value = "/edit")
		public String showEditForm(@RequestParam(name="studentId") long studentId, Model model) {
			model.addAttribute("student", studentService.getStudent(studentId));
			return "student-crud-form";
		}
		
	//Delete
		@GetMapping(value = "/delete")
		public String delete(@RequestParam(name="studentId") long studentId, Locale locale, RedirectAttributes redirectAttrs) {
			if(studentService.deleteStudent(studentId)) {
				redirectAttrs.addAttribute("message", messageSource.getMessage(MessageConstants.RECORD_SUCCESSFULLY_DELETED, null, locale));
			}
			
			//caller to be redirected to student list endpoint
			return "redirect:/student/list";
		}
	
	/*----------End: CRUD endpoints----------*/

	@GetMapping(value = "/showForm")
	public String studentForm(Model model) {
		model.addAttribute("student1", createStudentModelAttribute());
		model.addAttribute("countryOptions", countryOptions);
		model.addAttribute("favoriteLanguageOptions", languageOptions);
		model.addAttribute("osOptions", osOptions);
		return "student-form";
	}
	
	@PostMapping(value = "/processForm")
	public String processForm(@ModelAttribute("student1") com.mj.springdemo.mvc.form.model.Student student) {
		prepStudentModelAttributeForDisplay(student);
		System.out.println("student=" + student.toString());
		return "student-confirmation";
	}
	
	// get option values from property files
	@PostConstruct
	private void init() {
		// set countryOptions
		countryOptions = appOptionsConstants.getCountryOptions();
		languageOptions = appOptionsConstants.getLanguageOptions();
		osOptions = appOptionsConstants.getOsOptions();
	}

	private com.mj.springdemo.mvc.form.model.Student createStudentModelAttribute() {
		com.mj.springdemo.mvc.form.model.Student student = new com.mj.springdemo.mvc.form.model.Student();
		// Sets default country
		// Note: this works since model attribute's field getters are used when
		// rendering jsp
		student.setCountry("US");
		student.setFavoriteLanguage("002");
		return student;
	}

	private void prepStudentModelAttributeForDisplay(com.mj.springdemo.mvc.form.model.Student student) {
		String countryDescription = countryOptions.get(student.getCountry());
		student.setCountryDescription(countryDescription);

		String languageDescription = countryOptions.get(student.getFavoriteLanguage());
		student.setFavoriteLanguage(languageDescription);

		String[] osDescription = new String[student.getOperatingSystems().length];
		// for(String os : student.getOperatingSystems()) {
		for (int i = 0; i < student.getOperatingSystems().length; i++) {
			osDescription[i] = osOptions.get(student.getOperatingSystems()[i]);
		}
		student.setOperatingSystems(osDescription);
	}
}
