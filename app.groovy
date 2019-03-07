@Grab("thymeleaf-spring5")

import org.springframework.core.env.*
import com.fasterxml.jackson.databind.*

@Controller
class Application {


	@Autowired
	private ObjectMapper json;

	@Value('${VCAP_APPLICATION:{}}')
	private String application;

	@Value('${VCAP_SERVICES:{}}')
	private String services;


	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("cfapp", json.readValue(application, LinkedHashMap.class))
		try {
			def cfservices = json.readValue(services, LinkedHashMap.class)
			def cfservicename = cfservices.keySet().iterator().next();
			def cfservice = cfservices.get(cfservicename).get(0);
			model.addAttribute("cfservices", cfservices)
			model.addAttribute("cfservicename", cfservicename)
			model.addAttribute("cfservice", cfservice)
		} catch (Exception ex) {
			// No services
			model.addAttribute("cfservice", "")
			model.addAttribute("cfservice", new LinkedHashMap())
		}
		return "index"
	}

}
