package models;

import javax.persistence.*;

@Entity
@Table(name="subject")
public class Subject {
    
    @Id
  
    private int id;
    
    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="level")
    private String level;
    
    public Subject() {
        
    }

    public Subject(int id,String name, String description, String level) {
    	this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

    
}
