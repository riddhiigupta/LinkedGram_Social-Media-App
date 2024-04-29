import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileTest {

    @Test
    public void testGetAbout() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        assertEquals("about", profile.getAbout());
    }

    @Test
    public void testSetAbout() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        profile.setAbout("newAbout");
        assertEquals("newAbout", profile.getAbout());
    }


    @Test
    public void testSetExperience() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        experience.add("experience1");
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        List<String> newExperience = new ArrayList<>();
        newExperience.add("experience2");
        profile.setExperience(newExperience);
        assertEquals(newExperience, profile.getExperience());
    }

    @Test
    public void testGetEducation() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        assertEquals("education", profile.getEducation());
    }

    @Test
    public void testSetEducation() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        profile.setEducation("newEducation");
        assertEquals("newEducation", profile.getEducation());
    }

    @Test
    public void testGetAwards() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        assertEquals("awards", profile.getAwards());
    }

    @Test
    public void testSetAwards() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        profile.setAwards("newAwards");
        assertEquals("newAwards", profile.getAwards());
    }



    @Test
    public void testSetSkills() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        profile.setSkills("newSkills");
        assertEquals("newSkills", profile.getSkills());
    }

    @Test
    public void testGetStatus() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        assertEquals("status", profile.getStatus());
    }

    @Test
    public void testSetStatus() throws ProfileIncompleteException {
        List<String> experience = new ArrayList<>();
        Profile profile = new Profile("about", experience, "education", "awards", "skills", "status");
        profile.setStatus("newStatus");
        assertEquals("newStatus", profile.getStatus());
    }


}