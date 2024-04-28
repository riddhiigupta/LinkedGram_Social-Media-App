import java.io.Serializable;
import java.util.*;

public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String about;
    private List<String> experience;
    private String education;
    private String awards;
    private String skills;
    private String status;

    public Profile(String about, List<String> experience, String education, String awards, String skills, String status) throws ProfileIncompleteException {
        this.about = about;
        this.experience = new List<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, Collection<? extends String> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String get(int i) {
                return null;
            }

            @Override
            public String set(int i, String s) {
                return null;
            }

            @Override
            public void add(int i, String s) {

            }

            @Override
            public String remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<String> listIterator() {
                return null;
            }

            @Override
            public ListIterator<String> listIterator(int i) {
                return null;
            }

            @Override
            public List<String> subList(int i, int i1) {
                return null;
            }
        };
        this.education = education;
        this.awards = awards;
        this.skills = skills;
        this.status = status;
        validateProfile();
    }

    private void validateProfile() throws ProfileIncompleteException {
        if (this.about == null || this.education == null || this.awards == null || this.skills == null || this.status == null) {
            throw new ProfileIncompleteException("Profile is incomplete");
        }
    }

    // Getters and setters
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<String> getExperience() {
        return experience;
    }

    public void setExperience(List<String> experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "About: " + about + "\nExperience: " + experience.toString() + "\nEducation: " + education + "\nAwards: " + awards + "\nSkills: " + skills + "\nStatus: " + status;
    }
}
