package Models;

import Repositories.SkillRepository;
import ErrorClasses.SkillNotFoundException;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String            id;
	private String            firstName;
	private String            lastName;
	private String            jobTitle;
	private String            profilePictureURL = "";
	private List<Skill>       skills;
	private String            bio;
	private List<Endorsement> endorsments       = new ArrayList<Endorsement>();

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getFirstName () {
		return firstName;
	}

	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}

	public String getLastName () {
		return lastName;
	}

	public void setLastName (String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle () {
		return jobTitle;
	}

	public void setJobTitle (String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getProfilePictureURL () {
		return profilePictureURL;
	}

	public void setProfilePictureURL (String profilePictureURL) {
		this.profilePictureURL = profilePictureURL;
	}

	public List<Skill> getSkills () {
		return skills;
	}

	public void setSkills (String skills) throws JSONException {
		this.skills = SkillRepository.setSkills(skills, "");
	}

	public int getSkillPoint (Skill skill) {
		for (Skill currSkill : skills) {
			if (currSkill.getName().equals(skill.getName()))
				return currSkill.getPoint();
		}
		return 0;
	}

	public String getBio () {
		return bio;
	}

	public void setBio (String bio) {
		this.bio = bio;
	}

	public void addSkill (Skill skill) {
		skills.add(skill);
	}

	public boolean hasSkill (String skillName) {
		for (Skill skill : skills) {
			if (skill.getName().equals(skillName))
				return true;
		}
		return false;
	}

	public boolean deleteSkill (String skillName) {
		for (Skill skill : skills) {
			if (skill.getName().equals(skillName)) {
				skills.remove(skill);
				return true;
			}
		}
		return false;
	}

	public Skill getSkill (String skillName) throws SkillNotFoundException {
		for (Skill skill : skills) {
			if (skill.getName().equals(skillName))
				return skill;
		}
		throw new SkillNotFoundException();
	}

	public void addEndorsement (Endorsement endorsement) {
		endorsments.add(endorsement);
	}

	public boolean hasEndorsed (Skill skill) {
		for (Endorsement endorsement : endorsments) {
			if (endorsement.getEndorsedSkill().equals(skill))
				return true;
		}
		return false;
	}
}
