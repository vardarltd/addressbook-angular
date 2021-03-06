package addressbook;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Can be tested by
 * curl -X POST -d '{"name": "rocky", "surname": "balboa", "city": "philadelphia", "phone": "18001234567"}' -v -H "Content-Type:application/json" http://localhost:8080/api/save
 * curl http://localhost:8080/api/all
 *
 * Created by seb on 12.08.2015.
 */
@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	ContactRepository repository;

	Logger logger = LoggerFactory.getLogger("Controller");

	@ApiOperation("Returns all contacts in AddressBook")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Contact> findAll(){
		logger.info("Get request received");
		Iterable<Contact> all = repository.findAll();
		ArrayList list = new ArrayList();
		for (Contact contact : all) {
			list.add(contact);
		}
		return list;
	}

	@ApiOperation("Saves or updates a contact")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity save(@RequestBody Contact contact){
		try {
			if(contact.getName().isEmpty() || contact.getSurname().isEmpty() || contact.getCity().isEmpty() || contact.getPhone().isEmpty()){
				return ResponseEntity.badRequest().build(); //400
			}
			if(contact.getId()!=null && contact.getId().trim().isEmpty())
				contact.setId(null);
			repository.save(contact);
			logger.info("saved. current db:");
			repository.findAll().forEach(c -> System.out.println(c.toString()));
			return ResponseEntity.noContent().build(); //204
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@ApiOperation("Deletes given contact")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") String id){
		repository.delete(id);
        logger.info("deleted: "+id);
		return ResponseEntity.noContent().build();
	}

}
