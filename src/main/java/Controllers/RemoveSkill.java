package Controllers;


import Services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RemoveSkill {

	@RequestMapping (value = "/removeSkill", method = RequestMethod.POST)
	public ResponseEntity removeSkill (HttpServletRequest req) {

		try {
			JSONObject data   = new JSONObject(req.getParameter("data"));
			String     selfID = data.getString("selfID");
			if (selfID.equals("0"))
				return new ResponseEntity<>("login first!", HttpStatus.UNAUTHORIZED);

			if (UserService.deleteSkill(data.getString("skillName"), UserService.findUserWithID(selfID)))
				return ResponseEntity.ok("deleted successfully!");
			else
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

//data={"skillName"="HTML", "userID"="1"}
